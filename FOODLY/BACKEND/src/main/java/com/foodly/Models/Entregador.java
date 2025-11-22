package com.foodly.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregadores")
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer usuarioId;
    
    @Column(nullable = false)
    private String veiculoTipo;
    
    @Column(nullable = false)
    private String documento;
    
    @Column(nullable = false)
    private boolean ativo;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public Entregador() {
        this.ativo = true;
    }

    public Entregador(Integer id,
                      Integer usuarioId,
                      String veiculoTipo,
                      String documento,
                      boolean ativo,
                      LocalDateTime criadoEm) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.veiculoTipo = veiculoTipo;
        this.documento = documento;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public String getVeiculoTipo() { return veiculoTipo; }
    public void setVeiculoTipo(String veiculoTipo) { this.veiculoTipo = veiculoTipo; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
