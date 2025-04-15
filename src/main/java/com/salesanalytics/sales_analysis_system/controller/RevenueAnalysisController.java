package com.salesanalytics.sales_analysis_system.controller;

import com.salesanalytics.sales_analysis_system.service.RevenueAnalysisService;
import com.salesanalytics.sales_analysis_system.util.DataTransformUtils;
import com.salesanalytics.sales_analysis_system.util.DateUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/analysis")
@Tag(name = "Revenue Analysis", description = "Endpoints for analyzing revenue by different dimensions")
public class RevenueAnalysisController {

    private static final Logger log = LoggerFactory.getLogger(RevenueAnalysisController.class);

    @Autowired
    private RevenueAnalysisService revenueAnalysisService;

    @GetMapping("/total-revenue")
    @Operation(summary = "Get total revenue between two dates",
            description = "This endpoint returns the total revenue generated between the specified start and end dates.",
            parameters = {
                    @Parameter(name = "startDate", description = "Start date of the period (yyyy-MM-dd)", required = true),
                    @Parameter(name = "endDate", description = "End date of the period (yyyy-MM-dd)", required = true)
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total revenue calculated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid date format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<BigDecimal> getTotalRevenue(@RequestParam String startDate, @RequestParam String endDate) {

        log.info("Received request for total revenue from {} to {}", startDate, endDate);
        try {
            LocalDate start = DateUtils.parseDate(startDate);
            LocalDate end = DateUtils.parseDate(endDate);
            BigDecimal total = revenueAnalysisService.getTotalRevenue(start, end);
            log.info("Total revenue calculated: {}", total);
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            log.error("Error calculating total revenue from {} to {}", startDate, endDate, e);
            return ResponseEntity.status(500).body(BigDecimal.ZERO);
        }
    }

    @GetMapping("/revenue-by-product")
    @Operation(summary = "Get revenue by product between two dates",
            description = "This endpoint provides revenue breakdown by product for the specified date range.",
            parameters = {
                    @Parameter(name = "startDate", description = "Start date of the period (yyyy-MM-dd)", required = true),
                    @Parameter(name = "endDate", description = "End date of the period (yyyy-MM-dd)", required = true)
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Revenue by product fetched successfully"),
            @ApiResponse(responseCode = "204", description = "No data found for the given date range"),
            @ApiResponse(responseCode = "400", description = "Invalid date format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, BigDecimal>> getRevenueByProduct(@RequestParam String startDate, @RequestParam String endDate) {
        log.info("Received request for revenue by product from {} to {}", startDate, endDate);
        LocalDate start = DateUtils.parseDate(startDate);
        LocalDate end = DateUtils.parseDate(endDate);
        List<Object[]> results = revenueAnalysisService.getRevenueByProduct(start, end);
        if (results == null || results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        log.info("Revenue by product fetched successfully");
        return ResponseEntity.ok(DataTransformUtils.convertToMap(results));
    }

    @GetMapping("/revenue-by-category")
    @Operation(summary = "Get revenue by category between two dates",
            description = "This endpoint provides revenue breakdown by product category for the specified date range.",
            parameters = {
                    @Parameter(name = "startDate", description = "Start date of the period (yyyy-MM-dd)", required = true),
                    @Parameter(name = "endDate", description = "End date of the period (yyyy-MM-dd)", required = true)
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Revenue by category fetched successfully"),
            @ApiResponse(responseCode = "204", description = "No data found for the given date range"),
            @ApiResponse(responseCode = "400", description = "Invalid date format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, BigDecimal>> getRevenueByCategory(@RequestParam String startDate, @RequestParam String endDate) {
        log.info("Received request for revenue by category from {} to {}", startDate, endDate);
        LocalDate start = DateUtils.parseDate(startDate);
        LocalDate end = DateUtils.parseDate(endDate);
        List<Object[]> results = revenueAnalysisService.getRevenueByCategory(start, end);
        if (results == null || results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        log.info("Revenue by category fetched successfully");
        return ResponseEntity.ok(DataTransformUtils.convertToMap(results));
    }

    @GetMapping("/revenue-by-region")
    @Operation(summary = "Get revenue by region between two dates",
            description = "This endpoint provides revenue breakdown by region for the specified date range.",
            parameters = {
                    @Parameter(name = "startDate", description = "Start date of the period (yyyy-MM-dd)", required = true),
                    @Parameter(name = "endDate", description = "End date of the period (yyyy-MM-dd)", required = true)
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Revenue by region fetched successfully"),
            @ApiResponse(responseCode = "204", description = "No data found for the given date range"),
            @ApiResponse(responseCode = "400", description = "Invalid date format"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Map<String, BigDecimal>> getRevenueByRegion(@RequestParam String startDate, @RequestParam String endDate) {
        log.info("Received request for revenue by region from {} to {}", startDate, endDate);
        LocalDate start = DateUtils.parseDate(startDate);
        LocalDate end = DateUtils.parseDate(endDate);
        List<Object[]> results = revenueAnalysisService.getRevenueByRegion(start, end);
        if (results == null || results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        log.info("Revenue by region fetched successfully");
        return ResponseEntity.ok(DataTransformUtils.convertToMap(results));
    }
}
