package co.ogram.domain.interview

import io.quarkus.hibernate.reactive.panache.Panache
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import io.smallrye.mutiny.Uni

@ApplicationScoped
internal class InterviewRepository : PanacheRepository<Interview> {
    fun persistInterview(interview: Interview): Uni<Interview> {
        return Panache.withTransaction {
            persist(interview)
        }
    }
}