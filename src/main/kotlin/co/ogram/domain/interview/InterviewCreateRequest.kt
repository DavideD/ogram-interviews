package co.ogram.domain.interview

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import javax.validation.constraints.Min

@JsonRootName("interview")
@RegisterForReflection
internal data class InterviewCreateRequest (
    @field:JsonProperty("job_id")
    val jobId: Long? = null,

    @field:JsonProperty("retake_limit")
    @field:Min(1, message = "retake limit should be at least 1")
    val retakeLimit: Byte? = null,
) {
    fun toEntity(clientId: Long) = Interview(
        jobId = jobId as Long,
        clientId = clientId,
        retakeLimit = retakeLimit as Byte,
    )
}