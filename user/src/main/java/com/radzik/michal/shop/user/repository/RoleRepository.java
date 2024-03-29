package com.radzik.michal.shop.user.repository;


import com.radzik.michal.shop.user.domain.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role>findByName (String name);
}
