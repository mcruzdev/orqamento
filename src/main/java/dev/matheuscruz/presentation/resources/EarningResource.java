package dev.matheuscruz.presentation.resources;

import dev.matheuscruz.domain.Earning;
import dev.matheuscruz.infra.persistence.EarningRepository;
import dev.matheuscruz.presentation.resources.data.CreateEarningRequest;
import dev.matheuscruz.presentation.resources.data.ErrorResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.panache.common.Parameters;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@Path("/earnings")
public class EarningResource {

   @Inject
   EarningRepository earningRepository;

   @PUT
   @Produces(MediaType.APPLICATION_JSON)
   @Transactional
   public Response addEarning(CreateEarningRequest request) {

      Earning earning = new Earning(request.amount(), request.date());

      earningRepository.persist(earning);

      return Response.status(HttpResponseStatus.CREATED.code()).entity(request).build();
   }

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response query(@QueryParam("period") String period) {

      try {
         YearMonth yearMonth = YearMonth.parse(period);
         List<Earning> list = earningRepository.find("date BETWEEN :start AND :end",
               Parameters.with("start", yearMonth.atDay(1)).and("end", yearMonth.atEndOfMonth())).list();

         BigDecimal total = list.stream().map(Earning::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);

         return Response.ok(Map.of("earnings", list, "totalAmount", total)).build();
      } catch (DateTimeException e) {
         return Response.status(HttpResponseStatus.BAD_REQUEST.code()).entity(ErrorResponse.of("Invalid period"))
               .build();
      }
   }
}
