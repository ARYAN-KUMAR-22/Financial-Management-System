package engine;

import model.Analyzer;
import model.Predictor;
import model.Transaction;
import utils.ExcelParser;

import java.io.File;
import java.util.List;
import java.util.Map;

public class FinanceEngine {
    private static List<Transaction> transactions;

    public static void loadTransactions(File file) {
        transactions = ExcelParser.parse(file);
        Predictor.trainModel(transactions);
    }

    public static Map<String, Double> analyze() {
        return Analyzer.calculateSpendingByCategory(transactions);
    }

    public static String predict(String desc, double amt) {
        return Predictor.predict(desc, amt);
    }

    public static List<Transaction> getTransactions() {
        return transactions;
    }
}
