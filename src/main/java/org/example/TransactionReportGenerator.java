package org.example;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class TransactionReportGenerator {
    public static void printTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printPeriodExpensesReport(LocalDate from, LocalDate to,
                                                 Optional<Transaction> largest,
                                                 Optional<Transaction> smallest) {
        System.out.println("Витрати за період " + from + " - " + to + ":");
        if (largest.isEmpty()) {
            System.out.println("Витрат за цей період немає.");
            return;
        }
        System.out.println("Найбільша витрата: " + largest.get());
        System.out.println("Найменша витрата: " + smallest.get());
    }

    public static void printCategoryExpensesReport(Map<String, Double> totals) {
        System.out.println("Сумарні витрати за категоріями (* = 1000 грн):");
        for (Map.Entry<String, Double> entry : totals.entrySet()) {
            printExpenseBar(entry.getKey(), entry.getValue());
        }
    }

    public static void printMonthlyExpensesReport(Map<YearMonth, Double> totals) {
        System.out.println("Сумарні витрати за місяцями (* = 1000 грн):");
        for (Map.Entry<YearMonth, Double> entry : totals.entrySet()) {
            String monthYear = entry.getKey().format(DateTimeFormatter.ofPattern("MM-yyyy"));
            printExpenseBar(monthYear, entry.getValue());
        }
    }

    private static void printExpenseBar(String label, double amount) {
        System.out.print(label + ": " + amount + " ");
        for (int i = 0; i < (int) (amount / 1000); i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
