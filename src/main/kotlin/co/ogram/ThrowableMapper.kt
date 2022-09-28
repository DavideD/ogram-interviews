package co.ogram

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ThrowableMapper : ExceptionMapper<Throwable> {
    override fun toResponse(exception: Throwable?): Response {
        val defaultMessage = "Internal Server Error"
        val errorMessage = exception?.message ?: defaultMessage
        val errorResponse = ErrorResponse(errorMessage, mapOf())
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build()
    }
}