package com.salesys.sale.service.impl;

import com.opencsv.CSVWriter;
import com.salesys.sale.model.Sale;
import com.salesys.sale.model.SaleDetail;
import com.salesys.sale.repository.SaleRepository;
import com.salesys.sale.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    private static final String reportName = "Daily_Sale_Report_";
    private static final String csvExtension = ".csv";
    private static final String Header = "Daily Sale Report of ";
    private static final String[] CSV_Header = {"No", "Customer Name", "Product Name", "Category","Quantity", "Subtotal"};

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public String generateReport(LocalDate date) {

        List<Sale> sales = saleRepository.findBySaleDate(date);
        if (sales.isEmpty()){
            throw new RuntimeException("Sale data not found from selected date");
        }

        String dateFormat = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String fileName = reportName + date + csvExtension;
        String[] headerWithDate = {Header+dateFormat};


        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))){
            writer.writeNext(headerWithDate);
            writer.writeNext(new String[]{""});
            writer.writeNext(CSV_Header);

            int sequenceNo = 1;
            for (Sale sale : sales) {
                for (SaleDetail detail : sale.getSaleDetails()) {
                    String[] rowData = {
                            String.valueOf(sequenceNo++),
                            sale.getCustomer().getFirstName() + " " + sale.getCustomer().getLastName(),
                            detail.getProduct().getProductName(),
                            detail.getProduct().getCategory(),
                            String.valueOf(detail.getQuantity()),
                            String.valueOf(detail.getSubtotal())
                    };
                    writer.writeNext(rowData);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Generating the report with Error", e);
        }

        log.info("******* Daily report file name : "+fileName);

        return fileName;
    }
}
