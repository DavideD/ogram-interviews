package co.ogram.domain.answer

import co.ogram.domain.exception.AnswerNotFoundException
import co.ogram.domain.question.Question
import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.panache.common.Parameters
import javax.enterprise.context.ApplicationScoped
import io.smallrye.mutiny.Uni
import org.hibernate.reactive.mutiny.Mutiny

@ApplicationScoped
internal class AnswerRepository : PanacheRepository<Answer> {
    fun getAnswer(answerId: Long): Uni<Answer> {
        return findById(answerId)
            .map {
                it ?: throw AnswerNotFoundException("Answer with id $answerId not found")
            }
        // or:
//        return find("from answer a left join fetch a.question where a.id = :id", Parameters.with("id", answerId))
//            .firstResult()
        // or:
//        return findById(answerId)
//            .call { answer ->
//                Mutiny.fetch(answer.question)
//            } ?: throw AnswerNotFoundException("Answer with id $answerId not found")
//            .map {
//                it ?: throw AnswerNotFoundException("Answer with id $answerId not found")
//            }
    }

    fun persistAnswer(answer: Answer): Uni<Answer> {
        return Panache.withTransaction {
            persist(answer)
        }
    }

    fun persistAnswer(answer: Answer, questionId: Long): Uni<Answer> {
        return Panache.withTransaction {
            session.chain{ s ->
                    val question = s.getReference(Question::class.java, questionId)
                    answer.question = question;
                    persist(answer)
                }
        }
    }
}