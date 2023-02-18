package com.radzik.michal.shop.user.controller;


import com.radzik.michal.shop.common.dto.UserDto;
import com.radzik.michal.shop.user.mapper.HistoryMapper;
import com.radzik.michal.shop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final UserRepository userRepository;

    private final HistoryMapper historyMapper;

    @GetMapping("/{id}")
    public Page<UserDto> getUserHistory(@PathVariable Long id, @RequestParam int page, @RequestParam int size) {
        return userRepository.findRevisions(id, PageRequest.of(page, size)).map(historyMapper::toUserDto);
    }
}
