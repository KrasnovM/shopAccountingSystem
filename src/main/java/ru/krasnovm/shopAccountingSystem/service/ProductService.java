package ru.krasnovm.shopAccountingSystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krasnovm.shopAccountingSystem.dto.ProductDTO;
import ru.krasnovm.shopAccountingSystem.entity.Product;
import ru.krasnovm.shopAccountingSystem.repository.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Product create(ProductDTO dto) {
        return productRepository.save(Product.builder()
                .name(dto.getName())
                .amount(dto.getAmount())
                .price(dto.getPrice())
                .category(categoryService.readById(dto.getCategoryId()))
                .build());
    }

    public List<Product> readAll() {
        return productRepository.findAll();
    }

    public List<Product> readByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
