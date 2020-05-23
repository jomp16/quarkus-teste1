package dev.oxente.quarkus.repository.clients

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RegisterForReflection
@Entity(name = "clients")
open class ClientEntity {
    @Id
    @GeneratedValue
    open var id: Long? = 0
    @NotBlank
    @Column(unique = true)
    open var name: String? = ""
    @Size(min = 11, max = 14)
    @Column(unique = true)
    open var cnpj: String? = ""
}