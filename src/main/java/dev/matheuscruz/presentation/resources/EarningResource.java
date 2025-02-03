package dev.matheuscruz.presentation.resources;

import dev.matheuscruz.domain.Earning;
import dev.matheuscruz.infra.persistence.EarningRepository;
import dev.matheuscruz.presentation.resources.data.CreateEarningRequest;
import dev.matheuscruz.presentation.resources.data.ErrorResponse;
import dev.matheuscruz.presentation.resources.data.QueryOutflowByYearMonth;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.panache.common.Parameters;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.DateTimeException;
import java.util.List;

@Path("/earnings")
public class EarningResource {

    @Inject
    EarningRepository earningRepository;

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addEarning(CreateEarningRequest request) {

        Earning earning = new Earning(request.amount(), request.yearMonth());

        earningRepository.persist(earning);

        return Response.status(HttpResponseStatus.CREATED.code())
                .entity(request)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response query(QueryOutflowByYearMonth query) {

        try {
            List<Earning> list = earningRepository.find("date = :yearMonth", Parameters.with(
                    "yearMonth", query.yearMonth()
            )).list();
            return Response.ok(list).build();
        } catch (
                DateTimeException e) {
            return Response.status(HttpResponseStatus.BAD_REQUEST.code())
                    .entity(ErrorResponse.of(
                            "Invalid year or month"
                    )).build();
        }
    }
}
