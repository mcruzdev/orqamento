package dev.matheuscruz.infra.resources.data;

import dev.matheuscruz.domain.OutflowType;

import java.math.BigDecimal;

public record CreateOutflowRequest(
        String name,
        OutflowType type,
        BigDecimal amount
) {
}
