package dev.matheuscruz.presentation.resources.data;

import dev.matheuscruz.domain.OutflowType;

import java.util.List;

public record AddGoalsRequest(
        List<GoalRequest> goals
) {

    public record GoalRequest(OutflowType type, Integer percent) {
    }
}

