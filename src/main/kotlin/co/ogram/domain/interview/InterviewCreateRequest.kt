package co.ogram.domain.interview

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@JsonRootName("interview")
@RegisterForReflection
internal data class InterviewCreateRequest (
    @field:JsonProperty("job_id")
    val jobId: Long? = null,

    @field:JsonProperty("client_id")
    @field:NotNull(message = "client id is required")
    val clientId: Long? = null,

    @field:JsonProperty("retake_limit")
    @field:Min(1, message = "retake limit should be at least 1")
    val retakeLimit: Byte? = null,
) {
    fun toEntity() = Interview(
        jobId = jobId as Long,
        clientId = clientId as Long,
        retakeLimit = retakeLimit as Byte,
    )
}