package co.ogram.domain.answer

import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

import co.ogram.domain.question.QuestionService

@ApplicationScoped
internal class AnswerService {
    @Inject
    @field:Default
    lateinit var answerRepository: AnswerRepository

    @Inject
    @field:Default
    lateinit var questionService: QuestionService

    fun get(answerId: Long): Uni<Answer> {
        return answerRepository.getAnswer(answerId)
    }

    fun create(createRequest: AnswerCreateRequest, spId: Long): Uni<Answer> {
        return createRequest
            .toEntity(spId, fileURL = "http://www.a-url.com/mock")
            .run {
                val answer = Answer(
                    answerId = this.answerId,
                    spId = this.spId,
                    answerTime = this.answerTime,
                    fileURL = this.fileURL,
                    retakeCount = this.retakeCount,
                    totalTime = this.totalTime
                )
                answerRepository.persistAnswer(answer, questionId)
            }
    }
}