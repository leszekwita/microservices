package com.radzik.michal.shop.user.service.impl;

import com.radzik.michal.shop.user.domain.dao.Address;
import com.radzik.michal.shop.user.repository.AdressRepository;
import com.radzik.michal.shop.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AdressRepository adressRepository;

    @Override
    public Address save(Address address) {
        return adressRepository.save(address);
    }

    @Override
    @Transactional
    public Address update(Address address, Long id) {
        Address addressDb = findAdressById(id);
        addressDb.setName(address.getName());
        addressDb.setState(addressDb.getState());
        addressDb.setZipCode(address.getZipCode());
        addressDb.setStreetAddress(address.getStreetAddress());
        return addressDb;
    }

    @Override
    public void deleteById(Long id) {
        adressRepository.deleteById(id);
    }

    @Override
    public List<Address> getAll() {
        return adressRepository.findAll();
    }

    @Override
    public Address findAdressById(Long id) {
        return adressRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Adress does not exist"));
    }
}
