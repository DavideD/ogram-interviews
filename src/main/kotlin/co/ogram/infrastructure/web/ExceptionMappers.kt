package co.ogram.infrastructure.web

import co.ogram.domain.exception.NotFoundException
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.server.ServerExceptionMapper
import javax.ws.rs.core.Response

internal class ExceptionMappers {
    @ServerExceptionMapper
    fun mapException(x: NotFoundException): RestResponse<ErrorResponse> {
        return RestResponse.status(Response.Status.NOT_FOUND, ErrorResponse(x.name, mapOf()))
    }
}