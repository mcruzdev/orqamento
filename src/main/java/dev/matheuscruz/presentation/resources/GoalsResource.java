package dev.matheuscruz.presentation.resources;

import dev.matheuscruz.domain.Goal;
import dev.matheuscruz.domain.GoalItem;
import dev.matheuscruz.infra.persistence.GoalRepository;
import dev.matheuscruz.presentation.resources.data.AddGoalsRequest;
import dev.matheuscruz.presentation.resources.data.ErrorResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/goals")
public class GoalsResource {

    @Inject
    GoalRepository goalRepository;

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(AddGoalsRequest request) {

        Optional<Integer> sum = request.goals()
                .stream()
                .map(goal -> Optional.of(goal.percent()).orElse(0))
                .reduce(Integer::sum);

        if (sum.isEmpty() || sum.get() != 100) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ErrorResponse.of(
                            "invalid goal percents"
                    ))
                    .build();
        }

        Goal goal = new Goal();
        request.goals().forEach(goalRequest -> {
            goal.addGoal(new GoalItem(
                    goalRequest.type(),
                    goalRequest.percent()
            ));
        });

        goalRepository.persist(goal);

        return Response.status(HttpResponseStatus.OK.code()).build();
    }
}
