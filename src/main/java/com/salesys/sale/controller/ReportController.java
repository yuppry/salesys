package com.salesys.sale.controller;

import com.salesys.sale.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/report")
public class ReportController extends BaseResponse{

    @Autowired
    private ReportService reportService;

    @GetMapping("/daily")
    public ResponseEntity<?> generateDailyReport(@RequestParam String date){
        LocalDate reportDate = LocalDate.parse(date);
        String reportName = reportService.generateReport(reportDate);
        return buildResponse(reportName);
    }
}
