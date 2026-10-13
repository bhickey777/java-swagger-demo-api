package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Represents a product")
public record Product(
    @Schema(description = "Unique product ID", example = "1") Long id,
    @Schema(description = "Product name", example = "Laptop") String name,
    @Schema(description = "Product price", example = "999.99") double price
) {}
