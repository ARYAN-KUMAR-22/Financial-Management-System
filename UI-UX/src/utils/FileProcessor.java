package utils;

import javafx.scene.chart.*;
import model.Transaction;

import java.io.*;
import java.util.*;

public class FileProcessor {
    public static List<Transaction> parseCSV(File file) {
        List<Transaction> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                list.add(new Transaction(parts[0], parts[1], parts[2], Double.parseDouble(parts[3])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void updateCharts(List<Transaction> transactions, PieChart pieChart, BarChart<String, Number> barChart) {
        Map<String, Double> categoryMap = new HashMap<>();
        Map<String, Double> monthMap = new TreeMap<>();

        for (Transaction t : transactions) {
            categoryMap.merge(t.categoryProperty().get(), t.amountProperty().get(), Double::sum);
            String month = t.dateProperty().get().substring(0, 7); // YYYY-MM
            monthMap.merge(month, t.amountProperty().get(), Double::sum);
        }

        pieChart.getData().clear();
        categoryMap.forEach((cat, val) -> pieChart.getData().add(new PieChart.Data(cat, val)));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        monthMap.forEach((mon, val) -> series.getData().add(new XYChart.Data<>(mon, val)));
        barChart.getData().clear();
        barChart.getData().add(series);
    }
}
