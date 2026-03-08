package br.com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/bike_facil?useTimezone=true&serverTimezone=America/Sao_Paulo";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection connection() throws Exception {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        return conn;
    }

    public static boolean testarConexao() throws Exception, Exception {
        try (Connection conn = connection()){
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Erro ao testar conexão: " + e.getMessage());
            return false;
        }
    }
}
