package dao;

import models.Restaurante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO {

    public int salvar(Restaurante r) throws SQLException {
        String sql = "INSERT INTO restaurantes (usuario_id, nome_fantasia, cnpj, endereco, dados_bancarios, ativo) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, r.getUsuarioId());
            stmt.setString(2, r.getNomeFantasia());
            stmt.setString(3, r.getCnpj());
            stmt.setString(4, r.getEndereco());
            stmt.setString(5, r.getDadosBancarios());
            stmt.setBoolean(6, r.isAtivo());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    r.setId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    public Restaurante buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, usuario_id, nome_fantasia, cnpj, endereco, dados_bancarios, ativo " +
                     "FROM restaurantes WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Restaurante r = new Restaurante();
                    r.setId(rs.getInt("id"));
                    r.setUsuarioId(rs.getInt("usuario_id"));
                    r.setNomeFantasia(rs.getString("nome_fantasia"));
                    r.setCnpj(rs.getString("cnpj"));
                    r.setEndereco(rs.getString("endereco"));
                    r.setDadosBancarios(rs.getString("dados_bancarios"));
                    r.setAtivo(rs.getBoolean("ativo"));
                    return r;
                }
            }
        }
        return null;
    }

    public List<Restaurante> listarTodos() throws SQLException {
        String sql = "SELECT id, usuario_id, nome_fantasia, cnpj, endereco, dados_bancarios, ativo FROM restaurantes";
        List<Restaurante> lista = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Restaurante r = new Restaurante();
                r.setId(rs.getInt("id"));
                r.setUsuarioId(rs.getInt("usuario_id"));
                r.setNomeFantasia(rs.getString("nome_fantasia"));
                r.setCnpj(rs.getString("cnpj"));
                r.setEndereco(rs.getString("endereco"));
                r.setDadosBancarios(rs.getString("dados_bancarios"));
                r.setAtivo(rs.getBoolean("ativo"));
                lista.add(r);
            }
        }
        return lista;
    }
}
