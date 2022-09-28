package co.ogram.domain.question

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("question")
@RegisterForReflection
internal data class QuestionResponse(
    @field:JsonProperty("question_id")
    val questionId: Long?,

    @field:JsonProperty("work_category_id")
    val workCategoryId: Long? = null,

    @field:JsonProperty("description")
    val description: String,

    @field:JsonProperty("max_time")
    val maxTime: Short,

    @field:JsonProperty("client_id")
    val clientId: Long? = null
) {
    companion object {
        @JvmStatic
        fun build(question: Question): QuestionResponse = QuestionResponse(
            questionId = question.questionId,
            workCategoryId = question.workCategoryId,
            description = question.description,
            maxTime = question.maxTime,
            clientId = question.clientId,
        )
    }
}