package dev.matheuscruz.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class GoalItem {

    @Enumerated(value = EnumType.STRING)
    OutflowType type;
    Integer percent;

    protected GoalItem() {
    }

    public GoalItem(OutflowType type, Integer percent) {
        this.type = type;
        this.percent = percent;
    }

    public OutflowType getType() {
        return type;
    }

    public Integer getPercent() {
        return percent;
    }
}
