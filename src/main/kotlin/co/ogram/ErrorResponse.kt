package co.ogram

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("error")
@RegisterForReflection
data class ErrorResponse(
    @field:JsonProperty("message")
    val message: String,

    @field:JsonProperty("errors")
    val errors: Map<String, String>
)