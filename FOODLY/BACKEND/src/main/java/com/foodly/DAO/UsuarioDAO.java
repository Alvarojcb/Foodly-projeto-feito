package dao;

import models.Usuario;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public int salvar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, senha_hash, telefone, tipo_usuario) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaHash());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getTipoUsuario());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGerado = rs.getInt(1);
                    usuario.setId(idGerado);
                    return idGerado;
                }
            }
        }
        return -1;
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, nome, email, senha_hash, telefone, tipo_usuario, criado_em " +
                     "FROM usuarios WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNome(rs.getString("nome"));
                    u.setEmail(rs.getString("email"));
                    u.setSenhaHash(rs.getString("senha_hash"));
                    u.setTelefone(rs.getString("telefone"));
                    u.setTipoUsuario(rs.getString("tipo_usuario"));

                    Timestamp criadoEmTs = rs.getTimestamp("criado_em");
                    if (criadoEmTs != null) {
                        u.setCriadoEm(criadoEmTs.toLocalDateTime());
                    }

                    return u;
                }
            }
        }
        return null;
    }

    public List<Usuario> listarTodos() throws SQLException {
        String sql = "SELECT id, nome, email, senha_hash, telefone, tipo_usuario, criado_em FROM usuarios";
        List<Usuario> lista = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenhaHash(rs.getString("senha_hash"));
                u.setTelefone(rs.getString("telefone"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));

                Timestamp criadoEmTs = rs.getTimestamp("criado_em");
                if (criadoEmTs != null) {
                    u.setCriadoEm(criadoEmTs.toLocalDateTime());
                }

                lista.add(u);
            }
        }
        return lista;
    }

    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios " +
                     "SET nome = ?, email = ?, senha_hash = ?, telefone = ?, tipo_usuario = ? " +
                     "WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaHash());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getTipoUsuario());
            stmt.setInt(6, usuario.getId());

            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
