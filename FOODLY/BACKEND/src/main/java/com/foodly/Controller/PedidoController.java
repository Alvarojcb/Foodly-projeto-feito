package com.foodly.Controller;

import com.foodly.DAO.CarrinhoItemDAO;
import com.foodly.DAO.Conexao;
import com.foodly.DAO.PedidoDAO;
import com.foodly.DAO.PedidoItemDAO;
import com.foodly.Models.CarrinhoItem;
import com.foodly.Models.Pedido;
import com.foodly.Models.PedidoItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoController {

    private final PedidoDAO pedidoDAO;
    private final PedidoItemDAO pedidoItemDAO;
    private final CarrinhoItemDAO carrinhoItemDAO;

    public PedidoController() {
        this.pedidoDAO = new PedidoDAO();
        this.pedidoItemDAO = new PedidoItemDAO();
        this.carrinhoItemDAO = new CarrinhoItemDAO();
    }

    /**
     * Cria um pedido a partir de um carrinho já fechado.
     * - Copia itens do carrinho para pedido_itens
     * - Calcula valor total
     * - Define status inicial como "novo"
     *
     * H4 -> checkout / H3 -> cria pedido para o restaurante gerenciar.
     */
    public Pedido criarPedidoAPartirDoCarrinho(int clienteId,
                                               int restauranteId,
                                               int carrinhoId,
                                               String enderecoEntrega) {

        try {
            // 1) Busca itens do carrinho
            List<CarrinhoItem> itensCarrinho = carrinhoItemDAO.listarPorCarrinho(carrinhoId);
            if (itensCarrinho.isEmpty()) {
                System.err.println("Carrinho vazio, não é possível criar pedido.");
                return null;
            }

            // 2) Calcula valor total
            double total = 0.0;
            for (CarrinhoItem ci : itensCarrinho) {
                total += ci.getQuantidade() * ci.getPrecoUnitario();
            }

            // 3) Cria o pedido
            Pedido pedido = new Pedido();
            pedido.setClienteId(clienteId);
            pedido.setRestauranteId(restauranteId);
            pedido.setCarrinhoId(carrinhoId);
            pedido.setValorTotal(total);
            pedido.setStatus("novo");  // status inicial
            pedido.setEnderecoEntrega(enderecoEntrega);
            pedido.setCriadoEm(LocalDateTime.now());
            pedido.setAtualizadoEm(LocalDateTime.now());

            int pedidoId = pedidoDAO.salvar(pedido);
            pedido.setId(pedidoId);

            // 4) Cria os itens do pedido com base nos itens do carrinho
            for (CarrinhoItem ci : itensCarrinho) {
                PedidoItem pi = new PedidoItem();
                pi.setPedidoId(pedidoId);
                pi.setProdutoId(ci.getProdutoId());
                pi.setQuantidade(ci.getQuantidade());
                pi.setPrecoUnitario(ci.getPrecoUnitario());

                pedidoItemDAO.salvar(pi);
            }

            // 5) (Simulação) Notificação para o cliente
            System.out.println("📲 Notificação: seu pedido #" + pedidoId + " foi criado com sucesso!");

            return pedido;

        } catch (SQLException e) {
            System.err.println("Erro ao criar pedido: " + e.getMessage());
            return null;
        }
    }

    /**
     * Atualiza o status do pedido (usado pelo restaurante para H3).
     * Ex: 'preparando', 'pronto para entrega', 'entregue'
     */
    public void atualizarStatusPedido(int pedidoId, String novoStatus) {
        String sql = "UPDATE pedidos SET status = ?, atualizado_em = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(3, pedidoId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                // (Simulação) Notificação para o cliente
                System.out.println("📲 Notificação: seu pedido #" + pedidoId +
                                   " agora está com status: " + novoStatus);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status do pedido: " + e.getMessage());
        }
    }
}
