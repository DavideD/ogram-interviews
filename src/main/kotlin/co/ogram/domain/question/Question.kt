package co.ogram.domain.question

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction.CASCADE
import javax.persistence.*

import co.ogram.infrastructure.database.Table.QUESTION_TABLE
import co.ogram.infrastructure.database.Table.INTERVIEW_QUESTION_TABLE
import co.ogram.domain.answer.Answer
import co.ogram.domain.interview.Interview
import co.ogram.infrastructure.database.Table
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.annotations.Formula

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

        @field:OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
        @field:OnDelete(action = CASCADE)
        @field:Fetch(FetchMode.JOIN)
        val answers: MutableSet<Answer> = mutableSetOf(),
//        val answers: MutableList<Answer> = mutableListOf(),

        @field:ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @field:JoinTable(
                name = INTERVIEW_QUESTION_TABLE,
                joinColumns = [JoinColumn(name = "question_id", referencedColumnName = "id")],
                inverseJoinColumns = [JoinColumn(name = "interview_id", referencedColumnName = "id")]
        )
        @field:Fetch(FetchMode.JOIN)
        @field:JsonIgnore
        val interviews: MutableList<Interview> = mutableListOf(),
)