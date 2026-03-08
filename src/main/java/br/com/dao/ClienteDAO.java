package br.com.dao;

import br.com.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClienteDAO {
    public static void adicionarCliente(Cliente cliente, Connection connection) throws Exception {
        if (cliente != null && connection != null) {
            PreparedStatement stmtEndereco = null;
            PreparedStatement stmtCliente = null;
            ResultSet rs = null;

            try {
                stmtCliente = connection.prepareStatement("insert into bf_cliente(nome, telefone) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
                stmtCliente.setString(1, cliente.getNome());
                stmtCliente.setString(2, cliente.getTelefone());
                stmtCliente.executeUpdate();

                rs = stmtCliente.getGeneratedKeys();
                long ultimoEndereco = 0;
                if (rs.next()) {
                    ultimoEndereco = rs.getLong(1);
                }

                stmtEndereco = connection.prepareStatement("insert into bf_endereco(rua, bairro, numero, complemento, id_cliente) values(?, ?, ?, ?, ?)");
                stmtEndereco.setString(1, cliente.getRua());
                stmtEndereco.setString(2, cliente.getBairro());
                stmtEndereco.setString(3, cliente.getNumero());
                stmtEndereco.setString(4, cliente.getComplemento());
                stmtEndereco.setLong(5, ultimoEndereco);
                stmtEndereco.executeUpdate();

            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmtEndereco != null) {
                    stmtEndereco.close();
                }
                if (stmtCliente != null) {
                    stmtCliente.close();
                }
            }
        }
    }
}
