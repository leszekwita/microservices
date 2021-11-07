package com.radzik.michal.shop.user.security;


import com.radzik.michal.shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class SecurityService {

    private final UserService userService;

  //  private final ProductService productService;


    public boolean hasAccessToUser(Long id) {
        return userService.getCurrentUser().getId().equals(id);
    }

   /* public boolean hasAccesToProduct(Long productId) {

        try {
            productService.getProductByUserIdAndProductId(userService.getCurrentUser().getId(), productId);
        } catch (EntityNotFoundException e) {
            return false;
        }
        return true;
    }*/
}
