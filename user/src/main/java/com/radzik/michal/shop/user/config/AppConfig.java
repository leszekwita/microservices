package com.radzik.michal.shop.user.config;


import com.radzik.michal.shop.user.domain.dao.Role;
import com.radzik.michal.shop.user.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class AppConfig {

    private static final List<String> ROLES = Arrays.asList("ROLE_USER","ROLE_ADMIN");


    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository){
        return args ->


            addRole(roleRepository,ROLES);

        }


    private void addRole(RoleRepository roleRepository, List<String> roles) {
        for (String role : roles) {
            Optional<Role> optionalRole = roleRepository.findByName(role);
            if(!optionalRole.isPresent()){
                roleRepository.save(new Role(null,role));
            }
        }

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
