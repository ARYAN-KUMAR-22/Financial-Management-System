package model;

import java.util.*;

public class Report {
    public static void generateSummary(Map<Category, List<Transaction>> data) {
        for (Map.Entry<Category, List<Transaction>> entry : data.entrySet()) {
            Category category = entry.getKey();
            List<Transaction> transactions = entry.getValue();

            double total = 0;
            for (Transaction t : transactions) {
                total += t.getAmount();
            }

            System.out.println("Category: " + category.getName() + " | Total Spent: ₹" + total);
        }
    }
}

