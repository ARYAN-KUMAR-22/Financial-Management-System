package model;

import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.data.Tuple;
import smile.data.formula.Formula;

import java.util.*;

public class Predictor {
    private static RandomForest model;
    private static Map<String, Integer> categoryMap = new HashMap<>();
    private static Map<Integer, String> reverseCategoryMap = new HashMap<>();

    public static void trainModel(List<Transaction> transactions) {
        List<Double> descriptionLengths = new ArrayList<>();
        List<Double> amounts = new ArrayList<>();
        List<Integer> categoryLabels = new ArrayList<>();

        for (Transaction t : transactions) {
            descriptionLengths.add((double) t.getDescription().length());
            amounts.add(t.getAmount());

            String category = t.getCategory();
            if (!categoryMap.containsKey(category)) {
                int index = categoryMap.size();
                categoryMap.put(category, index);
                reverseCategoryMap.put(index, category);
            }

            categoryLabels.add(categoryMap.get(category));
        }

        double[] descArray = descriptionLengths.stream().mapToDouble(Double::doubleValue).toArray();
        double[] amtArray = amounts.stream().mapToDouble(Double::doubleValue).toArray();
        int[] catArray = categoryLabels.stream().mapToInt(Integer::intValue).toArray();

        // Create DataFrame using arrays and column names (older API style)
        DataFrame df = DataFrame.of(new double[][] { descArray, amtArray, Arrays.stream(catArray).asDoubleStream().toArray() },
                                   "description_length", "amount", "category");

        Formula formula = Formula.lhs("category");

        model = RandomForest.fit(formula, df);
    }

    public static String predict(String description, double amount) {
        if (model == null) return "Model not trained";
	
        double[] descArray = new double[]{ description.length() };
        double[] amtArray = new double[]{ amount };

        DataFrame input = DataFrame.of(new double[][] { descArray, amtArray }, "description_length", "amount");
        Tuple tuple = input.get(0);

        int pred = model.predict(tuple);

        return reverseCategoryMap.getOrDefault(pred, "Unknown");
    }
}	
