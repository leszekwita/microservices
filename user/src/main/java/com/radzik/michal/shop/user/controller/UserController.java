package com.radzik.michal.shop.user.controller;

import com.radzik.michal.shop.common.dto.UserDto;
import com.radzik.michal.shop.common.validator.group.Create;
import com.radzik.michal.shop.user.domain.dto.RemaindDto;
import com.radzik.michal.shop.user.mapper.UserMapper;
import com.radzik.michal.shop.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping
    @Validated(Create.class)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody @Valid UserDto user) {

        return userMapper.toDto(userService.save(userMapper.toDao(user)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.toDto(userService.findUserById(id));
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(security= { @SecurityRequirement(name = "BasicToken"), @SecurityRequirement(name = "BearerToken")})
    public Page<UserDto> getUserPage(@RequestParam int page, @RequestParam int size) {

        return userService.getPage(PageRequest.of(page, size)).map(userMapper::toDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || @securityService.hasAccessToUser(#id)")
    public UserDto updateUser(@RequestBody UserDto user, @PathVariable Long id) {
        return userMapper.toDto(userService.update(userMapper.toDao(user), id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/current")
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "get information about login current user", security = @SecurityRequirement(name = "BearerToken"))
    public UserDto getCurrentUser() {
        return userMapper.toDto(userService.getCurrentUser());
    }

    @PatchMapping("/remaind")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remaindPassword(@RequestBody RemaindDto remaindDto) {
        userService.remaindPassword(remaindDto.getEmail());
    }
    @GetMapping("/token")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void validToken(@RequestParam String token) {
        userService.validToken(token);
    }

    @PatchMapping("/restart")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void restartPassword(@RequestParam String newPassword) {

        userService.restartPassword(newPassword, userService.getCurrentUser().getPassword());
    }

}
