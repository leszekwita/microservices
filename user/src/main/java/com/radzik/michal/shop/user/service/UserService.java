package com.radzik.michal.shop.user.service;


import com.radzik.michal.shop.user.exception.InvalidTokenException;
import javassist.tools.web.BadHttpRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.radzik.michal.shop.user.domain.dao.User;

public interface UserService {

    User save (User user);

    User findUserById(Long id);

    void deleteById(Long id);

    Page<User> getPage(Pageable pageable);

    User update(User user, Long id);

    User getCurrentUser();

    void remaindPassword(String email);

    void restartPassword(String newPassword, String code);

    void validToken(String token);

}
