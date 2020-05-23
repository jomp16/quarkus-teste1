package dev.oxente.quarkus.repository.clients

import dev.oxente.quarkus.utils.exceptions.UniqueConstraintException
import io.quarkus.hibernate.orm.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped
import javax.persistence.PersistenceException

@ApplicationScoped
class ClientsRepository : PanacheRepository<ClientEntity> {
    fun createClient(clientEntity: ClientEntity) {
        try {
            persistAndFlush(clientEntity)
        } catch (e: PersistenceException) {
            throwException(e)
        }
    }

    fun updateClient(clientEntity: ClientEntity) {
        try {
            persistAndFlush(clientEntity)
        } catch (e: PersistenceException) {
            throwException(e)
        }
    }

    fun deleteClient(clientEntity: ClientEntity) {
        delete(clientEntity)
    }
    
    private fun throwException(e: PersistenceException) {
        e.cause?.let { cause ->
            if (cause is org.hibernate.exception.ConstraintViolationException) {
                when (cause.constraintName) {
                    "clients_name_key" -> throw UniqueConstraintException("name")
                    "clients_cnpj_key" -> throw UniqueConstraintException("cnpj")
                }
            }
        }

        throw e
    }
} 