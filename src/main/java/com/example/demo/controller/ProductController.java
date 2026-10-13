package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "Product management API")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Returns a list of available products")
    @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    public List<Product> getAllProducts() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Product found"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Product> getProduct(
        @Parameter(description = "Product ID", example = "1") @PathVariable Long id
    ) {
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a product")
    @ApiResponse(responseCode = "201", description = "Product created")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = service.create(product.name(), product.price());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Demonstrates documenting a secured endpoint without actually enforcing JWT validation.
    @GetMapping("/secured-example")
    @Operation(summary = "Example endpoint documented as requiring a bearer token")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponse(responseCode = "200", description = "Example response")
    public String securedExample() {
        return "OpenAPI security documentation example (not actually secured)";
    }
}
