package utils;

import model.Transaction;
import java.time.LocalDate;
import java.util.List;

public class SearchUtils {

    public static int binarySearchByDate(List<Transaction> transactions, LocalDate target) {
        int left = 0;
        int right = transactions.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            LocalDate midDate = transactions.get(mid).getDate();

            if (midDate.equals(target)) return mid;
            else if (midDate.isBefore(target)) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}
