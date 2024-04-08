package ru.krasnovm.shopAccountingSystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krasnovm.shopAccountingSystem.dto.CategoryDTO;
import ru.krasnovm.shopAccountingSystem.entity.Category;
import ru.krasnovm.shopAccountingSystem.repository.CategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(CategoryDTO dto) {
        return categoryRepository.save(Category.builder()
                .name(dto.getName())
                .build());
    }

    public List<Category> readAll() {
        return categoryRepository.findAll();
    }

    public Category readById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Category not found: " + id));
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
