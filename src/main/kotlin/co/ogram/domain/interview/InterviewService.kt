package co.ogram.domain.interview

import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

import co.ogram.domain.question.Question

@ApplicationScoped
internal class InterviewService {
    @Inject @field:Default lateinit var interviewRepository: InterviewRepository

    fun addQuestion(interviewId: Long, question: Question): Uni<Interview> {
        return interviewRepository
            .getInterview(interviewId)
            .map {
                it.questions.add(question)
                interviewRepository.persistInterview(it)
                it
            }
    }

    fun getInterview(interviewId: Long): Uni<Interview> {
        return interviewRepository.getInterview(interviewId)
    }

    fun getQuestions(interviewId: Long): Uni<MutableSet<Question>> {
        return interviewRepository.getInterviewQuestions(interviewId)
    }

    fun create(createRequest: InterviewCreateRequest, clientId: Long): Uni<Interview> = createRequest
        .toEntity(clientId)
        .run {
            interviewRepository.persistInterview(this)
        }
}