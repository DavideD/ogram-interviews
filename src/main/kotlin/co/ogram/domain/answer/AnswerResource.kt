package co.ogram.domain.answer

import javax.ws.rs.core.Response
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import io.smallrye.mutiny.Uni
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.annotation.security.RolesAllowed
import javax.validation.Valid
import javax.validation.constraints.NotNull
import org.eclipse.microprofile.openapi.annotations.Operation
import javax.annotation.security.PermitAll
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Path("/answers")
internal class AnswerResource {
    @Inject @field:Default lateinit var answerService: AnswerService

    @GET
    @Operation(summary = "returns answer for given id")
    @PermitAll
    @Path("/{answerId}")
    fun getAnswer(@PathParam("answerId") answerId: Long): Uni<Response> {
        return this.answerService.get(answerId).map {
            Response.status(Response.Status.OK).entity(AnswerResponse.build(it)).build()
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Operation(summary = "creates answer for question in the database")
    @RolesAllowed("SP")
    fun createAnswer(
        @Valid @NotNull(message = "Answer's data is required") answer: AnswerCreateRequest,
        @Context sec: SecurityContext,
    ): Uni<Response> {
        val spId = sec.userPrincipal.name.toLong()
        return this.answerService.create(answer, spId).map {
            Response.status(Response.Status.CREATED).entity(AnswerResponse.build(it)).build()
        }
    }
}