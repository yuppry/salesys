package com.salesys.sale.model.request;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaleRequest {

    private Long customerId;
    private List<SaleProductRequest> saleProductRequest;
}
