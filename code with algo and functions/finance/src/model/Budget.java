package model;

public class Budget {
    private double monthlyLimit;
    private double remaining;

    public Budget(double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
        this.remaining = monthlyLimit;
    }

    public void deduct(double amount) {
        remaining -= amount;
    }

    public double getMonthlyLimit() { return monthlyLimit; }
    public double getRemaining() { return remaining; }
}

