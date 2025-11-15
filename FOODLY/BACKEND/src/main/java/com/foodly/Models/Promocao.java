package models;

import java.time.LocalDateTime;

public class Promocao {

    private Integer id;
    private Integer restauranteId;      // pode ser null (promoção geral)
    private String titulo;
    private String descricao;
    private String tipoDesconto;        // 'percentual', 'valor', 'frete_gratis'
    private Double valorDesconto;       // depende do tipoDesconto
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private boolean ativo;
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
