package co.ogram

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "answer")
data class Answer (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val answerId: Long? = 0,
    val questionId: Long,
    val spId: Long,
    val answerTime: Short,
    val fileURL: String,
    val retakeCount: Byte,
    val totalTime: Short,
)