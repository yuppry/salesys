package com.salesys.sale.controller;

import com.salesys.sale.exception.ErrorResponse;
import com.salesys.sale.model.Dto.ProductDto;
import com.salesys.sale.model.Dto.SaleDto;
import com.salesys.sale.model.request.ProductRequest;
import com.salesys.sale.model.request.SaleRequest;
import com.salesys.sale.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class SaleController extends BaseResponse{

    @Autowired
    private SaleService saleService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllSale(){
        List<SaleDto> data = saleService.getAllSale();
        return buildResponse(data);
    }

    @PostMapping()
    public ResponseEntity<?> createSale(@RequestBody SaleRequest saleRequest){
        SaleDto data = saleService.createNewSale(saleRequest);
        return buildResponse(data);
    }

    @PatchMapping("/{saleId}")
    public ResponseEntity<?> updateProduct (@PathVariable Long saleId, @RequestBody SaleRequest salePatch){
        SaleDto data = saleService.updateSale(saleId,salePatch);
        return buildResponse(data);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProductById(@RequestParam Long saleId){
        saleService.deleteSaleById(saleId);
        return buildResponse("SaleId " + saleId + " is deleted");
    }

}
