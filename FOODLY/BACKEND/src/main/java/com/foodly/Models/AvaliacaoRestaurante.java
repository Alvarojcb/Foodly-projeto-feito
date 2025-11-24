package com.foodly.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacoes_restaurante")
public class AvaliacaoRestaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer clienteId;
    
    @Column(nullable = false)
    private Integer restauranteId;
    
    @Column(nullable = false)
    private Integer pedidoId;
    
    @Column(nullable = false)
    private int nota;
    
    @Column(columnDefinition = "TEXT")
    private String comentario;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public AvaliacaoRestaurante() {
    }

    public AvaliacaoRestaurante(Integer id,
                                Integer clienteId,
                                Integer restauranteId,
                                Integer pedidoId,
                                int nota,
                                String comentario,
                                LocalDateTime criadoEm) {
        this.id = id;
        this.clienteId = clienteId;
        this.restauranteId = restauranteId;
        this.pedidoId = pedidoId;
        this.nota = nota;
        this.comentario = comentario;
        this.criadoEm = criadoEm;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }

    public Integer getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Integer restauranteId) { this.restauranteId = restauranteId; }

    public Integer getPedidoId() { return pedidoId; }
    public void setPedidoId(Integer pedidoId) { this.pedidoId = pedidoId; }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
