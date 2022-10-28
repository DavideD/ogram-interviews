package co.ogram.domain.interview

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("interview")
@RegisterForReflection
internal data class InterviewResponse (
    @field:JsonProperty("interview_id")
    val interviewId: Long?,

    @field:JsonProperty("job_id")
    val jobId: Long?,

    @field:JsonProperty("client_id")
    val clientId: Long? = null,

    @field:JsonProperty("retake_limit")
    val retakeLimit: Byte,
) {
    companion object {
        @JvmStatic
        fun build(interview: Interview): InterviewResponse = InterviewResponse(
            interviewId = interview.interviewId,
            jobId = interview.jobId,
            clientId = interview.clientId,
            retakeLimit = interview.retakeLimit,
        )
    }
}