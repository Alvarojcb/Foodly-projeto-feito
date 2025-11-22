package com.foodly.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "promocoes")
public class Promocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer restauranteId;
    
    @Column(nullable = false)
    private String titulo;
    
    private String descricao;
    
    @Column(nullable = false)
    private String tipoDesconto;
    
    private Double valorDesconto;
    
    @Column(nullable = false)
    private LocalDateTime dataInicio;
    
    @Column(nullable = false)
    private LocalDateTime dataFim;
    
    @Column(nullable = false)
    private boolean ativo;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public Promocao() {
        this.ativo = true;
    }

    public Promocao(Integer id,
                    Integer restauranteId,
                    String titulo,
                    String descricao,
                    String tipoDesconto,
                    Double valorDesconto,
                    LocalDateTime dataInicio,
                    LocalDateTime dataFim,
                    boolean ativo,
                    LocalDateTime criadoEm) {
        this.id = id;
        this.restauranteId = restauranteId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipoDesconto = tipoDesconto;
        this.valorDesconto = valorDesconto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Integer restauranteId) { this.restauranteId = restauranteId; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getTipoDesconto() { return tipoDesconto; }
    public void setTipoDesconto(String tipoDesconto) { this.tipoDesconto = tipoDesconto; }

    public Double getValorDesconto() { return valorDesconto; }
    public void setValorDesconto(Double valorDesconto) { this.valorDesconto = valorDesconto; }

    public LocalDateTime getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDateTime dataInicio) { this.dataInicio = dataInicio; }

    public LocalDateTime getDataFim() { return dataFim; }
    public void setDataFim(LocalDateTime dataFim) { this.dataFim = dataFim; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
