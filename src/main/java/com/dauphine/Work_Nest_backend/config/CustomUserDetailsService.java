package com.dauphine.Work_Nest_backend.config;

import com.dauphine.Work_Nest_backend.entity.Login;
import com.dauphine.Work_Nest_backend.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Login login = loginRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));

        return new org.springframework.security.core.userdetails.User(
                login.getEmail(),
                login.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + login.getRole().name()))
        );
    }
}
