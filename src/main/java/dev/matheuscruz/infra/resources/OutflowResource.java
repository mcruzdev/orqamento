package dev.matheuscruz.infra.resources;

import dev.matheuscruz.domain.Outflow;
import dev.matheuscruz.infra.persistence.OutflowRepository;
import dev.matheuscruz.infra.resources.data.CreateOutflowRequest;
import dev.matheuscruz.infra.resources.data.ErrorResponse;
import dev.matheuscruz.infra.resources.data.QueryOutflowByYearMonth;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.panache.common.Parameters;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.DateTimeException;
import java.util.List;

@Path("/outflows")
public class OutflowResource {

    @Inject
    OutflowRepository outflowRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CreateOutflowRequest req) {
        Outflow outflow = new Outflow(req.type(), req.amount(), req.name());

        outflowRepository.persist(outflow);

        return Response.status(HttpResponseStatus.CREATED.code()).entity(outflow).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response query(QueryOutflowByYearMonth query) {

        try {
            List<Outflow> outflows = outflowRepository.find(
                    "date BETWEEN :start AND :end",
                    Parameters
                            .with("start", query.firstDay())
                            .and("end", query.lastDay())
            ).list();

            return Response.status(HttpResponseStatus.OK.code())
                    .entity(outflows)
                    .build();

        } catch (DateTimeException e) {
            return Response.status(HttpResponseStatus.BAD_REQUEST.code())
                    .entity(ErrorResponse.of(
                            "Invalid year or month"
                    )).build();
        }
    }
}
