package co.ogram.domain.question

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Column
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

import co.ogram.infrastructure.database.Table.QUESTION_TABLE

@Entity(name = QUESTION_TABLE)
@RegisterForReflection
internal data class Question constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @field:Column(name = "id")
        val questionId: Long? = null,
        @field:Column(name = "work_category_id", nullable = true)
        val workCategoryId: Long? = null,
        @field:NotEmpty(message = "Description cannot be null")
        val description: String,
        @field:Column(name = "max_time")
        @field:NotNull(message = "Max time cannot be null")
        val maxTime: Short,
        @field:Column(name = "client_id", nullable = true)
        val clientId: Long? = null
)