package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.chart.*;
import javafx.stage.FileChooser;
import model.Transaction;
import utils.FileProcessor;

import java.io.*;
import java.util.*;

public class MainController {
    @FXML private TableView<Transaction> transactionTable;
    @FXML private TableColumn<Transaction, String> dateCol, descCol, categoryCol;
    @FXML private TableColumn<Transaction, Double> amountCol;
    @FXML private PieChart categoryChart;
    @FXML private BarChart<String, Number> monthlyChart;
    @FXML private LineChart<String, Number> predictionChart;

    private File uploadedFile;

    @FXML
    public void initialize() {
        dateCol.setCellValueFactory(c -> c.getValue().dateProperty());
        descCol.setCellValueFactory(c -> c.getValue().descriptionProperty());
        categoryCol.setCellValueFactory(c -> c.getValue().categoryProperty());
        amountCol.setCellValueFactory(c -> c.getValue().amountProperty().asObject());
    }

    public void handleUpload() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Upload Financial Dataset");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        uploadedFile = chooser.showOpenDialog(null);
        if (uploadedFile != null) {
            List<Transaction> list = FileProcessor.parseCSV(uploadedFile);
            transactionTable.getItems().setAll(list);
        }
    }

    public void handleAnalyze() {
        List<Transaction> list = transactionTable.getItems();
        FileProcessor.updateCharts(list, categoryChart, monthlyChart);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Suggestion");
        alert.setHeaderText("Spending Analysis");
        alert.setContentText("Consider reducing entertainment expenses to stay within budget.");
        alert.showAndWait();
    }

    public void handlePredict() {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "predictor.py", uploadedFile.getAbsolutePath());
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            String line;
            int day = 1;
            while ((line = reader.readLine()) != null) {
                series.getData().add(new XYChart.Data<>("Day " + day, Double.parseDouble(line)));
                day++;
            }
            predictionChart.getData().clear();
            predictionChart.getData().add(series);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
