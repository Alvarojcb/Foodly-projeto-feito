package com.foodly.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private Integer usuarioId;
    
    private String enderecoPadrao;

    public Cliente() {
    }

    public Cliente(Integer id, Integer usuarioId, String enderecoPadrao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.enderecoPadrao = enderecoPadrao;
    }

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEnderecoPadrao() {
        return enderecoPadrao;
    }

    public void setEnderecoPadrao(String enderecoPadrao) {
        this.enderecoPadrao = enderecoPadrao;
    }
}
