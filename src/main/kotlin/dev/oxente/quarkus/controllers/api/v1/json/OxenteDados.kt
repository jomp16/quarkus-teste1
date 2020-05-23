package dev.oxente.quarkus.controllers.api.v1.json

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class OxenteDados(
        val pessoa: OxentePessoa = OxentePessoa()
)