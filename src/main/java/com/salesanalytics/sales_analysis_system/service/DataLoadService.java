package com.salesanalytics.sales_analysis_system.service;
import java.io.InputStream;

public interface DataLoadService {

    public void loadCSVData(InputStream inputStream) throws Exception;
}
