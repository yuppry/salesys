package com.salesys.sale.service;

import com.salesys.sale.model.Dto.SaleDto;
import com.salesys.sale.model.Sale;
import com.salesys.sale.model.request.SaleRequest;

import java.util.List;

public interface SaleService {

    List<SaleDto> getAllSale();
    SaleDto createNewSale(SaleRequest saleRequest);
    SaleDto updateSale(Long saleId, SaleRequest saleRequest);
    void deleteSaleById (Long saleId);
}
