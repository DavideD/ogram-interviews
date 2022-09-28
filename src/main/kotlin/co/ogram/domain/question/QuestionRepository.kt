package co.ogram.domain.question

import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import io.smallrye.mutiny.Uni

@ApplicationScoped
internal class QuestionRepository : PanacheRepository<Question> {
    fun persistQuestion(question: Question): Uni<Question> {
        return Panache.withTransaction {
            persist(question)
        }
    }
}