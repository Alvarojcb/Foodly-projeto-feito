package com.foodly.DAO;

import com.foodly.Models.PlanoPremium;
import com.foodly.config.Conexao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlanoPremiumDAO {

    public List<PlanoPremium> listarTodos() throws SQLException {
        String sql = "SELECT * FROM planos_premium WHERE ativo = TRUE ORDER BY id";
        List<PlanoPremium> planos = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PlanoPremium plano = new PlanoPremium();
                plano.setId(rs.getInt("id"));
                plano.setNome(rs.getString("nome"));
                plano.setDescricao(rs.getString("descricao"));
                plano.setValorMensal(rs.getBigDecimal("valor_mensal"));
                plano.setDuracaoDias(rs.getInt("duracao_dias"));
                plano.setAtivo(rs.getBoolean("ativo"));
                plano.setCriadoEm(rs.getTimestamp("criado_em").toLocalDateTime());
                planos.add(plano);
            }
        }

        return planos;
    }

    public int salvar(PlanoPremium p) throws SQLException {
        String sql = "INSERT INTO planos_premium (nome, descricao, valor_mensal, duracao_dias, ativo, criado_em) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setBigDecimal(3, p.getValorMensal());
            stmt.setInt(4, p.getDuracaoDias());
            stmt.setBoolean(5, p.isAtivo());
            stmt.setTimestamp(6, Timestamp.valueOf(
                    p.getCriadoEm() != null ? p.getCriadoEm() : LocalDateTime.now()));

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
}
