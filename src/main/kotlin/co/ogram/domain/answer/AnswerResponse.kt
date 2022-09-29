package co.ogram.domain.answer

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("answer")
@RegisterForReflection
internal data class AnswerResponse (
    @field:JsonProperty("answer_id")
    val answerId: Long?,

    @field:JsonProperty("answer_time")
    val answerTime: Short,

    @field:JsonProperty("file_url")
    val fileURL: String,

    @field:JsonProperty("question_id")
    val questionId: Long,

    @field:JsonProperty("retake_count")
    val retakeCount: Byte,

    @field:JsonProperty("sp_id")
    val spId: Long,

    @field:JsonProperty("total_time")
    val totalTime: Short,
) {
    companion object {
        @JvmStatic
        fun build(answer: Answer): AnswerResponse = AnswerResponse(
            answerId = answer.answerId,
            answerTime = answer.answerTime,
            fileURL = answer.fileURL,
            questionId = answer.questionId,
            retakeCount = answer.retakeCount,
            spId = answer.spId,
            totalTime = answer.totalTime,
        )
    }
}