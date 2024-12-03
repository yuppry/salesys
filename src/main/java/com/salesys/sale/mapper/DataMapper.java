package com.salesys.sale.mapper;

import com.salesys.sale.model.Customer;
import com.salesys.sale.model.Dto.CustomerDto;
import com.salesys.sale.model.Dto.ProductDto;
import com.salesys.sale.model.Dto.SaleDto;
import com.salesys.sale.model.Product;
import com.salesys.sale.model.Sale;
import com.salesys.sale.model.SaleDetail;
import com.salesys.sale.model.request.CustomerRequest;
import com.salesys.sale.model.request.ProductRequest;

import java.util.List;

public interface DataMapper {

    Customer newCustomer (CustomerRequest customerRequest);
    Customer updateCustomer (Customer customer, CustomerRequest customerPatch);
    Product newProduct(ProductRequest productRequest);
    Product updateProduct (Product product, ProductRequest productPatch);
    Sale newSale(Customer customer, List<SaleDetail> saleDetail, Double amount);
    SaleDto toSaleDto(Sale sale);
    CustomerDto toCustomerDto(Customer customer);
    ProductDto toProductDto(Product product);
}
