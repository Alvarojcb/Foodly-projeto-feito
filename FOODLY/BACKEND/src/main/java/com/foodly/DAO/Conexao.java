package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    // Ajuste esses valores conforme o seu banco
    private static final String URL = "jdbc:mysql://localhost:3306/foodly";
    private static final String USER = "root";
    private static final String PASSWORD = "senha";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
