package controller;

import engine.FinanceEngine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import model.Transaction;
import utils.SearchUtils;
import utils.SortUtils;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MainViewController {

    @FXML private Button uploadButton;
    @FXML private Button analyzeButton;
    @FXML private Button predictButton;
    @FXML private Button sortButton;
    @FXML private Button searchButton;
    @FXML private TextArea outputArea;
    @FXML private DatePicker datePicker;

    private File selectedFile;

    @FXML
    public void initialize() {
        uploadButton.setText("Upload Transaction");
    }

    @FXML
    void handleUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Transaction");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        selectedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
        if (selectedFile != null) {
            FinanceEngine.loadTransactions(selectedFile);
            outputArea.setText("Transactions loaded successfully.");
        } else {
            outputArea.setText("File selection cancelled.");
        }
    }

    @FXML
    void handleAnalyze(ActionEvent event) {
        Map<String, Double> result = FinanceEngine.analyze();
        outputArea.clear();
        outputArea.appendText("Spending by Category:\n");
        result.forEach((k, v) -> outputArea.appendText(k + ": $" + v + "\n"));
    }

    @FXML
    void handlePredict(ActionEvent event) {
        String exampleDescription = "Uber Ride";
        double exampleAmount = 22.50;
        String category = FinanceEngine.predict(exampleDescription, exampleAmount);
        outputArea.setText("Predicted category for \"" + exampleDescription + "\": " + category);
    }

    @FXML
    void handleSort(ActionEvent event) {
        List<Transaction> transactions = FinanceEngine.getTransactions();
        if (transactions == null || transactions.isEmpty()) {
            outputArea.setText("No transactions loaded.");
            return;
        }

        SortUtils.quickSortByAmount(transactions, 0, transactions.size() - 1);

        outputArea.clear();
        outputArea.appendText("Transactions sorted by amount:\n");
        for (Transaction t : transactions) {
            outputArea.appendText(t.getDate() + " | " + t.getDescription() + " | $" + t.getAmount() + "\n");
        }
    }

    @FXML
    void handleSearch(ActionEvent event) {
        List<Transaction> transactions = FinanceEngine.getTransactions();
        if (transactions == null || transactions.isEmpty()) {
            outputArea.setText("No transactions loaded.");
            return;
        }

        if (datePicker.getValue() == null) {
            outputArea.setText("Please select a date to search.");
            return;
        }

        // Sort by date for binary search
        SortUtils.quickSortByDate(transactions, 0, transactions.size() - 1);

        LocalDate searchDate = datePicker.getValue();

        int idx = SearchUtils.binarySearchByDate(transactions, searchDate);

        if (idx != -1) {
            Transaction t = transactions.get(idx);
            outputArea.setText("Transaction found:\n" +
                    t.getDate() + " | " + t.getDescription() + " | $" + t.getAmount() + " | " + t.getCategory());
        } else {
            outputArea.setText("No transaction found on date: " + searchDate);
        }
    }
}
