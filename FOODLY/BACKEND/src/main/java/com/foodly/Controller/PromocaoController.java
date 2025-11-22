package com.foodly.Controller;

import com.foodly.DAO.PromocaoClienteDAO;
import com.foodly.DAO.PromocaoDAO;
import com.foodly.Models.Promocao;
import com.foodly.Models.PromocaoCliente;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class PromocaoController {

    private final PromocaoDAO promocaoDAO;
    private final PromocaoClienteDAO promocaoClienteDAO;

    public PromocaoController() {
        this.promocaoDAO = new PromocaoDAO();
        this.promocaoClienteDAO = new PromocaoClienteDAO();
    }

    /**
     * Cria uma nova promoção (pode ser geral ou de um restaurante específico).
     */
    public Promocao criarPromocao(Integer restauranteId,
                                  String titulo,
                                  String descricao,
                                  String tipoDesconto,
                                  Double valorDesconto,
                                  LocalDateTime dataInicio,
                                  LocalDateTime dataFim) {

        Promocao p = new Promocao();
        p.setRestauranteId(restauranteId);
        p.setTitulo(titulo);
        p.setDescricao(descricao);
        p.setTipoDesconto(tipoDesconto);
        p.setValorDesconto(valorDesconto);
        p.setDataInicio(dataInicio);
        p.setDataFim(dataFim);
        p.setAtivo(true);
        p.setCriadoEm(LocalDateTime.now());

        try {
            int id = promocaoDAO.salvar(p);
            p.setId(id);
            System.out.println("🎁 Promoção criada com sucesso: " + titulo);
            return p;
        } catch (SQLException e) {
            System.err.println("Erro ao criar promoção: " + e.getMessage());
            return null;
        }
    }

    /**
     * H8 - Vincula uma promoção a um cliente (oferta personalizada).
     */
    public void atribuirPromocaoParaCliente(int promocaoId, int clienteId) {

        PromocaoCliente pc = new PromocaoCliente();
        pc.setPromocaoId(promocaoId);
        pc.setClienteId(clienteId);
        pc.setResgatada(false);
        pc.setResgatadaEm(null);

        try {
            promocaoClienteDAO.salvar(pc);
            System.out.println("🎯 Promoção #" + promocaoId + " atribuída ao cliente #" + clienteId);
        } catch (SQLException e) {
            System.err.println("Erro ao atribuir promoção ao cliente: " + e.getMessage());
        }
    }

    /**
     * Marca a promoção como resgatada pelo cliente.
     */
    public void resgatarPromocao(int promocaoClienteId) {
        // Esqueleto: aqui você poderia fazer um UPDATE em promocoes_clientes.resgatada/resgatada_em
        System.out.println("🧾 (TODO) Implementar resgate da promoção para id=" + promocaoClienteId);
    }
}
