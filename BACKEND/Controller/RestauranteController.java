package controllers;

import dao.RestauranteDAO;
import dao.UsuarioDAO;
import models.Restaurante;
import models.Usuario;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class RestauranteController {

    private final UsuarioDAO usuarioDAO;
    private final RestauranteDAO restauranteDAO;

    public RestauranteController() {
        this.usuarioDAO = new UsuarioDAO();
        this.restauranteDAO = new RestauranteDAO();
    }

    /**
     * H2 - Cadastro do Restaurante
     * Fluxo: cria um usuário do tipo "restaurante" e depois o registro em restaurantes.
     */
    public Restaurante cadastrarRestaurante(String nomeProprietario,
                                            String email,
                                            String senhaHash,
                                            String telefone,
                                            String nomeFantasia,
                                            String cnpj,
                                            String endereco,
                                            String dadosBancarios) {

        try {
            // 1) Cria o usuário para o proprietário do restaurante
            Usuario usuario = new Usuario();
            usuario.setNome(nomeProprietario);
            usuario.setEmail(email);
            usuario.setSenhaHash(senhaHash);
            usuario.setTelefone(telefone);
            usuario.setTipoUsuario("restaurante");
            usuario.setCriadoEm(LocalDateTime.now());

            int usuarioId = usuarioDAO.salvar(usuario);

            // 2) Cria o restaurante associado a esse usuário
            Restaurante r = new Restaurante();
            r.setUsuarioId(usuarioId);
            r.setNomeFantasia(nomeFantasia);
            r.setCnpj(cnpj);
            r.setEndereco(endereco);
            r.setDadosBancarios(dadosBancarios);
            r.setAtivo(true);

            int restauranteId = restauranteDAO.salvar(r);
            r.setId(restauranteId);

            return r;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar restaurante: " + e.getMessage());
            return null;
        }
    }

    public Restaurante buscarRestaurantePorId(int restauranteId) {
        try {
            return restauranteDAO.buscarPorId(restauranteId);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar restaurante: " + e.getMessage());
            return null;
        }
    }

    public List<Restaurante> listarRestaurantes() {
        try {
            return restauranteDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar restaurantes: " + e.getMessage());
            return List.of();
        }
    }

    // Aqui depois dá pra adicionar:
    // - desativarRestaurante(int id)
    // - atualizarDadosRestaurante(...)
}
