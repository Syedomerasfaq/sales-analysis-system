package com.salesanalytics.sales_analysis_system.service.Impl;

import com.salesanalytics.sales_analysis_system.repository.OrderDetailRepository;
import com.salesanalytics.sales_analysis_system.service.RevenueAnalysisService;
import com.salesanalytics.sales_analysis_system.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RevenueAnalysisServiceImpl implements RevenueAnalysisService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public BigDecimal getTotalRevenue(LocalDate startDate, LocalDate endDate) {
        DateUtils.validateDates(startDate,endDate);
        return orderDetailRepository.findTotalRevenue(startDate, endDate);
    }

    @Override
    public List<Object[]> getRevenueByProduct(LocalDate startDate, LocalDate endDate) {
        DateUtils.validateDates(startDate,endDate);
        return orderDetailRepository.findRevenueByProduct(startDate, endDate);
    }

    @Override
    public List<Object[]> getRevenueByCategory(LocalDate startDate, LocalDate endDate) {
        DateUtils.validateDates(startDate,endDate);
        return orderDetailRepository.findRevenueByCategory(startDate, endDate);
    }

    @Override
    public List<Object[]> getRevenueByRegion(LocalDate startDate, LocalDate endDate) {
        DateUtils.validateDates(startDate,endDate);
        return orderDetailRepository.findRevenueByRegion(startDate, endDate);
    }
}
