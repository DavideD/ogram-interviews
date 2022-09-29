package co.ogram.domain.answer

import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import io.smallrye.mutiny.Uni

@ApplicationScoped
internal class AnswerRepository : PanacheRepository<Answer> {
    fun persistAnswer(answer: Answer): Uni<Answer> {
        return Panache.withTransaction {
            persist(answer)
        }
    }
}