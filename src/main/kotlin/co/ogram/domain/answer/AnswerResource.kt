package co.ogram.domain.answer

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import io.smallrye.mutiny.Uni
import javax.enterprise.inject.Default
import javax.inject.Inject
import org.eclipse.microprofile.openapi.annotations.Operation
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.Consumes
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

@Path("/interviews")
internal class AnswerResource {
    @Inject @field:Default lateinit var answerService: AnswerService

    @POST
    @Consumes(APPLICATION_JSON)
    @Operation(summary = "creates answer for question in the database")
    @Path("/answer")
    fun createAnswer(@Valid @NotNull(message = "Answer's data is required") answer: AnswerCreateRequest): Uni<Response> {
        return this.answerService.create(answer).map {
            Response.status(Response.Status.CREATED).entity(it).build()
        }
    }
}