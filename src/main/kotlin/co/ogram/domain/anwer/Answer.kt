package co.ogram.domain.anwer

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import io.quarkus.runtime.annotations.RegisterForReflection

import co.ogram.infrastructure.database.Table.ANSWER_TABLE

@Entity(name = ANSWER_TABLE)
@RegisterForReflection
internal data class Answer (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val answerId: Long? = 0,
    val questionId: Long,
    val spId: Long,
    val answerTime: Short,
    val fileURL: String,
    val retakeCount: Byte,
    val totalTime: Short,
)