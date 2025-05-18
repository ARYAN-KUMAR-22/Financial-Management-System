package controller;

import model.*;

import java.util.*;

public class ExpenseManagement {
    private Map<Category, List<Transaction>> transactionMap = new HashMap<>();
    private Budget budget;

    public ExpenseManagement(Budget budget) {
        this.budget = budget;
    }

    public void addTransaction(Transaction transaction) {
        transactionMap.computeIfAbsent(transaction.getCategory(), k -> new ArrayList<>()).add(transaction);
        budget.deduct(transaction.getAmount());
    }

    public void printTopExpenses() {
        PriorityQueue<Transaction> queue = new PriorityQueue<>(
            (a, b) -> Double.compare(b.getAmount(), a.getAmount())
        );
        for (List<Transaction> list : transactionMap.values()) {
            queue.addAll(list);
        }

        System.out.println("\nTop Expenses:");
        int count = 0;
        while (!queue.isEmpty() && count < 5) {
            Transaction t = queue.poll();
            System.out.println(t.getDescription() + " - ₹" + t.getAmount());
            count++;
        }
    }

    public Map<Category, List<Transaction>> getTransactionMap() {
        return transactionMap;
    }
}

