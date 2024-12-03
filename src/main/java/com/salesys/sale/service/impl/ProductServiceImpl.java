package com.salesys.sale.service.impl;

import com.salesys.sale.mapper.DataMapper;
import com.salesys.sale.model.Dto.ProductDto;
import com.salesys.sale.model.Product;
import com.salesys.sale.model.request.ProductRequest;
import com.salesys.sale.repository.ProductRepository;
import com.salesys.sale.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DataMapper dataMapper;

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ArrayList<>(products)
                .stream()
                .map(dataMapper::toProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> getActiveProducts() {
        List<Product> products = productRepository.findByIsDeletedIsFalse();
        return new ArrayList<>(products)
                .stream()
                .map(dataMapper::toProductDto)
                .toList();
    }

    @Override
    public Product addNewProduct(ProductRequest productRequest) {
        return productRepository.save(dataMapper.newProduct(productRequest));
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductRequest productPatch) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        Product result = productRepository.save(dataMapper.updateProduct(product,productPatch));
        return dataMapper.toProductDto(result);
    }

    @Override
    public void deleteProduct(Long productId) {
        if (productRepository.findById(productId).isEmpty()){
            throw new RuntimeException("ProductId not found");
        }
        productRepository.deleteById(productId);
    }
}
