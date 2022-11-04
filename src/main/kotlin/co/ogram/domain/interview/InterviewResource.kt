package co.ogram.domain.interview

import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import io.smallrye.mutiny.Uni
import javax.enterprise.inject.Default
import javax.inject.Inject
import org.eclipse.microprofile.openapi.annotations.Operation
import javax.annotation.security.RolesAllowed
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.Consumes
import javax.ws.rs.core.Response
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext
import javax.annotation.security.DenyAll

@Path("/interviews")
internal class InterviewResource {
    @Inject @field:Default lateinit var interviewService: InterviewService

    @POST
    @Consumes(APPLICATION_JSON)
    @Operation(summary = "creates an interview with questions in the database")
    @DenyAll
    @RolesAllowed("CLIENT_OWNER", "CLIENT_ADMIN", "CLIENT_MANAGER")
    fun createInterview(@Valid @NotNull(message = "Interview's data is required") interview: InterviewCreateRequest, @Context sec: SecurityContext): Uni<Response> {
        return this.interviewService.create(interview).map {
            Response.status(Response.Status.CREATED).entity(it).build()
        }
    }
}