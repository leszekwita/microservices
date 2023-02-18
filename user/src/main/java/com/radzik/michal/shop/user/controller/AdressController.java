package com.radzik.michal.shop.user.controller;


import com.radzik.michal.shop.common.dto.AddressDto;
import com.radzik.michal.shop.user.domain.dao.Address;
import com.radzik.michal.shop.user.mapper.AddressMapper;
import com.radzik.michal.shop.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/adress")
public class AdressController {

    private final AddressService addressService;

    private final AddressMapper addressMapper;

    @PostMapping
    public AddressDto saveAdress(@RequestBody AddressDto addressDto) {
        return addressMapper.toDto(addressService.save(addressMapper.toDao(addressDto)));
    }

    @PutMapping("/{id}")
    public AddressDto updateAdress(@RequestBody AddressDto address, @PathVariable Long id) {
        return addressMapper.toDto(addressService.update(addressMapper.toDao(address), id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdress(@PathVariable Long id) {
        addressService.deleteById(id);
    }

    @GetMapping("/{id}")
    public AddressDto getAddressById(@PathVariable Long id) {
        return addressMapper.toDto(addressService.findAdressById(id));
    }

    @GetMapping
    public List<Address> getAddress() {
        return addressService.getAll();
    }

}
