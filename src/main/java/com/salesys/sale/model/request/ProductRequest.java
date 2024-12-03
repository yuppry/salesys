package com.salesys.sale.model.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    private String productName;
    private String category;
    private Double price;
    private int quantity;
}
