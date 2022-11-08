package co.ogram.domain.interview

import co.ogram.domain.exception.InterviewNotFoundException
import co.ogram.domain.question.Question
import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.panache.common.Parameters
import javax.enterprise.context.ApplicationScoped
import io.smallrye.mutiny.Uni
import org.hibernate.reactive.mutiny.Mutiny

@ApplicationScoped
internal class InterviewRepository : PanacheRepository<Interview> {
    fun persistInterview(interview: Interview): Uni<Interview> {
        return Panache.withTransaction {
            persist(interview)
        }
    }

    fun getInterview(interviewId: Long): Uni<Interview> {
        return findById(interviewId)
            .map {
                it ?: throw InterviewNotFoundException("Interview with id $interviewId not found")
            }
        // or:
//        return find(
//            "from interview i left join fetch i.questions where i.id = :id",
//            Parameters.with("id", interviewId)
//        ).firstResult() ?: throw InterviewNotFoundException("Interview with id $interviewId not found")
        // or:
//        return findById(interviewId)
//            .call { interview ->
//                Mutiny.fetch(interview.questions)
//            } ?: throw InterviewNotFoundException("Interview with id $interviewId not found")
    }

    fun getInterviewQuestions(interviewId: Long): Uni<MutableSet<Question>> {
        return this.findById(interviewId).chain { interview ->
            if (interview == null) {
                throw InterviewNotFoundException("Interview with id $interviewId not found")
            }
            Mutiny.fetch(interview.questions)
        }
    }
}