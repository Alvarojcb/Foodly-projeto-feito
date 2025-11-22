package com.foodly.DAO;

import com.foodly.Models.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public int salvar(Produto p) throws SQLException {
        String sql = "INSERT INTO produtos (restaurante_id, nome, descricao, preco, ativo) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, p.getRestauranteId());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getDescricao());
            stmt.setDouble(4, p.getPreco());
            stmt.setBoolean(5, p.isAtivo());

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    p.setId(id);
                    return id;
                }
            }
        }
        return -1;
    }

    public Produto buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, restaurante_id, nome, descricao, preco, ativo FROM produtos WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setRestauranteId(rs.getInt("restaurante_id"));
                    p.setNome(rs.getString("nome"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setAtivo(rs.getBoolean("ativo"));
                    return p;
                }
            }
        }
        return null;
    }

    public List<Produto> listarPorRestaurante(int restauranteId) throws SQLException {
        String sql = "SELECT id, restaurante_id, nome, descricao, preco, ativo FROM produtos WHERE restaurante_id = ?";
        List<Produto> lista = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, restauranteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setRestauranteId(rs.getInt("restaurante_id"));
                    p.setNome(rs.getString("nome"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setAtivo(rs.getBoolean("ativo"));
                    lista.add(p);
                }
            }
        }
        return lista;
    }
}
