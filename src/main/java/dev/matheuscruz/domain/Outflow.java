package dev.matheuscruz.domain;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
   @ElementCollection
   private List<String> tags = new ArrayList<>();

   protected Outflow() {
   }

   public Outflow(OutflowType type, BigDecimal amount, String name, List<String> tags) {
      this.id = UUID.randomUUID().toString();
      this.type = type;
      this.amount = Objects.requireNonNull(amount, "the 'amount' must not be null");
      this.name = Objects.requireNonNull(name, "the 'name' must not be null");
      this.name = Objects.requireNonNull(name, "the 'name' must not be null");
      this.date = LocalDate.now(ZoneId.of("UTC"));
      this.createdAt = Instant.now();
      this.tags = Objects.requireNonNullElse(tags, List.of());
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

   public List<String> getTags() {
      return Collections.unmodifiableList(tags);
   }
}
