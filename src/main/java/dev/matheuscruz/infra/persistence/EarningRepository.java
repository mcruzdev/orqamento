package dev.matheuscruz.infra.persistence;

import dev.matheuscruz.domain.Earning;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EarningRepository implements PanacheRepository<Earning> {
}
