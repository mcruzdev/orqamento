package dev.matheuscruz.presentation.resources.data;

import java.math.BigDecimal;
import java.time.YearMonth;

public record CreateEarningRequest(YearMonth yearMonth, BigDecimal amount) {
}
