// Практична робота №2 Киян М. ПД-31

package org.example;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);
        TransactionReportGenerator.printTransactions(transactions);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);
        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        LocalDate from = LocalDate.of(2023, 12, 1);
        LocalDate to = LocalDate.of(2024, 1, 31);
        TransactionReportGenerator.printPeriodExpensesReport(from, to,
                TransactionAnalyzer.findLargestExpense(transactions, from, to),
                TransactionAnalyzer.findSmallestExpense(transactions, from, to));
        TransactionReportGenerator.printCategoryExpensesReport(
                TransactionAnalyzer.calculateExpensesByCategory(transactions));
        TransactionReportGenerator.printMonthlyExpensesReport(
                TransactionAnalyzer.calculateExpensesByMonth(transactions));
    }
}
