package co.ogram.domain.interview

import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

import co.ogram.domain.question.Question
import co.ogram.domain.exception.InterviewNotFoundException

@ApplicationScoped
internal class InterviewService {
    @Inject @field:Default lateinit var interviewRepository: InterviewRepository

    fun addQuestion(interviewId: Long, question: Question): Uni<Interview> {
        return interviewRepository
            .findById(interviewId)
            .map {
                if (it == null) {
                    throw InterviewNotFoundException("Interview with id $interviewId not found")
                } else {
                    it.questions.add(question)
                    interviewRepository.persistInterview(it)
                    it
                }
            }
    }

    fun create(createRequest: InterviewCreateRequest): Uni<InterviewResponse> = createRequest
        .toEntity()
        .run {
            interviewRepository.persistInterview(this).map {
                InterviewResponse.build((it))
            }
        }
}