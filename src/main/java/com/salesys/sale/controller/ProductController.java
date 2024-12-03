package com.salesys.sale.controller;

import com.salesys.sale.model.Dto.ProductDto;
import com.salesys.sale.model.Product;
import com.salesys.sale.model.request.ProductRequest;
import com.salesys.sale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController extends BaseResponse{

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(){
        List<ProductDto> data = productService.getAllProducts();
        return buildResponse(data);
    }

    @GetMapping()
    public ResponseEntity<?> getActiveProducts(){
        List<ProductDto> data = productService.getActiveProducts();
        return buildResponse(data);
    }

    @PostMapping()
    public ResponseEntity<?> addNewProduct (@RequestBody ProductRequest productRequest){
        Product data = productService.addNewProduct(productRequest);
        return buildResponse(data);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<?> updateProduct (@PathVariable Long productId, @RequestBody ProductRequest productPatch){
        ProductDto data = productService.updateProduct(productId,productPatch);
        return buildResponse(data);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProductById(@RequestParam Long productId){
        productService.deleteProduct(productId);
        return buildResponse("ProductId " + productId + " is deleted");
    }
}
