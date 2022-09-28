package co.ogram

import javax.validation.ConstraintViolationException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()

@Provider
class ConstraintViolationExceptionMapper : ExceptionMapper<ConstraintViolationException> {
    override fun toResponse(exception: ConstraintViolationException?): Response {
        val errorMessages = mutableMapOf<String, String>()
        exception?.constraintViolations?.forEach {
            val nodes = it.propertyPath.toList()
            errorMessages[nodes[nodes.lastIndex].toString().toSnakeCase()] = it.message
        }
        val errorMessage = if (errorMessages.isEmpty()) exception?.message.toString() else "Invalid Data"
        val errorResponse = ErrorResponse(errorMessage, errorMessages.toMap())
        return Response
            .status(Response.Status.BAD_REQUEST)
            .entity(errorResponse)
            .build()
    }

    private fun String.toSnakeCase(): String {
        return camelRegex.replace(this) {
            "_${it.value}"
        }.lowercase()
    }
}