package com.salesys.sale.service.impl;

import com.salesys.sale.mapper.DataMapper;
import com.salesys.sale.model.Customer;
import com.salesys.sale.model.Dto.SaleDto;
import com.salesys.sale.model.Product;
import com.salesys.sale.model.Sale;
import com.salesys.sale.model.SaleDetail;
import com.salesys.sale.model.request.SaleProductRequest;
import com.salesys.sale.model.request.SaleRequest;
import com.salesys.sale.repository.CustomerRepository;
import com.salesys.sale.repository.ProductRepository;
import com.salesys.sale.repository.SaleDetailRepository;
import com.salesys.sale.repository.SaleRepository;
import com.salesys.sale.service.SaleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private DataMapper dataMapper;


    @Override
    public List<SaleDto> getAllSale() {
        List<Sale> sales = saleRepository.findAll();
        return new ArrayList<>(sales
                .stream()
                .map(dataMapper::toSaleDto)
                .toList());
    }

    @Override
    public SaleDto createNewSale(SaleRequest saleRequest) {
        Sale sale = new Sale();
        Customer customer = customerRepository.findById(saleRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Date date = new Date();
        Timestamp saveDate = new Timestamp(date.getTime());

        sale.setCustomer(customer);
        sale.setSaleDate(saveDate);
        double totalAmount = 0.0;

        List<SaleDetail> saleDetails = saleRequest.getSaleProductRequest().stream()
                .map(request -> {
                    Product product = productRepository.findById(request.getProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));

                    if (product.getQuantity() < request.getQuantity()) {
                        throw new RuntimeException("Insufficient stock for : " + product.getProductName());
                    }

                    product.setQuantity(product.getQuantity() - request.getQuantity());
                    productRepository.save(product);

                    SaleDetail saleDetail = new SaleDetail();
                    saleDetail.setProduct(product);
                    saleDetail.setQuantity(request.getQuantity());
                    saleDetail.setSubtotal(product.getPrice() * request.getQuantity());
                    saleDetail.setSale(sale);

                    return saleDetail;
                }).toList();
        sale.setSaleDetails(saleDetails);
        sale.setAmount(saleDetails.stream().mapToDouble(SaleDetail::getSubtotal).sum());
        saleRepository.save(sale);
        return dataMapper.toSaleDto(sale);
    }

@Override
public SaleDto updateSale(Long saleId, SaleRequest saleRequest) {

    Sale sale = saleRepository.findById(saleId)
            .orElseThrow(() -> new RuntimeException("Sale not found"));
    Customer customer = customerRepository.findById(saleRequest.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    sale.setCustomer(customer);
    sale.setModifiedDate(new Timestamp(System.currentTimeMillis()));
    double totalAmount = 0.0;
    List<SaleDetail> existingDetails = sale.getSaleDetails();
    List<SaleDetail> updatedDetails = saleRequest.getSaleProductRequest().stream()
            .map(request -> {
                Product product = productRepository.findById(request.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                if (product.getQuantity() < request.getQuantity()) {
                    throw new RuntimeException("Insufficient stock for: " + product.getProductName());
                }

                product.setQuantity(product.getQuantity() - request.getQuantity());
                productRepository.save(product);

                SaleDetail existingDetail = existingDetails.stream()
                        .filter(detail -> detail.getProduct().getProductId().equals(request.getProductId()))
                        .findFirst()
                        .orElse(null);

                if (existingDetail != null) {
                    existingDetail.setQuantity(request.getQuantity());
                    existingDetail.setSubtotal(product.getPrice() * request.getQuantity());
                    return existingDetail;
                } else {
                    SaleDetail newDetail = new SaleDetail();
                    newDetail.setProduct(product);
                    newDetail.setQuantity(request.getQuantity());
                    newDetail.setSubtotal(product.getPrice() * request.getQuantity());
                    newDetail.setSale(sale);
                    return newDetail;
                }
            }).toList();

    totalAmount = updatedDetails.stream().mapToDouble(SaleDetail::getSubtotal).sum();
    existingDetails.clear();
    existingDetails.addAll(updatedDetails);
    sale.setAmount(totalAmount);

    Sale updatedSale = saleRepository.save(sale);
    return dataMapper.toSaleDto(updatedSale);
}

    @Override
    public void deleteSaleById(Long saleId) {
        if (saleRepository.findById(saleId).isEmpty()){
            throw new RuntimeException("SaleId not found");
        }
        saleRepository.deleteById(saleId);
    }
}
