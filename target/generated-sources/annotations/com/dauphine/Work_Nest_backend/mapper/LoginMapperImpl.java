package com.dauphine.Work_Nest_backend.mapper;

import com.dauphine.Work_Nest_backend.dto.LoginRequest;
import com.dauphine.Work_Nest_backend.dto.LoginResponse;
import com.dauphine.Work_Nest_backend.entity.Login;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-04T12:53:02+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Eclipse Adoptium)"
)
@Component
public class LoginMapperImpl implements LoginMapper {

    @Override
    public Login toEntity(LoginRequest request) {
        if ( request == null ) {
            return null;
        }

        Login login = new Login();

        login.setEmail( request.getEmail() );
        login.setPassword( request.getPassword() );

        return login;
    }

    @Override
    public LoginResponse toResponse(Login entity) {
        if ( entity == null ) {
            return null;
        }

        LoginResponse loginResponse = new LoginResponse();

        if ( entity.getRole() != null ) {
            loginResponse.setRole( entity.getRole().name() );
        }

        return loginResponse;
    }
}
