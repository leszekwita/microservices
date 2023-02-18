package com.radzik.michal.shop.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.radzik.michal.shop.common.validator.SamePassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SamePassword//(//groups = Create.class)
public non-sealed class UserDto extends AuditableDto {

    private Long id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 5, max = 10)
    private String password;

    @NotBlank
    @Size(min = 5, max = 10)
    private String confirmPassword;

    //private LocalDateTime createdDate;

    //private LocalDateTime lastModifiedDate;

    private String revType;

    private Long operationNumber;

    private Set<String> roles;

    private AddressDto address;

}
