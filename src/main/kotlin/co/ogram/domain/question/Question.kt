package co.ogram.domain.question

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

import co.ogram.infrastructure.database.Table.QUESTION_TABLE
import co.ogram.domain.answer.Answer
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction.CASCADE
import javax.persistence.*

@Entity(name = QUESTION_TABLE)
@RegisterForReflection
internal data class Question (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @field:Column(name = "id")
        val questionId: Long? = null,
        @field:Column(name = "work_category_id", nullable = true)
        val workCategoryId: Long? = null,
        @field:NotEmpty(message = "Description cannot be null")
        val description: String = "",
        @field:Column(name = "max_time")
        @field:NotNull(message = "Max time cannot be null")
        val maxTime: Short = Short.MIN_VALUE,
        @field:Column(name = "client_id", nullable = true)
        val clientId: Long? = null,
        @field:OneToMany(mappedBy = "question")
        @field:OnDelete(action = CASCADE)
        val answers: MutableList<Answer>? = mutableListOf(),
)