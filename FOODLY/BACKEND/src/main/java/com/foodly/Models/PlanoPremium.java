package models;

import java.time.LocalDateTime;

public class PlanoPremium {

    private Integer id;
    private String nome;
    private String descricao;
    private double valorMensal;
    private int duracaoDias;           // geralmente 30
    private boolean ativo;
    private LocalDateTime criadoEm;

    public PlanoPremium() {
        this.ativo = true;
        this.duracaoDias = 30;
    }

    public PlanoPremium(Integer id,
                        String nome,
                        String descricao,
                        double valorMensal,
                        int duracaoDias,
                        boolean ativo,
                        LocalDateTime criadoEm) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorMensal = valorMensal;
        this.duracaoDias = duracaoDias;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
    }

    // Getters e Setters

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValorMensal() { return valorMensal; }
    public void setValorMensal(double valorMensal) { this.valorMensal = valorMensal; }

    public int getDuracaoDias() { return duracaoDias; }
    public void setDuracaoDias(int duracaoDias) { this.duracaoDias = duracaoDias; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}
