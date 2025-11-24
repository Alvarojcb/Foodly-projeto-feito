package com.foodly.DAO;

import com.foodly.Models.Promocao;

import java.sql.*;
import java.time.LocalDateTime;

public class PromocaoDAO {

    public int salvar(Promocao p) throws SQLException {
        String sql = "INSERT INTO promocoes (restaurante_id, titulo, descricao, tipo_desconto, valor_desconto, " +
                     "data_inicio, data_fim, ativo, criado_em) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (p.getRestauranteId() != null) {
                stmt.setInt(1, p.getRestauranteId());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }
            stmt.setString(2, p.getTitulo());
            stmt.setString(3, p.getDescricao());
            stmt.setString(4, p.getTipoDesconto());

            if (p.getValorDesconto() != null) {
                stmt.setDouble(5, p.getValorDesconto());
            } else {
                stmt.setNull(5, Types.DOUBLE);
            }

            stmt.setTimestamp(6, Timestamp.valueOf(p.getDataInicio()));
            stmt.setTimestamp(7, Timestamp.valueOf(p.getDataFim()));
            stmt.setBoolean(8, p.isAtivo());
            stmt.setTimestamp(9, Timestamp.valueOf(
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
