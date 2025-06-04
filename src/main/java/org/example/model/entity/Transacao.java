package org.example.model.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private String tipo;

    public Transacao(Long idTransacao, LocalDate data, String categoria, String descricao, double valor, String tipo) {
        this.idTransacao = idTransacao;
        this.data = data;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Transacao(LocalDate data, String categoria, String descricao, double valor, String tipo) {
        this.data = data;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Transacao() {

    }

    // Getters
    public Long getIdTransacao() {
        return idTransacao;
    }
    public LocalDate getData() { return data; }
    public String getCategoria() { return categoria; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public String getTipo() { return tipo; }
    public Usuario getUsuario() {
        return usuario;
    }

    // Setters

    public void setId(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
