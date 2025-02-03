package dev.matheuscruz.presentation.resources.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateEarningRequest(LocalDate date, BigDecimal amount) {
}
