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

    fun create(createRequest: AnswerCreateRequest, spId: Long): Uni<Answer> {
        return createRequest
            .toEntity(spId, fileURL = "http://www.a-url.com/mock")
            .run {
                questionService
                    .addAnswer(this.questionId, this)
                    .chain { it ->
                        val answer = Answer(
                            answerId = this.answerId,
                            questionId = this.questionId,
                            spId = this.spId,
                            answerTime = this.answerTime,
                            fileURL = this.fileURL,
                            retakeCount = this.retakeCount,
                            totalTime = this.totalTime,
                            question = it,
                        )
                        answerRepository.persistAnswer(answer)
                    }
            }
    }
}