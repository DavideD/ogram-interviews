package co.ogram.domain.question

import co.ogram.domain.exception.QuestionNotFoundException
import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import io.smallrye.mutiny.Uni

@ApplicationScoped
internal class QuestionRepository : PanacheRepository<Question> {
    fun getQuestion(questionId: Long): Uni<Question> {
        return findById(questionId)
            .map {
                it ?: throw QuestionNotFoundException("Interview with id $questionId not found")
            }
    }

    fun persistQuestion(question: Question): Uni<Question> {
        return Panache.withTransaction {
            persist(question)
        }
    }
}