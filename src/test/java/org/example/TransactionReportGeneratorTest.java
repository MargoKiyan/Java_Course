package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.YearMonth;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TransactionReportGeneratorTest {
    @Test
    void testCategoryAndMonthlyReports() {
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream output = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(output);
            TransactionReportGenerator.printCategoryExpensesReport(Map.of("Їжа", 2500.0));
            Map<YearMonth, Double> months = new TreeMap<>();
            months.put(YearMonth.of(2023, 12), 33400.0);
            months.put(YearMonth.of(2024, 1), 38800.0);
            TransactionReportGenerator.printMonthlyExpensesReport(months);
        } finally {
            System.setOut(original);
        }
        String report = bytes.toString(StandardCharsets.UTF_8);
        assertTrue(report.contains("Їжа: 2500.0 **" + System.lineSeparator()));
        assertTrue(report.contains("12-2023: 33400.0 " + "*".repeat(33) + System.lineSeparator()));
        assertTrue(report.contains("01-2024: 38800.0 " + "*".repeat(38) + System.lineSeparator()));
    }
}
