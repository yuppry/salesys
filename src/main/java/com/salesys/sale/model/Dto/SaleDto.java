package com.salesys.sale.model.Dto;
import lombok.Data;
import java.util.List;

@Data
public class SaleDto {
    private Long saleId;
    private Long customerId;
    private Double amount;
    private List<SaleDetailDto> saleDetails;
}
