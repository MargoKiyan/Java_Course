package org.example;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class TransactionAnalyzer {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static double calculateTotalBalance(List<Transaction> transactions) {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate(), DATE_FORMAT);
            String transactionMonthYear = date.format(DateTimeFormatter.ofPattern("MM-yyyy"));
            if (transactionMonthYear.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public static Optional<Transaction> findLargestExpense(List<Transaction> transactions,
                                                          LocalDate from, LocalDate to) {
        return expensesInPeriod(transactions, from, to)
                .min(Comparator.comparing(Transaction::getAmount));
    }

    public static Optional<Transaction> findSmallestExpense(List<Transaction> transactions,
                                                           LocalDate from, LocalDate to) {
        return expensesInPeriod(transactions, from, to)
                .max(Comparator.comparing(Transaction::getAmount));
    }

    public static Map<String, Double> calculateExpensesByCategory(List<Transaction> transactions) {
        Map<String, Double> totals = new TreeMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                totals.merge(transaction.getDescription(), -transaction.getAmount(), Double::sum);
            }
        }
        return totals;
    }

    public static Map<YearMonth, Double> calculateExpensesByMonth(List<Transaction> transactions) {
        Map<YearMonth, Double> totals = new TreeMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                totals.merge(YearMonth.from(dateOf(transaction)), -transaction.getAmount(), Double::sum);
            }
        }
        return totals;
    }

    private static Stream<Transaction> expensesInPeriod(List<Transaction> transactions,
                                                        LocalDate from, LocalDate to) {
        return transactions.stream().filter(t -> t.getAmount() < 0).filter(t -> {
            LocalDate date = dateOf(t);
            return !date.isBefore(from) && !date.isAfter(to);
        });
    }

    private static LocalDate dateOf(Transaction transaction) {
        return LocalDate.parse(transaction.getDate(), DATE_FORMAT);
    }
}
