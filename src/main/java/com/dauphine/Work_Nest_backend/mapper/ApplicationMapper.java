package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.ApplicationRequest;
import com.dauphine.Work_Nest_backend.dto.ApplicationResponse;
import com.dauphine.Work_Nest_backend.entity.Application;
import com.dauphine.Work_Nest_backend.entity.Job;
import com.dauphine.Work_Nest_backend.entity.Jobseeker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    // Pour convertir ApplicationRequest → Application (entité)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "job", source = "jobId", qualifiedByName = "jobFromId")
    @Mapping(target = "jobseeker", source = "jobseekerId", qualifiedByName = "jobseekerFromId")
    @Mapping(source = "status", target = "status")
    @Mapping(target = "appliedAt", ignore = true) // géré par @CreationTimestamp
    Application toEntity(ApplicationRequest request);

    // Pour convertir Application → ApplicationResponse (DTO sortie enrichi)
    @Mapping(source = "job.id", target = "jobId")
    @Mapping(source = "job.title", target = "jobTitle")
    @Mapping(source = "jobseeker.id", target = "jobseekerId")
    @Mapping(source = "jobseeker.phone", target = "jobseekerPhone")
    @Mapping(source = "jobseeker.firstName", target = "jobseekerFirstName")
    @Mapping(source = "jobseeker.lastName", target = "jobseekerLastName")
    @Mapping(target = "jobseekerEmail", expression = "java(application.getJobseeker() != null && application.getJobseeker().getLogin() != null ? application.getJobseeker().getLogin().getEmail() : null)")
    @Mapping(source = "jobseeker.resume", target = "jobseekerCV")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "appliedAt", target = "appliedAt")
    ApplicationResponse toResponse(Application application);

    // Fonctions utilitaires MapStruct
    @Named("jobFromId")
    default Job jobFromId(Integer id) {
        if (id == null) return null;
        Job job = new Job();
        job.setId(id);
        return job;
    }

    @Named("jobseekerFromId")
    default Jobseeker jobseekerFromId(Integer id) {
        if (id == null) return null;
        Jobseeker js = new Jobseeker();
        js.setId(id);
        return js;
    }
}
