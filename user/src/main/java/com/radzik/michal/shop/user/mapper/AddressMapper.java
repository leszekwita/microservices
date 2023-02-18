package com.radzik.michal.shop.user.mapper;

import com.radzik.michal.shop.common.dto.AddressDto;
import com.radzik.michal.shop.user.domain.dao.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends AuditableMapper<Address, AddressDto> {


    AddressDto toDto(Address address);

    Address toDao(AddressDto addressDto);
}
