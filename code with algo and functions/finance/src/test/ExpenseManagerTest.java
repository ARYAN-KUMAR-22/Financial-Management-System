package test;
import controller.ExpenseManagement;
import model.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class ExpenseManagerTest {

    @Test
    public void testAddTransactionAndBudget() {
        Budget budget = new Budget(1000);
        ExpenseManagement manager = new ExpenseManagement(budget);

        Category food = new Category("Food", 300);
        Transaction t = new Transaction(150, food, LocalDate.now(), "Pizza");

        manager.addTransaction(t);

        assertEquals(850, budget.getRemaining(), 0.001);
        List<Transaction> transactions = manager.getTransactionMap().get(food);
        assertNotNull(transactions);
        assertTrue(transactions.contains(t));
    }
}

