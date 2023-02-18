package com.radzik.michal.shop.user.service;

import com.radzik.michal.shop.user.domain.dao.Address;

import java.util.List;

public interface AddressService {
    Address save(Address address);
    Address update(Address address, Long id);
    void deleteById(Long id);
    List<Address> getAll();
    Address findAdressById(Long id);
}
