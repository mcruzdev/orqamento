package dev.matheuscruz.presentation.resources.data;

import dev.matheuscruz.domain.OutflowType;

import java.math.BigDecimal;
import java.util.List;

public record CreateOutflowRequest(String name, OutflowType type, BigDecimal amount, List<String> tags) {
}
