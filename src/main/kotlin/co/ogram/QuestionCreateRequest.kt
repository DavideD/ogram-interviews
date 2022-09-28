package co.ogram

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import javax.validation.constraints.Max
import javax.validation.constraints.NotNull
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive

@JsonRootName("question")
@RegisterForReflection
data class QuestionCreateRequest(
    @field:JsonProperty("work_category_id")
    val workCategoryId: Long? = null,

    @field:JsonProperty("description")
    @field:NotEmpty(message = "Description is required")
    val description: String?,

    @field:JsonProperty("max_time")
    @field:NotNull(message = "Time limit of answers is required")
    @field:Positive(message = "Time limit should be at least 1 second")
    @field:Max(600, message = "Time limit should not exceed 10 minutes")
    val maxTime: Short?,

    @field:JsonProperty("client_id")
    val clientId: Long? = null,
) {
    fun toEntity() = Question(
        workCategoryId = workCategoryId,
        description = description as String,
        clientId = clientId,
        maxTime = maxTime as Short,
    )
}