package co.ogram.domain.answer

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import javax.validation.constraints.NotNull
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive

@JsonRootName("answer")
@RegisterForReflection
internal data class AnswerCreateRequest(
    @field:JsonProperty("question_id")
    @field:NotNull(message = "Question id is required")
    val questionId: Long?,

    @field:JsonProperty("sp_id")
    @field:NotNull(message = "SP id is required")
    val spId: Long?,

    @field:JsonProperty("answer_time")
    @field:NotNull(message = "Answer time is required")
    @field:Positive(message = "Answer time should be at least 1 second")
    val answerTime: Short?,

    @field:JsonProperty("retake_count")
    @field:NotNull(message = "Amount of attempts is required")
    val retakeCount: Byte?,

    @field:JsonProperty("total_time")
    @field:NotNull(message = "Total answer time is required")
    val totalTime: Short?,
) {
    fun toEntity(fileURL: String) = Answer(
        questionId = questionId as Long,
        spId = spId as Long,
        answerTime = answerTime as Short,
        retakeCount = retakeCount as Byte,
        totalTime = totalTime as Short,
        fileURL = fileURL,
    )
}