package dev.matheuscruz.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Entity
@Table(name = "outflows")
public class Outflow {

    @Id
    private String id;
    @Column
    @Enumerated(value = EnumType.STRING)
    private OutflowType type;
    private BigDecimal amount;
    private String name;
    private LocalDate date;
    private Instant createdAt;

    protected Outflow() {
    }

    public Outflow(OutflowType type, BigDecimal amount, String name) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.amount = amount;
        this.name = name;
        this.date = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public OutflowType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
