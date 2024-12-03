package com.salesys.sale.mapper;

import com.salesys.sale.model.Customer;
import com.salesys.sale.model.Dto.CustomerDto;
import com.salesys.sale.model.Dto.ProductDto;
import com.salesys.sale.model.Dto.SaleDetailDto;
import com.salesys.sale.model.Dto.SaleDto;
import com.salesys.sale.model.Product;
import com.salesys.sale.model.Sale;
import com.salesys.sale.model.SaleDetail;
import com.salesys.sale.model.request.CustomerRequest;
import com.salesys.sale.model.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataMapperImpl implements DataMapper{

    @Override
    public Customer newCustomer(CustomerRequest customerRequest) {
        if (customerRequest == null){
            return null;
        }
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setAddress(customerRequest.getAddress());
        customer.setCreatedDate(getTimestamp());
        customer.setDeleted(false);

        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer, CustomerRequest customerPatch) {
        if (customerPatch == null){
            return null;
        }
        if (customerPatch.getFirstName() != null) {
            customer.setFirstName(customerPatch.getFirstName());
        }
        if (customerPatch.getLastName() != null){
            customer.setLastName(customerPatch.getLastName());
        }
        if (customerPatch.getEmail() != null){
            customer.setEmail(customerPatch.getEmail());
        }
        if (customerPatch.getPhoneNumber() != null){
            customer.setPhoneNumber(customerPatch.getPhoneNumber());
        }
        if (customerPatch.getAddress() != null){
            customer.setAddress(customerPatch.getAddress());
        }
        customer.setModifiedDate(getTimestamp());
        return customer;
    }

    @Override
    public Product newProduct(ProductRequest productRequest) {
        if (productRequest == null){
            return null;
        }
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setCreatedDate(getTimestamp());

        return product;
    }

    @Override
    public Product updateProduct(Product product, ProductRequest productPatch) {
        if (productPatch == null){
            return null;
        }
        if (productPatch.getProductName() != null){
            product.setProductName(productPatch.getProductName());
        }
        if (productPatch.getCategory() != null){
            product.setCategory(productPatch.getCategory());
        }
        if (productPatch.getPrice() != null){
            product.setPrice(productPatch.getPrice());
        }
        if (productPatch.getQuantity() >= 0){
            product.setQuantity(productPatch.getQuantity());
        }

        product.setModifiedDate(getTimestamp());
        return product;
    }

    @Override
    public Sale newSale(Customer customer, List<SaleDetail> saleDetail, Double amount) {
        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setSaleDetails(saleDetail);
        sale.setAmount(amount);
        sale.setSaleDate(getTimestamp());
        sale.setDeleted(false);

        return sale;
    }

    @Override
    public SaleDto toSaleDto(Sale sale) {
        SaleDto saleDto = new SaleDto();
        saleDto.setSaleId(sale.getSaleId());
        saleDto.setCustomerId(sale.getCustomer().getCustomerId());
        saleDto.setAmount(sale.getAmount());

        List<SaleDetailDto> saleDetails = sale.getSaleDetails()
                .stream()
                .map(saleDetail -> {
                    SaleDetailDto detailDto = new SaleDetailDto();
                    detailDto.setProductId(saleDetail.getProduct().getProductId());
                    detailDto.setProductName(saleDetail.getProduct().getProductName());
                    detailDto.setQuantity(saleDetail.getQuantity());
                    detailDto.setSubtotal(saleDetail.getSubtotal());
                    return detailDto;
                })
                .toList();
        saleDto.setSaleDetails(saleDetails);

        return saleDto;
    }

    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getFirstName());
        customerDto.setEmail(customer.getEmail());
        customer.setAddress(customer.getAddress());
        customerDto.setCreatedDate(customer.getCreatedDate());
        customerDto.setModifiedDate(customer.getModifiedDate());
        customerDto.setDeleted(customer.isDeleted());

        return customerDto;
    }

    @Override
    public ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setCategory(product.getCategory());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setCreatedDate(product.getCreatedDate());
        productDto.setModifiedDate(product.getModifiedDate());
        productDto.setDeleted(product.isDeleted());
        return productDto;
    }


    private Timestamp getTimestamp(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
