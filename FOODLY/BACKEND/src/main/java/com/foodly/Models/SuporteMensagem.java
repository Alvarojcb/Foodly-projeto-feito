package com.foodly.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "suporte_mensagens")
public class SuporteMensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer atendimentoId;
    
    @Column(nullable = false)
    private String remetenteTipo;
    
    @Column(nullable = false)
    private Integer remetenteUsuarioId;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String mensagem;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime enviadoEm;

    public SuporteMensagem() {
    }

    public SuporteMensagem(Integer id,
                           Integer atendimentoId,
                           String remetenteTipo,
                           Integer remetenteUsuarioId,
                           String mensagem,
                           LocalDateTime enviadoEm) {
        this.id = id;
        this.atendimentoId = atendimentoId;
        this.remetenteTipo = remetenteTipo;
        this.remetenteUsuarioId = remetenteUsuarioId;
        this.mensagem = mensagem;
        this.enviadoEm = enviadoEm;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getAtendimentoId() { return atendimentoId; }
    public void setAtendimentoId(Integer atendimentoId) { this.atendimentoId = atendimentoId; }

    public String getRemetenteTipo() { return remetenteTipo; }
    public void setRemetenteTipo(String remetenteTipo) { this.remetenteTipo = remetenteTipo; }

    public Integer getRemetenteUsuarioId() { return remetenteUsuarioId; }
    public void setRemetenteUsuarioId(Integer remetenteUsuarioId) { this.remetenteUsuarioId = remetenteUsuarioId; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public LocalDateTime getEnviadoEm() { return enviadoEm; }
    public void setEnviadoEm(LocalDateTime enviadoEm) { this.enviadoEm = enviadoEm; }
}
