package dev.matheuscruz.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.YearMonth;
import java.util.UUID;

@Entity
@Table(name = "earnings")
public class Earning {

    @Id
    private String id;
    private BigDecimal amount;
    private YearMonth date;
    private Instant createAt;

    protected Earning() {
    }

    public Earning(BigDecimal amount, YearMonth date) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.date = date;
        this.createAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public YearMonth getDate() {
        return date;
    }

    public Instant getCreateAt() {
        return createAt;
    }
}
