package co.ogram.domain.answer

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.*
import javax.persistence.FetchType.LAZY

import co.ogram.domain.question.Question
import co.ogram.infrastructure.database.Table.ANSWER_TABLE

@Entity(name = ANSWER_TABLE)
@RegisterForReflection
internal data class Answer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id")
    val answerId: Long? = null,
    @field:Column(name = "question_id", nullable = false)
    val questionId: Long,
    @field:Column(name = "sp_id", nullable = false)
    val spId: Long,
    @field:Column(name = "answer_time", nullable = false)
    val answerTime: Short,
    @field:Column(name = "file_url", nullable = false)
    val fileURL: String,
    @field:Column(name = "retake_count", nullable = false)
    val retakeCount: Byte,
    @field:Column(name = "total_time", nullable = false)
    val totalTime: Short,
    @field:ManyToOne(fetch = LAZY)
    @JoinColumn(name="question_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false, insertable = false, updatable = false)
    val question: Question = Question(
        questionId = questionId,
        description = "",
        maxTime = 1),
)