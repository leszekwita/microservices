package com.radzik.michal.shop.user.service.impl;


import com.radzik.michal.shop.common.dto.MailDto;
import com.radzik.michal.shop.user.client.NotificationClient;
import com.radzik.michal.shop.user.domain.dao.User;
import com.radzik.michal.shop.user.domain.dto.RestartPasswordMailDto;
import com.radzik.michal.shop.user.exception.InvalidTokenException;
import com.radzik.michal.shop.user.repository.RoleRepository;
import com.radzik.michal.shop.user.repository.UserRepository;
import com.radzik.michal.shop.user.service.UserService;
import javassist.tools.web.BadHttpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final NotificationClient notificationClient;

    @Override
    public User save(User user) {
        roleRepository.findByName("ROLE_USER").ifPresent(role->user.setRoles(Collections.singleton(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new EntityNotFoundException("User does not exist"));
    }


    @Override
    public void deleteById(Long id) {
    userRepository.deleteById(id);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User update(User user, Long id) {
        User userDb = findUserById(id);
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        userDb.setEmail(user.getEmail());

        return save(userDb);
    }

    @Override
    public User getCurrentUser() {

        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(()->new EntityNotFoundException("User does not exist or is not logged"));
    }

    @Override
    @Transactional
    public void remaindPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(email));
        user.setToken(UUID.randomUUID().toString());

        MailDto<RestartPasswordMailDto> mailDto = MailDto.<RestartPasswordMailDto>builder()
                .to(user.getEmail())
                .templateName("restart_password")
                .variables(new RestartPasswordMailDto("http://localhost:8091/api/users/token?token=" + user.getToken()))
                .build();
        notificationClient.sendEmail(mailDto);

    }

    @Override
    @Transactional
    public void restartPassword(String newPassword, String code) {
        User user = userRepository.findByToken(code).orElseThrow(() -> new EntityNotFoundException(code));
        user.setPassword(passwordEncoder.encode(newPassword));
    }

    @Override
    public void validToken(String token) {
        Optional<User> user = userRepository.findByToken(token);
        if (!user.isPresent()) {
            throw new InvalidTokenException("Bad token");
        }
    }
}
