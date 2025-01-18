package dev.matheuscruz.infra.persistence;

import dev.matheuscruz.domain.Goal;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GoalRepository implements PanacheRepository<Goal> {
}
