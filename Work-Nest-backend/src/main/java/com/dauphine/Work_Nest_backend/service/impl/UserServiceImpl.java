package com.dauphine.Work_Nest_backend.service.impl;

import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import com.dauphine.Work_Nest_backend.entity.Login;
import com.dauphine.Work_Nest_backend.repository.EmployerRepository;
import com.dauphine.Work_Nest_backend.repository.JobseekerRepository;
import com.dauphine.Work_Nest_backend.repository.LoginRepository;
import com.dauphine.Work_Nest_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final LoginRepository loginRepository;
    private final JobseekerRepository jobseekerRepository;
    private final EmployerRepository employerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Login authenticate(String email, String password) {
        Optional<Login> login = loginRepository.findByEmail(email);
        if (login.isPresent() && passwordEncoder.matches(password, login.get().getPassword())) {
            return login.get();
        }
        throw new RuntimeException("Email ou mot de passe invalide");
    }

    @Override
    public Jobseeker createJobseeker(Jobseeker jobseeker) {
        // Encoder le mot de passe avant sauvegarde
        jobseeker.getLogin().setPassword(passwordEncoder.encode(jobseeker.getLogin().getPassword()));
        loginRepository.save(jobseeker.getLogin());
        return jobseekerRepository.save(jobseeker);
    }

    @Override
    public Jobseeker updateJobseekerProfile(Integer id, Jobseeker updates) {
        Jobseeker jobseeker = jobseekerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        // Mise à jour des champs
        if (updates.getFirstName() != null) jobseeker.setFirstName(updates.getFirstName());
        if (updates.getEducation() != null) jobseeker.setEducation(updates.getEducation());

        return jobseekerRepository.save(jobseeker);
    }
}