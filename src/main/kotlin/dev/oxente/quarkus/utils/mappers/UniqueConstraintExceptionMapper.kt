package dev.oxente.quarkus.utils.mappers

import com.fasterxml.jackson.databind.ObjectMapper
import dev.oxente.quarkus.utils.exceptions.UniqueConstraintException
import javax.inject.Inject
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider


@Provider
class UniqueConstraintExceptionMapper : ExceptionMapper<UniqueConstraintException> {
    @Inject
    internal lateinit var objectMapper: ObjectMapper

    override fun toResponse(exception: UniqueConstraintException): Response {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(objectMapper.writeValueAsString(
                        mapOf(
                                "exception" to true,
                                "message" to "Value already exists in the database, try another value.",
                                "field" to exception.fieldName
                        )
                ))
                .build()
    }
}