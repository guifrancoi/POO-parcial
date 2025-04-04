package org.example.model;

public class Transaction {
    private String date;
    private String category;
    private String description;
    private double value;
    private String type;

    public Transaction(String date, String category, String description, double value, String type) {
        this.date = date;
        this.category = category;
        this.description = description;
        this.value = value;
        this.type = type;
    }

    // Getters
    public String getDate() { return date; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public double getValue() { return value; }
    public String getType() { return type; }
}
