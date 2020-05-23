package dev.oxente.quarkus.controllers.api.v1.clients.json

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RegisterForReflection
class ClientApiV1Json {
    var id: Long? = null
    @NotBlank
    var name: String? = null
    @NotBlank
    @Size(min = 11, max = 14)
    var cnpj: String? = null
}