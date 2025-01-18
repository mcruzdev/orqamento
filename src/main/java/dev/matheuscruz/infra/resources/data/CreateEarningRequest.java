package dev.matheuscruz.infra.resources.data;

import java.math.BigDecimal;
import java.time.YearMonth;

public record CreateEarningRequest(YearMonth yearMonth, BigDecimal amount) {
}
