package ru.krasnovm.shopAccountingSystem.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private Long amount;
    private Double price;
    private Long categoryId;
}
