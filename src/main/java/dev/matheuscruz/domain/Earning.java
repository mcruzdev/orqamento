package dev.matheuscruz.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "earnings")
public class Earning {

   @Id
   private String id;
   private BigDecimal amount;
   @Column(columnDefinition = "date")
   private LocalDate date;
   @Column(name = "created_at")
   private Instant createdAt;

   protected Earning() {
   }

   public Earning(BigDecimal amount, LocalDate date) {
      this.id = UUID.randomUUID().toString();
      this.amount = Objects.requireNonNull(amount, "The amount must not be null");
      this.date = Objects.requireNonNull(date, "The date must not be null");
      this.createdAt = Instant.now();
   }

   public String getId() {
      return id;
   }

   public BigDecimal getAmount() {
      return amount;
   }

   public LocalDate getDate() {
      return date;
   }

   public Instant getCreatedAt() {
      return createdAt;
   }
}
