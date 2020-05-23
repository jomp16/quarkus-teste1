package dev.oxente.quarkus.controllers.api.v1.clients

import dev.oxente.quarkus.repository.clients.ClientEntity
import dev.oxente.quarkus.repository.clients.ClientsRepository
import dev.oxente.quarkus.utils.exceptions.UniqueConstraintException
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api/v1/clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class ClientsApiV1Controller {
    @Inject
    lateinit var clientsRepository: ClientsRepository

    @GET
    @Path("/list")
    fun listClients(): List<ClientEntity> = clientsRepository.listAll()

    @GET
    @Path("/{id}")
    fun getClient(@PathParam("id") id: Long): Response {
        val clientEntity = clientsRepository.findById(id)

        return if (clientEntity == null) {
            Response.status(Response.Status.NOT_FOUND).build()
        } else {
            Response.ok(clientEntity).build()
        }
    }

    @POST
    @Path("/create")
    @Transactional
    @Throws(UniqueConstraintException::class)
    fun createClient(@Valid clientEntity: ClientEntity): Response {
        clientsRepository.createClient(clientEntity)

        return Response.ok(clientEntity).build()
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    fun updateClient(@PathParam("id") id: Long, @Valid clientEntity: ClientEntity): Response {
        val clientFromRepository = clientsRepository.findById(id)

        clientFromRepository.name = clientEntity.name
        clientFromRepository.cnpj = clientEntity.cnpj

        clientsRepository.updateClient(clientFromRepository)

        return Response.noContent().build()
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    fun deleteClient(@PathParam("id") id: Long): Response {
        val clientFromRepository = clientsRepository.findById(id)

        clientsRepository.deleteClient(clientFromRepository)

        return Response.noContent().build()
    }
}