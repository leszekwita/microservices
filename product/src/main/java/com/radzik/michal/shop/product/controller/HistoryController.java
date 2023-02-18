package com.radzik.michal.shop.product.controller;

import com.radzik.michal.shop.common.dto.ProductDto;
import com.radzik.michal.shop.product.mapper.HistoryMapper;
import com.radzik.michal.shop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final ProductRepository productRepository;

    private final HistoryMapper mapper;

    @GetMapping("/{id}")
    public Page<ProductDto> getUserHistory(@PathVariable Long id, @RequestParam int page, @RequestParam int size) {
        return productRepository.findRevisions(id, PageRequest.of(page, size)).map(mapper::toProductDto);
    }
}
