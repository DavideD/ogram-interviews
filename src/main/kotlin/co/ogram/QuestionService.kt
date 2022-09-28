package co.ogram

import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
class QuestionService {
    @Inject @field:Default lateinit var questionRepository: QuestionRepository

    fun get(questionId: Long): Uni<QuestionResponse?> = questionRepository
        .findById(questionId)
        .map {
            if (it == null) {
                null
            } else {
                QuestionResponse.build(it)
            }
        }

    fun create(createRequest: QuestionCreateRequest): Uni<QuestionResponse> = createRequest
        .toEntity()
        .run {
            questionRepository.persistQuestion(this).map {
                QuestionResponse.build(it)
            }
        }
}