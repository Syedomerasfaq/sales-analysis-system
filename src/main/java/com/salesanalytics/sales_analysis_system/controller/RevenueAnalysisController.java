package com.salesanalytics.sales_analysis_system.controller;

import com.salesanalytics.sales_analysis_system.service.RevenueAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/analysis")
public class RevenueAnalysisController {

    @Autowired
    private RevenueAnalysisService revenueAnalysisService;

    @GetMapping("/total-revenue")
    public ResponseEntity<BigDecimal> getTotalRevenue(@RequestParam String startDate, @RequestParam String endDate) {
        BigDecimal total = revenueAnalysisService.getTotalRevenue(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(total);
    }

    @GetMapping("/revenue-by-product")
    public ResponseEntity<Map<String, BigDecimal>> getRevenueByProduct(@RequestParam String startDate, @RequestParam String endDate) {
        List<Object[]> results = revenueAnalysisService.getRevenueByProduct(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(convertToMap(results));
    }

    @GetMapping("/revenue-by-category")
    public ResponseEntity<Map<String, BigDecimal>> getRevenueByCategory(@RequestParam String startDate, @RequestParam String endDate) {
        List<Object[]> results = revenueAnalysisService.getRevenueByCategory(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(convertToMap(results));
    }

    @GetMapping("/revenue-by-region")
    public ResponseEntity<Map<String, BigDecimal>> getRevenueByRegion(@RequestParam String startDate, @RequestParam String endDate) {
        List<Object[]> results = revenueAnalysisService.getRevenueByRegion(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(convertToMap(results));
    }

    private Map<String, BigDecimal> convertToMap(List<Object[]> resultList) {
        Map<String, BigDecimal> map = new HashMap<>();
        for (Object[] row : resultList) {
            map.put((String) row[0], (BigDecimal) row[1]);
        }
        return map;
    }
}
