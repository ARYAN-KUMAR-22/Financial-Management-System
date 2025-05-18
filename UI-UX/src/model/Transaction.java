package model;

import javafx.beans.property.*;

public class Transaction {
    private final StringProperty date, description, category;
    private final DoubleProperty amount;

    public Transaction(String date, String description, String category, double amount) {
        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
        this.category = new SimpleStringProperty(category);
        this.amount = new SimpleDoubleProperty(amount);
    }

    public StringProperty dateProperty() { return date; }
    public StringProperty descriptionProperty() { return description; }
    public StringProperty categoryProperty() { return category; }
    public DoubleProperty amountProperty() { return amount; }
}
