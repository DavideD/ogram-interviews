package co.ogram.domain.question

import co.ogram.domain.exception.QuestionNotFoundException
import co.ogram.domain.interview.InterviewService
import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
internal class QuestionService {
    @Inject @field:Default lateinit var questionRepository: QuestionRepository
    @Inject @field:Default lateinit var interviewService: InterviewService

    fun get(questionId: Long): Uni<QuestionResponse> = questionRepository
        .findById(questionId)
        .map {
            if (it == null) {
                throw QuestionNotFoundException("Question with id $questionId not found")
            } else {
                QuestionResponse.build(it)
            }
        }

    fun create(createRequest: QuestionCreateRequest, interviewId: Long): Uni<QuestionResponse> = createRequest
        .toEntity()
        .run {
            interviewService
                .addQuestion(interviewId, this)
                .chain { it ->
                    val question = Question(
                        questionId = this.questionId,
                        workCategoryId = this.workCategoryId,
                        description = this.description,
                        maxTime = this.maxTime,
                        clientId = this.clientId,
                        interviews = mutableListOf(it),
                    )
                    questionRepository.persistQuestion(question)
                }
                .map {
                    QuestionResponse.build(it)
                }
//            questionRepository
//                .persistQuestion(this)
//                .chain { it -> interviewService.addQuestion(interviewId, it) }
//                .map {
//                    QuestionResponse.build(this)
//                }
        }
}