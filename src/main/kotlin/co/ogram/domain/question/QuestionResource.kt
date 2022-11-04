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

@Path("/interviews")
internal class QuestionResource {
    @Inject @field:Default lateinit var questionService: QuestionService

    @GET
    @Operation(summary = "returns question from the database")
    @PermitAll
    @Path("/{interviewId}/questions/{questionId}")
    fun getQuestions(@PathParam("interviewId") interviewId: Long, @PathParam("questionId") questionId: Long): Uni<Response> {
        return this.questionService
            .get(questionId)
            .map { Response.ok().entity(it).build() }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Operation(summary = "creates a question in the database")
    @RolesAllowed("CLIENT_OWNER", "CLIENT_ADMIN", "CLIENT_MANAGER")
    @Path("/{interviewId}/questions")
    fun createQuestion(
        @PathParam("interviewId")
        interviewId: Long,
        @Valid
        @NotNull(message = "Question's data is required")
        question: QuestionCreateRequest
    ): Uni<Response> {
        return this.questionService.create(question, interviewId).map {
            Response.status(Response.Status.CREATED).entity(it).build()
        }
    }
}