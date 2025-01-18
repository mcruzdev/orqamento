package dev.matheuscruz.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    String id;

    @ElementCollection
    List<GoalItem> goals = new ArrayList<>();

    public Goal() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void addGoal(GoalItem goalItem) {
        Optional<Integer> sum = this.goals.stream()
                .map(item -> Optional.ofNullable(item.getPercent()).orElse(0))
                .reduce(Integer::sum);
        Integer finalSum = sum.orElse(0);
        Set<OutflowType> types = this.goals.stream().map(GoalItem::getType).collect(Collectors.toSet());

        if (types.contains(goalItem.getType()) || finalSum + goalItem.getPercent() > 100) {
            throw new IllegalStateException("Invalid goals percent");
        }
        this.goals.add(goalItem);

    }
}
