package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TransactionAnalyzerTest {
    private static Transaction t(String date, double amount, String description) {
        return new Transaction(date, amount, description);
    }

    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("2023-01-01", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("2023-01-02", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("2023-01-03", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);
        double result = TransactionAnalyzer.calculateTotalBalance(transactions);
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    void balancesEmptyNegativeAndFractionalAmounts() {
        assertEquals(0, TransactionAnalyzer.calculateTotalBalance(List.of()));
        assertEquals(-0.3, TransactionAnalyzer.calculateTotalBalance(List.of(
                t("01-01-2024", -0.1, "А"), t("01-01-2024", -0.2, "Б"))), 1e-9);
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);
        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");
        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    void monthIncludesYearAndCountsIncomeAndZero() {
        var transactions = List.of(t("01-01-2023", -20, "А"),
                t("01-01-2024", 50, "Б"), t("02-01-2024", 0, "В"));
        assertEquals(2, TransactionAnalyzer.countTransactionsByMonth(transactions, "01-2024"));
        assertEquals(0, TransactionAnalyzer.countTransactionsByMonth(List.of(), "01-2024"));
    }

    @Test
    void findsExactlyTenLargestExpensesWithoutChangingInput() {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            transactions.add(t("01-01-2024", -i * 100, "Витрата " + i));
        }
        transactions.add(t("01-01-2024", 10000, "Дохід"));
        transactions.add(t("01-01-2024", 0, "Нуль"));
        var original = List.copyOf(transactions);
        var top = TransactionAnalyzer.findTopExpenses(transactions);
        assertEquals(List.of(-1200.0, -1100.0, -1000.0, -900.0, -800.0,
                -700.0, -600.0, -500.0, -400.0, -300.0),
                top.stream().map(Transaction::getAmount).toList());
        assertEquals(original, transactions);
    }

    @Test
    void topExpensesHandlesFewNoExpensesAndTies() {
        var first = t("01-01-2024", -10, "А");
        var second = t("02-01-2024", -10, "Б");
        assertEquals(List.of(first, second), TransactionAnalyzer.findTopExpenses(List.of(first, second)));
        assertTrue(TransactionAnalyzer.findTopExpenses(List.of()).isEmpty());
        assertTrue(TransactionAnalyzer.findTopExpenses(List.of(t("01-01-2024", 10, "Дохід"))).isEmpty());
    }

    @Test
    void periodIncludesBothBoundariesAndExcludesOtherDatesAndIncome() {
        var first = t("01-01-2024", -500, "А");
        var last = t("31-01-2024", -10, "Б");
        var transactions = List.of(t("31-12-2023", -10000, "Поза періодом"), first, last,
                t("01-02-2024", -1, "Поза періодом"), t("15-01-2024", 20000, "Дохід"));
        var from = LocalDate.of(2024, 1, 1);
        var to = LocalDate.of(2024, 1, 31);
        assertEquals(first, TransactionAnalyzer.findLargestExpense(transactions, from, to).orElseThrow());
        assertEquals(last, TransactionAnalyzer.findSmallestExpense(transactions, from, to).orElseThrow());
        assertEquals(first, TransactionAnalyzer.findSmallestExpense(transactions, from, from).orElseThrow());
    }

    @Test
    void periodHandlesNoExpensesAndTies() {
        var day = LocalDate.of(2024, 1, 1);
        assertTrue(TransactionAnalyzer.findLargestExpense(List.of(), day, day).isEmpty());
        assertTrue(TransactionAnalyzer.findSmallestExpense(
                List.of(t("01-01-2024", 100, "Дохід")), day, day).isEmpty());
        var first = t("01-01-2024", -10, "А");
        var tied = List.of(first, t("01-01-2024", -10, "Б"));
        assertSame(first, TransactionAnalyzer.findLargestExpense(tied, day, day).orElseThrow());
        assertSame(first, TransactionAnalyzer.findSmallestExpense(tied, day, day).orElseThrow());
    }

    @Test
    void groupsOnlyExpensesByCategoryAndChronologicalMonth() {
        var transactions = List.of(t("01-01-2024", -200, "Їжа"),
                t("01-12-2023", -100, "Їжа"), t("02-01-2024", -50, "Транспорт"),
                t("03-01-2024", 1000, "Їжа"), t("04-01-2024", 0, "Інше"));
        assertEquals(Map.of("Їжа", 300.0, "Транспорт", 50.0),
                TransactionAnalyzer.calculateExpensesByCategory(transactions));
        var monthly = TransactionAnalyzer.calculateExpensesByMonth(transactions);
        assertEquals(Map.of(YearMonth.of(2023, 12), 100.0, YearMonth.of(2024, 1), 250.0), monthly);
        assertEquals(List.of(YearMonth.of(2023, 12), YearMonth.of(2024, 1)), List.copyOf(monthly.keySet()));
        assertTrue(TransactionAnalyzer.calculateExpensesByCategory(List.of()).isEmpty());
        assertTrue(TransactionAnalyzer.calculateExpensesByMonth(List.of()).isEmpty());
    }

    @Test
    void bundledCsvMatchesPresentationResults() throws Exception {
        var transactions = TransactionCSVReader.readTransactions(
                getClass().getResource("/pr2.csv").toExternalForm());
        assertEquals(26, transactions.size());
        assertEquals(153000, TransactionAnalyzer.calculateTotalBalance(transactions), 1e-9);
        assertEquals(13, TransactionAnalyzer.countTransactionsByMonth(transactions, "01-2024"));
        assertEquals(List.of(-9100.0, -8800.0, -7850.0, -7500.0, -6000.0,
                        -5500.0, -4500.0, -3300.0, -3200.0, -3200.0),
                TransactionAnalyzer.findTopExpenses(transactions).stream().map(Transaction::getAmount).toList());
        assertEquals(Map.of(YearMonth.of(2023, 12), 33400.0, YearMonth.of(2024, 1), 38800.0),
                TransactionAnalyzer.calculateExpensesByMonth(transactions));
        assertEquals(16300, TransactionAnalyzer.calculateExpensesByCategory(transactions).get("Подарунки"));
    }
}
