package ru.krasnovm.shopAccountingSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.krasnovm.shopAccountingSystem.dto.ProductDTO;
import ru.krasnovm.shopAccountingSystem.entity.Product;
import ru.krasnovm.shopAccountingSystem.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto) {
        return mappingResponseEntity(productService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<Product>> readAll() {
        return mappingResponseEntityList(productService.readAll());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> readByCategoryId(@PathVariable("id") Long id) {
        return mappingResponseEntityList(productService.readByCategoryId(id));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Product>> readByCategoryIdInForm(@RequestParam(value = "categoryId") Long id) {
        if (id == null) {
            id = -1L;
        }
        return mappingResponseEntityList(productService.readByCategoryId(id));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return mappingResponseEntity(productService.update(product));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public HttpStatus delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return HttpStatus.OK;
    }

    private ResponseEntity<Product> mappingResponseEntity(Product product) {
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    private ResponseEntity<List<Product>> mappingResponseEntityList(List<Product> products) {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
