package ru.krasnovm.shopAccountingSystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.krasnovm.shopAccountingSystem.dto.CategoryDTO;
import ru.krasnovm.shopAccountingSystem.entity.Category;
import ru.krasnovm.shopAccountingSystem.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody CategoryDTO dto) {
        return new ResponseEntity<>(categoryService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> readAll() {
        return new ResponseEntity<>(categoryService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return HttpStatus.OK;
    }
}