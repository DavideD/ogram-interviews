package co.ogram.domain.answer

import co.ogram.domain.exception.AnswerNotFoundException
import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import io.smallrye.mutiny.Uni

@ApplicationScoped
internal class AnswerRepository : PanacheRepository<Answer> {
    fun getAnswer(answerId: Long): Uni<Answer> {
        return findById(answerId)
            .map {
                it ?: throw AnswerNotFoundException("Answer with id $answerId not found")
            }
    }

    fun persistAnswer(answer: Answer): Uni<Answer> {
        return Panache.withTransaction {
            persist(answer)
        }
    }
}