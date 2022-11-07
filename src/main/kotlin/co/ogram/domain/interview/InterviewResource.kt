package co.ogram.domain.interview

import javax.ws.rs.core.MediaType.APPLICATION_JSON
import io.smallrye.mutiny.Uni
import javax.enterprise.inject.Default
import javax.inject.Inject
import org.eclipse.microprofile.openapi.annotations.Operation
import javax.annotation.security.PermitAll
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.*
import javax.ws.rs.core.Response
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Path("/interviews")
internal class InterviewResource {
    @Inject @field:Default lateinit var interviewService: InterviewService

    @GET
    @Operation(summary = "returns an interview for given interview's id")
    @PermitAll
    @Path("/{interviewId}")
    fun getInterview(@PathParam("interviewId") interviewId: Long): Uni<Response> {
        return this.interviewService.getInterview(interviewId).map {
            Response.ok().entity(it).build()
        }
    }

    @GET
    @Operation(summary = "returns all questions for given interview")
    @PermitAll
    @Path("/{interviewId}/questions")
    fun getQuestions(@PathParam("interviewId") interviewId: Long): Uni<Response> {
        return this.interviewService.getQuestions(interviewId).map { Response.ok().entity(it).build() }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Operation(summary = "creates an interview with questions in the database")
    @RolesAllowed("CLIENT_OWNER", "CLIENT_ADMIN", "CLIENT_MANAGER")
    fun createInterview(
        @Valid @NotNull(message = "Interview's data is required") interview: InterviewCreateRequest,
        @Context sec: SecurityContext,
    ): Uni<Response> {
        val clientId = sec.userPrincipal.name.toLong()
        return this.interviewService.create(interview, clientId).map {
            Response.status(Response.Status.CREATED).entity(InterviewResponse.build((it))).build()
        }
    }
}