package org.example.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "transactions")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private String tipo;

    public Transacao(Long id, String data, String categoria, String descricao, double valor, String tipo) {
        this.id = id;
        this.data = data;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Transacao(String data, String categoria, String descricao, double valor, String tipo) {
        this.data = data;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Transacao() {

    }

    // Getters

    public Long getId() {
        return id;
    }
    public String getData() { return data; }
    public String getCategoria() { return categoria; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public String getTipo() { return tipo; }
}
