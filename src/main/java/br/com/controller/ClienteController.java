package br.com.controller;

import br.com.config.DataBaseConfig;
import br.com.dao.ClienteDAO;
import br.com.model.Cliente;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;

@Path("/cliente")
public class ClienteController {

    @POST
    @Path("/cadastrar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarCliente(Cliente cliente) {
        Connection connection = null;
        if (cliente == null || cliente.getNome() == null || cliente.getTelefone() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"Falha\": false, \"mensagem\": \"Campos obrigatórios não preenchidos.\"}").build();
        }
        if (cliente.getTelefone().length() > 15) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"Falha\": false, \"mensagem\": \"Número do telefone maior que 15 caracteres.\"}").build();
        }
        try {
            connection = DataBaseConfig.connection();
            ClienteDAO.adicionarCliente(cliente, connection);
            return Response.ok().entity("{\"sucesso\": true, \"mensagem\": \"Cliente salvo\"}").build();
        } catch (Exception ex) {
            //LOGGER.error("Erro ao cadastrar usuario ", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Falha\": false, \"mensagem\": \"Erro ao cadastrar usuário.\"}").build();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                //LOGGER.error("falha ao fechar connection ", ex);
            }
        }
    }

    @GET
    @Path("/teste")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String teste() throws Exception {
        try {
            boolean ok = DataBaseConfig.testarConexao();
            return "{\"status\": " + ok + "}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\": false, \"erro\": \"" + e.getMessage() + "\"}";
        }
    }
}
