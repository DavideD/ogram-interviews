package co.ogram.domain.answer

import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

import co.ogram.domain.question.QuestionService

@ApplicationScoped
internal class AnswerService {
    @Inject @field:Default lateinit var answerRepository: AnswerRepository
    @Inject @field:Default lateinit var questionService: QuestionService

    fun create(createRequest: AnswerCreateRequest, spId: Long): Uni<AnswerResponse> {
         createRequest
            .toEntity(spId, fileURL = "http://www.a-url.com/mock")
            .run {
                return questionService
                    .get(this.questionId)
                    .chain { _ -> answerRepository.persistAnswer(this)
                    }
                    .map { AnswerResponse.build(it) }
            }
    }
}