package dev.matheuscruz.infra.persistence;

import dev.matheuscruz.domain.Outflow;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OutflowRepository implements PanacheRepository<Outflow> {
}
