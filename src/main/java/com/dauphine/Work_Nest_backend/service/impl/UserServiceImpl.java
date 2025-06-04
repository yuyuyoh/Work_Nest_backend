package com.dauphine.Work_Nest_backend.service.impl;

import com.dauphine.Work_Nest_backend.dto.*;
import com.dauphine.Work_Nest_backend.entity.*;
import com.dauphine.Work_Nest_backend.enums.Role;
import com.dauphine.Work_Nest_backend.enums.SkillLevel;
import com.dauphine.Work_Nest_backend.mapper.EmployerMapper;
import com.dauphine.Work_Nest_backend.mapper.JobseekerMapper;
import com.dauphine.Work_Nest_backend.mapper.LoginMapper;
import com.dauphine.Work_Nest_backend.repository.*;
import com.dauphine.Work_Nest_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final LoginRepository loginRepository;
    private final JobseekerRepository jobseekerRepository;
    private final EmployerRepository employerRepository;
    private final JobseekerMapper jobseekerMapper;
    private final EmployerMapper employerMapper;
    private final LoginMapper loginMapper;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    private static final String BASE_DIR = System.getProperty("user.dir") + "/uploads/";
    private static final String CV_UPLOAD_DIR = BASE_DIR + "cv/";
    private static final String PHOTO_UPLOAD_DIR = BASE_DIR + "photo/";

    @Override
    public EmployerResponse createEmployer(EmployerRequest employerRequest) {
        if (loginRepository.findByEmail(employerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé.");
        }
        Login login = new Login();
        login.setEmail(employerRequest.getEmail());
        login.setPassword(employerRequest.getPassword());
        login.setRole(Role.EMPLOYER);
        login = loginRepository.save(login);

        Employer employer = employerMapper.toEntity(employerRequest);
        employer.setLogin(login);
        Employer saved = employerRepository.save(employer);
        return employerMapper.toResponse(saved);
    }

    @Override
    public JobseekerResponse createJobseeker(JobseekerRequest jobseekerRequest) {
        if (loginRepository.findByEmail(jobseekerRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé.");
        }
        // Création du login
        Login login = new Login();
        login.setEmail(jobseekerRequest.getEmail());
        login.setPassword(jobseekerRequest.getPassword());
        login.setRole(Role.CANDIDATE);
        login = loginRepository.save(login);

        // Création de l'entité Jobseeker
        Jobseeker jobseeker = jobseekerMapper.toEntity(jobseekerRequest);
        jobseeker.setLogin(login);
        Jobseeker saved = jobseekerRepository.save(jobseeker);

        // ENREGISTRER LES EXPERIENCES
        if (jobseekerRequest.getExperiences() != null) {
            for (WorkExperienceRequest expReq : jobseekerRequest.getExperiences()) {
                WorkExperience exp = new WorkExperience();
                exp.setJobseeker(saved);
                exp.setTitle(expReq.getTitle());
                exp.setCompany(expReq.getCompany());
                exp.setStartDate(expReq.getStartDate());
                exp.setEndDate(expReq.getEndDate());
                exp.setDescription(expReq.getDescription());
                workExperienceRepository.save(exp);
            }
        }

        // ENREGISTRER LES COMPÉTENCES
        if (jobseekerRequest.getSkills() != null) {
            for (SkillRequest skillReq : jobseekerRequest.getSkills()) {
                Skill skill = new Skill();
                skill.setJobseeker(saved);
                skill.setName(skillReq.getName());
                skill.setLevel(SkillLevel.valueOf(skillReq.getLevel().toUpperCase())); // convert String to Enum
                skillRepository.save(skill);
            }
        }

        return jobseekerMapper.toResponse(saved);
    }

    @Override
    public JobseekerResponse updateJobseekerProfile(Integer id, JobseekerRequest updates) {
        Jobseeker jobseeker = jobseekerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        Jobseeker updateData = jobseekerMapper.toEntity(updates);

        if (updateData.getFirstName() != null) jobseeker.setFirstName(updateData.getFirstName());
        if (updateData.getEducation() != null) jobseeker.setEducation(updateData.getEducation());
        if (updateData.getPhone() != null) jobseeker.setPhone(updateData.getPhone());
        if (updateData.getSpecialty() != null) jobseeker.setSpecialty(updateData.getSpecialty());
        if (updateData.getLastName() != null) jobseeker.setLastName(updateData.getLastName());

        Jobseeker saved = jobseekerRepository.save(jobseeker);
        return jobseekerMapper.toResponse(saved);
    }

    @Override
    public String uploadResume(Integer jobseekerId, MultipartFile file) {
        Jobseeker jobseeker = jobseekerRepository.findById(jobseekerId)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(CV_UPLOAD_DIR + filename);

        try {
            dest.getParentFile().mkdirs();
            System.out.println("Uploading CV to: " + dest.getAbsolutePath());
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du téléversement du CV", e);
        }

        jobseeker.setResume(filename);
        jobseekerRepository.save(jobseeker);
        return filename;
    }

    @Override
    public String uploadPhoto(Integer jobseekerId, MultipartFile file) {
        Jobseeker jobseeker = jobseekerRepository.findById(jobseekerId)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dest = new File(PHOTO_UPLOAD_DIR + filename);

        try {
            dest.getParentFile().mkdirs();
            System.out.println("Uploading photo to: " + dest.getAbsolutePath());
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du téléversement de la photo", e);
        }

        jobseeker.setPhoto(filename);
        jobseekerRepository.save(jobseeker);
        return filename;
    }

    @Override
    public Jobseeker getJobseekerById(Integer id) {
        return jobseekerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));
    }

    @Override
    public JobseekerResponse getJobseekerResponseById(Integer id) {
        Jobseeker jobseeker = jobseekerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jobseeker not found"));
        System.out.println(">>> Résumé dans l'entité : " + jobseeker.getResume());
        return jobseekerMapper.toResponse(jobseeker);
    }

    @Override
    public LoginResponse authenticate(String email, String password) {
        Optional<Login> loginOpt = loginRepository.findByEmail(email);
        if (loginOpt.isEmpty() || !password.equals(loginOpt.get().getPassword())) {
            return null;
        }

        Login login = loginOpt.get();

        Integer userId = null;
        String roleStr = null;
        if (login.getJobseeker() != null) {
            userId = login.getJobseeker().getId();
            roleStr = "CANDIDATE";
        } else if (login.getEmployer() != null) {
            userId = login.getEmployer().getId();
            roleStr = "EMPLOYER";
        }

        if (userId == null || roleStr == null) {
            return null;
        }

        LoginResponse response = new LoginResponse();
        response.setRole(roleStr);
        response.setUserId(userId);
        return response;
    }
}
