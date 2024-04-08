package ru.krasnovm.shopAccountingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krasnovm.shopAccountingSystem.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
