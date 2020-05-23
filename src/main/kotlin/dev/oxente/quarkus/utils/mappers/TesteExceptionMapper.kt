package dev.oxente.quarkus.utils.mappers

import com.fasterxml.jackson.databind.ObjectMapper
import javax.inject.Inject
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider


@Provider
class TesteExceptionMapper : ExceptionMapper<Throwable> {
    @Inject
    internal lateinit var objectMapper: ObjectMapper

    override fun toResponse(exception: Throwable): Response {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(objectMapper.writeValueAsString(
                        mapOf("exception" to true,
                                "messages" to getExceptionMessageChain(exception))
                ))
                .build()
    }

    private fun getExceptionMessageChain(originalThrowable: Throwable?): List<String?>? {
        var throwable = originalThrowable
        val result = mutableListOf<String>()
        while (throwable != null) {
            throwable.message?.let { result.add(it.replace("[\\r\\n]+".toRegex(), "")) }
            throwable = throwable.cause
        }

        return result
    }
}