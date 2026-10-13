package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {
    private final Map<Long, Product> products = new LinkedHashMap<>();
    private long nextId = 1;

    public ProductService() {
        create("Laptop", 999.99);
        create("Mouse", 29.99);
    }

    public synchronized List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public synchronized Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public synchronized Product create(String name, double price) {
        Product product = new Product(nextId++, name, price);
        products.put(product.id(), product);
        return product;
    }
}
