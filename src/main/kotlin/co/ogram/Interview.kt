package co.ogram

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Column

@Entity
@Table(name = "interview")
data class Interview (
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