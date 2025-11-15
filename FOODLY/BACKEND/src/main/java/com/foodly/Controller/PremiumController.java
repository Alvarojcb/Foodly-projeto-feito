package controllers;

import dao.AssinaturaPremiumDAO;
import dao.PlanoPremiumDAO;
import models.AssinaturaPremium;
import models.PlanoPremium;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class PremiumController {

    private final PlanoPremiumDAO planoPremiumDAO;
    private final AssinaturaPremiumDAO assinaturaPremiumDAO;

    public PremiumController() {
        this.planoPremiumDAO = new PlanoPremiumDAO();
        this.assinaturaPremiumDAO = new AssinaturaPremiumDAO();
    }

    /**
     * Cria um plano premium (caso queira cadastrar no sistema).
     */
    public PlanoPremium criarPlano(String nome,
                                   String descricao,
                                   double valorMensal,
                                   int duracaoDias) {

        PlanoPremium p = new PlanoPremium();
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setValorMensal(valorMensal);
        p.setDuracaoDias(duracaoDias);
        p.setAtivo(true);
        p.setCriadoEm(LocalDateTime.now());

        try {
            int id = planoPremiumDAO.salvar(p);
            p.setId(id);
            System.out.println("💎 Plano premium criado: " + nome);
            return p;
        } catch (SQLException e) {
            System.err.println("Erro ao criar plano premium: " + e.getMessage());
            return null;
        }
    }

    /**
     * H9 - Cliente assina um plano premium.
     */
    public AssinaturaPremium assinarPlano(int clienteId,
                                          int planoId,
                                          String metodoPagamento,
                                          String referenciaPagamento) {

        LocalDateTime inicio = LocalDateTime.now();
        // Não calculei fim com base na duração para simplificar o esqueleto
        LocalDateTime fim = null;

        AssinaturaPremium a = new AssinaturaPremium();
        a.setClienteId(clienteId);
        a.setPlanoId(planoId);
        a.setStatus("ativa");
        a.setDataInicio(inicio);
        a.setDataFim(fim);
        a.setRenovacaoAutomatica(true);
        a.setMetodoPagamento(metodoPagamento);
        a.setReferenciaPagamento(referenciaPagamento);
        a.setCriadoEm(LocalDateTime.now());

        try {
            int id = assinaturaPremiumDAO.salvar(a);
            a.setId(id);
            System.out.println("💳 Assinatura premium criada para cliente #" + clienteId);
            return a;
        } catch (SQLException e) {
            System.err.println("Erro ao criar assinatura premium: " + e.getMessage());
            return null;
        }
    }
}
