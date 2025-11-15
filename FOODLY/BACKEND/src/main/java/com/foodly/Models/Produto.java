package models;

public class Produto {

    private Integer id;
    private Integer restauranteId;
    private String nome;
    private String descricao;
    private double preco;
    private boolean ativo;

    public Produto() {
        this.ativo = true;
    }

    public Produto(Integer id, Integer restauranteId, String nome, String descricao, double preco, boolean ativo) {
        this.id = id;
        this.restauranteId = restauranteId;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Integer restauranteId) { this.restauranteId = restauranteId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
