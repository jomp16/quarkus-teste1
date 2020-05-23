package dev.oxente.quarkus.controllers.api.v1.json

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class OxentePessoa(
        val nome: String = "",
        val idade: Int = 0
)