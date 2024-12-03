package com.salesys.sale.model.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SaleDetailDto {

    private Long productId;
    private String productName;
    private int quantity;
    private Double subtotal;
}
