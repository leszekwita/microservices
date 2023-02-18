package com.radzik.michal.shop.common.dto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Product {

    private final Long id;

    private final List<String> categories;

    public Product(Long id, List<String> categories) {
        this.id = id;
        //this.categories = List.copyOf(categories);
        this.categories = categories.stream().collect(Collectors.toUnmodifiableList());
    }

    public Long getId() {
        return id;
    }

    public List<String> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(categories, product.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categories);
    }
}
