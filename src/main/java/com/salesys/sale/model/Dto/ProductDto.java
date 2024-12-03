package com.salesys.sale.model.Dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductDto {
    private Long productId;
    private String productName;
    private String category;
    private Double price;
    private Integer quantity;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private boolean isDeleted;
}
