package co.ogram

import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import io.smallrye.mutiny.Uni

@ApplicationScoped
class QuestionRepository : PanacheRepository<Question> {
    fun persistQuestion(question: Question): Uni<Question> {
        return Panache.withTransaction {
            persist(question)
        }
    }
}