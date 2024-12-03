package com.salesys.sale.service;

import com.salesys.sale.model.Dto.ProductDto;
import com.salesys.sale.model.Product;
import com.salesys.sale.model.request.ProductRequest;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();
    List<ProductDto> getActiveProducts();
    Product addNewProduct(ProductRequest productRequest);
    ProductDto updateProduct(Long productId, ProductRequest productPatch);
    void deleteProduct(Long productId);
}
