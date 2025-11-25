package com.foodly.Models;

import java.time.LocalDateTime;

public class AssinaturaPremium {

    private Integer id;
    private Integer clienteId;
    private Integer planoId;
    private String status;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private boolean renovacaoAutomatica;
    private String metodoPagamento;
    private String referenciaPagamento;
    private LocalDateTime criadoEm;

    public AssinaturaPremium() {
        this.renovacaoAutomatica = true;
    }

    public AssinaturaPremium(Integer id,
                             Integer clienteId,
                             Integer planoId,
                             String status,
                             LocalDateTime dataInicio,
                             LocalDateTime dataFim,
                             boolean renovacaoAutomatica,
                             String metodoPagamento,
                             String referenciaPagamento,
                             LocalDateTime criadoEm) {
        this.id = id;
        this.clienteId = clienteId;
        this.planoId = planoId;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.renovacaoAutomatica = renovacaoAutomatica;
        this.metodoPagamento = metodoPagamento;
        this.referenciaPagamento = referenciaPagamento;
        this.criadoEm = criadoEm;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }

    public Integer getPlanoId() { return planoId; }
    public void setPlanoId(Integer planoId) { this.planoId = planoId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }

    public boolean isRenovacaoAutomatica() { return renovacaoAutomatica; }
    public void setRenovacaoAutomatica(boolean renovacaoAutomatica) { this.renovacaoAutomatica = renovacaoAutomatica; }

    public String getMetodoPagamento() { return metodoPagamento; }
    public void setMetodoPagamento(String metodoPagamento) { this.metodoPagamento = metodoPagamento; }

    public String getReferenciaPagamento() { return referenciaPagamento; }
    public void setReferenciaPagamento(String referenciaPagamento) { this.referenciaPagamento = referenciaPagamento; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
