package co.ogram.domain.question

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
internal class QuestionResource {
    @Inject @field:Default lateinit var questionService: QuestionService

    @GET
    @Operation(summary = "returns question from the database")
    @Path("/{interviewId}/questions/{questionId}")
    fun getQuestions(@PathParam("interviewId") interviewId: Long, @PathParam("questionId") questionId: Long): Uni<Response> {
        return this.questionService
            .get(questionId)
            .map { Response.ok().status(Response.Status.OK).entity(it).build() }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Operation(summary = "creates a question in the database")
    @Path("/questions")
    fun createQuestion(@Valid @NotNull(message = "Question's data is required") question: QuestionCreateRequest): Uni<Response> {
        return this.questionService.create(question).map {
            Response.ok().status(Response.Status.CREATED).entity(it).build()
        }
    }
}