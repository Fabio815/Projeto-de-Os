package br.com.dao;

import br.com.model.Cliente;
import br.com.model.Filtro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Cliente> listarClientes(Connection connection, List<Filtro> filtro) throws Exception {
        if (connection == null) return null;

        List <Cliente> lista = null;
        StringBuffer buff = new StringBuffer();
        PreparedStatement stmt = null;
        buff.append("""
			select c.id, c.nome, c.telefone, e.rua, e.bairro, e.numero, e.complemento, c.statusCliente
			from bf_cliente as c left join bf_endereco as e on c.id = e.id_cliente
			""");
        if (!filtro.isEmpty()) {
            buff.append(" where");
            for (Filtro f : filtro) {
                switch (f.getOperador()) {
                    case "like":
                        buff.append(" c.nome like ? and");
                        break;
                    case "eq":
                        buff.append(" c.id=? and");
                        break;
                    case "==":
                        buff.append(" c.statusCliente=? and");
                        break;
                }
            }
            buff.setLength(buff.length() - 3);
        }
        buff.append(" order by c.id");
        stmt = connection.prepareStatement(buff.toString());
        int i = 1;
        if (!filtro.isEmpty()) {
            for (Filtro f : filtro) {
                switch (f.getOperador()) {
                    case "like":
                        stmt.setString(i, "%" + f.getValor() + "%");
                        break;
                    case "eq":
                        stmt.setInt(i, Integer.parseInt(f.getValor()));
                        break;
                    case "==":
                        stmt.setByte(i, Byte.parseByte(f.getValor()));
                        break;
                }
                ++i;
            }
        }
        ResultSet rs = stmt.executeQuery();
        Cliente cliente = null;
        if (rs != null) {
            lista = new ArrayList<Cliente>();
            while(rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getLong("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setRua(rs.getString("rua"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setStatusCliente(rs.getByte("statusCliente"));
                lista.add(cliente);
            }
        }
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        return lista;
    }
}
