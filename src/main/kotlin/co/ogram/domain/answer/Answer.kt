package co.ogram.domain.answer

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.*
import co.ogram.domain.question.Question
import co.ogram.infrastructure.database.Table
import co.ogram.infrastructure.database.Table.ANSWER_TABLE
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.annotations.Formula

@Entity(name = ANSWER_TABLE)
@RegisterForReflection
internal data class Answer (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "id")
    val answerId: Long? = null,

    @field:Column(name = "question_id", nullable = false)
    val questionId: Long = Long.MIN_VALUE,

    @field:Column(name = "sp_id", nullable = false)
    val spId: Long = Long.MIN_VALUE,

    @field:Column(name = "answer_time", nullable = false)

    val answerTime: Short = Short.MIN_VALUE,

    @field:Column(name = "file_url", nullable = false)
    val fileURL: String = "",

    @field:Column(name = "retake_count", nullable = false)
    val retakeCount: Byte = Byte.MIN_VALUE,

    @field:Column(name = "total_time", nullable = false)
    val totalTime: Short = Short.MIN_VALUE,

    @field:ManyToOne(fetch = FetchType.LAZY)
    @field:Fetch(FetchMode.JOIN)
    @field:JoinColumn(
        name="question_id",
        foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT),
        nullable = false,
        insertable = false,
        updatable = false,
    )
    @field:JsonIgnore
    val question: Question = Question(),
)