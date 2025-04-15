package com.salesanalytics.sales_analysis_system.controller;

import com.salesanalytics.sales_analysis_system.service.DataLoadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Data Refresh", description = "Endpoints to refresh sales data from CSV")
public class RefreshDataController {

    public DataLoadService dataLoadService;

    @Autowired
    public RefreshDataController(DataLoadService dataLoadService) {
        this.dataLoadService = dataLoadService;
    }

    @PostMapping(value = "/refresh-data", consumes = "multipart/form-data")
    @Operation(
            summary = "Refresh sales data from the uploaded CSV file",
            description = "This endpoint allows uploading a CSV file to refresh the sales data. The CSV file should contain the latest data for processing."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data refresh completed successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid CSV file format or bad request"),
            @ApiResponse(responseCode = "500", description = "Error during data refresh, internal server error")
    })
    public ResponseEntity<String> refreshData(@Parameter(description = "CSV file to upload", required = true) @RequestPart("file") MultipartFile file) {
        try {
            dataLoadService.loadCSVData(file.getInputStream());
            return ResponseEntity.ok("Data refresh completed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during data refresh: " + e.getMessage());
        }
    }
}
