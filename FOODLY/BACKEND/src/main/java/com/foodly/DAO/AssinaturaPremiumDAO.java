package com.foodly.DAO;

import com.foodly.Models.AssinaturaPremium;

import java.sql.*;
import java.time.LocalDateTime;

public class AssinaturaPremiumDAO {

    public int salvar(AssinaturaPremium a) throws SQLException {
        String sql = "INSERT INTO assinaturas_premium (cliente_id, plano_id, status, data_inicio, data_fim, " +
                     "renovacao_automatica, metodo_pagamento, referencia_pagamento, criado_em) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, a.getClienteId());
            stmt.setInt(2, a.getPlanoId());
            stmt.setString(3, a.getStatus());
            stmt.setTimestamp(4, Timestamp.valueOf(a.getDataInicio()));

            if (a.getDataFim() != null) {
                stmt.setTimestamp(5, Timestamp.valueOf(a.getDataFim()));
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }

            stmt.setBoolean(6, a.isRenovacaoAutomatica());
            stmt.setString(7, a.getMetodoPagamento());
            stmt.setString(8, a.getReferenciaPagamento());
            stmt.setTimestamp(9, Timestamp.valueOf(
                    a.getCriadoEm() != null ? a.getCriadoEm() : LocalDateTime.now()));

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    a.setId(id);
                    return id;
                }
            }
        }
        return -1;
    }
}
