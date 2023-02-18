package com.radzik.michal.shop.user.repository;

import com.radzik.michal.shop.user.domain.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Address,Long> {
}
