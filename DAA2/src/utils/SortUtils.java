package utils;

import model.Transaction;

import java.time.LocalDate;
import java.util.List;

public class SortUtils {

    // QuickSort by amount ascending
    public static void quickSortByAmount(List<Transaction> list, int low, int high) {
        if (low < high) {
            int pi = partitionByAmount(list, low, high);
            quickSortByAmount(list, low, pi - 1);
            quickSortByAmount(list, pi + 1, high);
        }
    }

    private static int partitionByAmount(List<Transaction> list, int low, int high) {
        double pivot = list.get(high).getAmount();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getAmount() <= pivot) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    // QuickSort by date ascending
    public static void quickSortByDate(List<Transaction> list, int low, int high) {
        if (low < high) {
            int pi = partitionByDate(list, low, high);
            quickSortByDate(list, low, pi - 1);
            quickSortByDate(list, pi + 1, high);
        }
    }

    private static int partitionByDate(List<Transaction> list, int low, int high) {
        LocalDate pivot = list.get(high).getDate();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j).getDate().compareTo(pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Transaction> list, int i, int j) {
        Transaction temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
