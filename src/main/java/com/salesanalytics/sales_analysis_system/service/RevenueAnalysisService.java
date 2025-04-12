package com.salesanalytics.sales_analysis_system.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface RevenueAnalysisService {
    BigDecimal getTotalRevenue(LocalDate startDate, LocalDate endDate);
    List<Object[]> getRevenueByProduct(LocalDate startDate, LocalDate endDate);
    List<Object[]> getRevenueByCategory(LocalDate startDate, LocalDate endDate);
    List<Object[]> getRevenueByRegion(LocalDate startDate, LocalDate endDate);
}
