package model;

import java.util.*;

public class Analyzer {
    public static Map<String, Double> calculateSpendingByCategory(List<Transaction> transactions) {
        Map<String, Double> spendMap = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.getTransactionType().equalsIgnoreCase("debit")) {
                spendMap.put(t.getCategory(), spendMap.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
            }
        }
        return spendMap;
    }
}
