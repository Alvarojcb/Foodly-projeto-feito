package com.foodly.Controller;

import com.foodly.DAO.ClienteDAO;
import com.foodly.DAO.UsuarioDAO;
import com.foodly.Models.Cliente;
import com.foodly.Models.Usuario;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ClienteController {

    private final UsuarioDAO usuarioDAO;
    private final ClienteDAO clienteDAO;

    public ClienteController() {
        this.usuarioDAO = new UsuarioDAO();
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * H1 - Cadastro do Cliente
     * Fluxo: cria um usuário do tipo "cliente" e depois o registro em clientes.
     */
    public Cliente cadastrarCliente(String nome,
                                    String email,
                                    String senhaHash,
                                    String telefone,
                                    String enderecoPadrao) {

        try {
            // 1) Cria o usuário
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenhaHash(senhaHash);
            usuario.setTelefone(telefone);
            usuario.setTipoUsuario("cliente");
            usuario.setCriadoEm(LocalDateTime.now());

            int usuarioId = usuarioDAO.salvar(usuario);

            // 2) Cria o cliente associado ao usuário
            Cliente cliente = new Cliente();
            cliente.setUsuarioId(usuarioId);
            cliente.setEnderecoPadrao(enderecoPadrao);

            int clienteId = clienteDAO.salvar(cliente);
            cliente.setId(clienteId);

            return cliente;

        } catch (SQLException e) {
            // Aqui é só esqueleto: você decide se loga, relança, ou trata de outra forma
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
            return null;
        }
    }

    public Cliente buscarClientePorId(int clienteId) {
        try {
            return clienteDAO.buscarPorId(clienteId);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }

    public List<Cliente> listarClientes() {
        try {
            return clienteDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
            return List.of();
        }
    }

}
