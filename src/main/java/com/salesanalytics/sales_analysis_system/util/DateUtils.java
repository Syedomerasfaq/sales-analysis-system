package com.salesanalytics.sales_analysis_system.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateUtils {

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for value: '" + dateStr + "'. Use YYYY-MM-DD.");
        }
    }

    public static void validateDates(LocalDate startDate,LocalDate endDate){
        if (startDate.isAfter(endDate)){
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
        if (endDate.isBefore(startDate)){
            throw new IllegalArgumentException("End date must be after or equal to start date.");
        }
    }
}
