package co.ogram.domain.question

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import io.smallrye.mutiny.Uni
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.annotation.security.RolesAllowed
import javax.annotation.security.PermitAll
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.Consumes
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import org.eclipse.microprofile.openapi.annotations.Operation
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Path("/questions")
internal class QuestionResource {
    @Inject @field:Default lateinit var questionService: QuestionService

    @GET
    @Operation(summary = "returns question from the database")
    @PermitAll
    @Path("/{questionId}")
    fun getQuestion(@PathParam("questionId") questionId: Long): Uni<Response> {
        return this.questionService
            .get(questionId)
            .map { Response.ok().entity(QuestionResponse.build(it)).build() }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Operation(summary = "creates a question in the database")
    @RolesAllowed("CLIENT_OWNER", "CLIENT_ADMIN", "CLIENT_MANAGER")
    fun createQuestion(
        @Valid @NotNull(message = "Question's data is required") question: QuestionCreateRequest,
    ): Uni<Response> {
        return this.questionService.create(question).map {
            Response.status(Response.Status.CREATED).entity(QuestionResponse.build(it)).build()
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Operation(summary = "creates a question in the database for given interview and client")
    @RolesAllowed("CLIENT_OWNER", "CLIENT_ADMIN", "CLIENT_MANAGER")
    @Path("/{interviewId}")
    fun createQuestion(
        @PathParam("interviewId") interviewId: Long,
        @Valid @NotNull(message = "Question's data is required") question: QuestionCreateRequest,
        @Context sec: SecurityContext,
    ): Uni<Response> {
        val clientId = sec.userPrincipal.name.toLong()
        return this.questionService.create(question, interviewId, clientId).map {
            Response.status(Response.Status.CREATED).entity(QuestionResponse.build(it)).build()
        }
    }
}