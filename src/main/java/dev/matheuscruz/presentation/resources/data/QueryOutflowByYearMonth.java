package dev.matheuscruz.presentation.resources.data;

import org.jboss.resteasy.reactive.RestQuery;

import java.time.LocalDate;
import java.time.YearMonth;

public class QueryOutflowByYearMonth {

    @RestQuery("year")
    String year;

    @RestQuery("month")
    String month;

    public LocalDate firstDay() {
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        return YearMonth.of(yearInt, monthInt).atDay(1);
    }

    public LocalDate lastDay() {
        int yearInt = Integer.parseInt(year);
        int monthInt = Integer.parseInt(month);
        return YearMonth.of(yearInt, monthInt).atEndOfMonth();
    }

    public YearMonth yearMonth() {
        return YearMonth.of(Integer.parseInt(year), Integer.parseInt(month));
    }
}
