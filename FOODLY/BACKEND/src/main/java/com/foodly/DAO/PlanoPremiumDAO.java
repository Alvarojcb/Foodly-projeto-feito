package com.foodly.DAO;

import com.foodly.Models.PlanoPremium;

import java.sql.*;
import java.time.LocalDateTime;

public class PlanoPremiumDAO {

    public int salvar(PlanoPremium p) throws SQLException {
        String sql = "INSERT INTO planos_premium (nome, descricao, valor_mensal, duracao_dias, ativo, criado_em) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setDouble(3, p.getValorMensal());
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
