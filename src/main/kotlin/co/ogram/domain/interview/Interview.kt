package co.ogram.domain.interview

import co.ogram.domain.question.Question
import io.quarkus.runtime.annotations.RegisterForReflection

import co.ogram.infrastructure.database.Table.INTERVIEW_TABLE
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity(name = INTERVIEW_TABLE)
@RegisterForReflection
internal data class Interview (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val interviewId: Long? = null,

    @Column(name = "job_id", nullable = false)
    val jobId: Long = Long.MIN_VALUE,

    @Column(name = "client_id", nullable = false)
    val clientId: Long = Long.MIN_VALUE,

    @Column(name = "retake_limit", nullable = false)
    val retakeLimit: Byte = Byte.MIN_VALUE,

    @field:ManyToMany(mappedBy = "interviews", fetch = FetchType.LAZY)
    @field:Fetch(FetchMode.JOIN)
    val questions: MutableSet<Question> = mutableSetOf(),
)