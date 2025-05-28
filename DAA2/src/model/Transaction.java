package model;

import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String description;
    private double amount;
    private String transactionType;
    private String category;
    private String accountName;
    private String month;

    public Transaction(LocalDate date, String description, double amount,
                       String transactionType, String category, String accountName, String month) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.transactionType = transactionType;
        this.category = category;
        this.accountName = accountName;
        this.month = month;
    }

    // Getters
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getTransactionType() { return transactionType; }
    public String getCategory() { return category; }
    public String getAccountName() { return accountName; }
    public String getMonth() { return month; }
}
