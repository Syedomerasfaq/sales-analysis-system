package com.salesanalytics.sales_analysis_system.controller;

import com.salesanalytics.sales_analysis_system.service.DataLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;

@RestController
@RequestMapping("/api/v1")
public class RefreshDataController {

    public DataLoadService dataLoadService;

    @Autowired
    public RefreshDataController(DataLoadService dataLoadService) {
        this.dataLoadService = dataLoadService;
    }
    @GetMapping("/refresh-data")
    public ResponseEntity<String> refreshData() {
        try {
            ClassPathResource resource = new ClassPathResource("test.csv");
            try (FileInputStream fileInputStream = new FileInputStream(resource.getFile())) {
                dataLoadService.loadCSVData(fileInputStream);
            }
            return ResponseEntity.ok("Data refresh completed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during data refresh");
        }
    }
}
