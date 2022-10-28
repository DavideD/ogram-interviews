package co.ogram.domain.interview

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
internal class InterviewResource {
    @Inject @field:Default lateinit var interviewService: InterviewService

    @POST
    @Consumes(APPLICATION_JSON)
    @Operation(summary = "creates an interview with questions in the database")
    fun createInterview(@Valid @NotNull(message = "Interview's data is required") interview: InterviewCreateRequest): Uni<Response> {
        return this.interviewService.create(interview).map {
            Response.status(Response.Status.CREATED).entity(it).build()
        }
    }
}