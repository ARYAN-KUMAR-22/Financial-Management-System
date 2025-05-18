package model;

public class Category {
    private String name;
    private double spendingLimit;

    public Category(String name, double spendingLimit) {
        this.name = name;
        this.spendingLimit = spendingLimit;
    }

    public String getName() { return name; }
    public double getSpendingLimit() { return spendingLimit; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Category)) return false;
        Category other = (Category) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

