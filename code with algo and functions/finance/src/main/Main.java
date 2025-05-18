
package main;

import controller.ExpenseManagement;
import model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Budget budget = new Budget(100000); // Budget in Rupees
        ExpenseManagement expenseManager = new ExpenseManagement(budget);

        Category food = new Category("Food", 5000);
        Category travel = new Category("Travel", 3000);

        expenseManager.addTransaction(new Transaction(2500, food, LocalDate.now(), "Groceries"));
        expenseManager.addTransaction(new Transaction(700, travel, LocalDate.now(), "Taxi"));
        expenseManager.addTransaction(new Transaction(1200, food, LocalDate.now(), "Dinner"));

        expenseManager.printTopExpenses();
        Report.generateSummary(expenseManager.getTransactionMap());

        System.out.println("\nRemaining Budget: ₹" + budget.getRemaining());
    }
}

