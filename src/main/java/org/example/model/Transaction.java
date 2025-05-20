package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;
    private String category;
    private String description;
    private double value;
    private String type;

    public Transaction(Long id, String date, String category, String description, double value, String type) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.description = description;
        this.value = value;
        this.type = type;
    }

    public Transaction() {

    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getDate() { return date; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public double getValue() { return value; }
    public String getType() { return type; }
}
