package co.ogram.domain.interview

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Column
import io.quarkus.runtime.annotations.RegisterForReflection

import co.ogram.infrastructure.database.Table.INTERVIEW_TABLE

@Entity(name = INTERVIEW_TABLE)
@RegisterForReflection
internal data class Interview (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val interviewId: Long? = 0,
    @Column(name = "job_id", nullable = false)
    val jobId: Long,
    @Column(name = "client_id", nullable = false)
    val clientId: Long,
    @Column(name = "retake_limit", nullable = false)
    val retakeLimit: Byte,
)