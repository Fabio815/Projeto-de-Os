package br.com.controller;

import br.com.config.DataBaseConfig;
import br.com.dao.ClienteDAO;
import br.com.model.Cliente;
import br.com.model.Filtro;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/cliente")
public class ClienteController {

    private static final Logger LOGGER = LogManager.getLogger();

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
            LOGGER.error("Erro ao cadastrar usuario ", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"Falha\": false, \"mensagem\": \"Erro ao cadastrar usuário.\"}").build();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                LOGGER.error("falha ao fechar connection ", ex);
            }
        }
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarCliente(@QueryParam("filtros") String jsonData) {
        Connection connection = null;
        List<Cliente> clientes;
        try {
            connection = DataBaseConfig.connection();
            List<Filtro> filtro = new ArrayList<>();
            Map<String, Object> resposta;
            resposta = new HashMap<>();
            clientes = ClienteDAO.listarClientes(connection, filtro);
            resposta.put("clientes", clientes);
            return Response.ok().entity(resposta).build();
        } catch (Exception ex) {
            LOGGER.error("Erro ao listar usuarios ", ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"sucesso\": false, \"mensagem\": \"Erro ao listar usuarios.\"}").build();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                LOGGER.error("falha ao fechar connection ", ex);
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
