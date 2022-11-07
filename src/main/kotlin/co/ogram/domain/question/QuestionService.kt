package co.ogram.domain.question

import co.ogram.domain.answer.Answer
import co.ogram.domain.interview.InterviewService
import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

@ApplicationScoped
internal class QuestionService {
    @Inject @field:Default lateinit var questionRepository: QuestionRepository
    @Inject @field:Default lateinit var interviewService: InterviewService

    fun get(questionId: Long): Uni<Question> = questionRepository.getQuestion(questionId)

    fun addAnswer(questionId: Long, answer: Answer): Uni<Question> {
        return questionRepository
            .getQuestion(questionId)
            .map {
                it.answers.add(answer)
                questionRepository.persistQuestion(it)
                it
            }
    }

    fun create(createRequest: QuestionCreateRequest): Uni<Question> = createRequest
        .toEntity(null)
        .run {
            questionRepository.persistQuestion(this)
        }

    fun create(createRequest: QuestionCreateRequest, interviewId: Long, clientId: Long?): Uni<Question> = createRequest
        .toEntity(clientId)
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
                        answers = mutableSetOf(),
//                        answers = mutableListOf(),
                    )
                    questionRepository.persistQuestion(question)
                }
        }
}