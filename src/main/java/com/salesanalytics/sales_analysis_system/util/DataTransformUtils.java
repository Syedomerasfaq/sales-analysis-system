package com.salesanalytics.sales_analysis_system.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTransformUtils {

    public static Map<String, BigDecimal> convertToMap(List<Object[]> resultList) {
        Map<String, BigDecimal> map = new HashMap<>();
        for (Object[] row : resultList) {
            if (row.length >= 2 && row[0] instanceof String && row[1] instanceof BigDecimal) {
                map.put((String) row[0], (BigDecimal) row[1]);
            }
        }
        return map;
    }
}
