package model;

import java.time.LocalDate;

public class Transaction {
    private double amount;
    private Category category;
    private LocalDate date;
    private String description;

    public Transaction(double amount, Category category, LocalDate date, String description) {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public double getAmount() { return amount; }
    public Category getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
}

