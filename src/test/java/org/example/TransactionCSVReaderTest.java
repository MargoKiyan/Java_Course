package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class TransactionCSVReaderTest {
    @TempDir
    Path directory;

    @Test
    void testReadTransactions() throws Exception {
        Path file = directory.resolve("transactions.csv");
        Files.writeString(file,
                "05-12-2023,-7850,Сільпо\n10-12-2023,80000,Зарплата",
                StandardCharsets.UTF_8);
        List<Transaction> transactions = TransactionCSVReader.readTransactions(file.toUri().toString());
        Assertions.assertEquals(2, transactions.size());
        Assertions.assertEquals("05-12-2023", transactions.get(0).getDate());
        Assertions.assertEquals(-7850.0, transactions.get(0).getAmount());
        Assertions.assertEquals("Сільпо", transactions.get(0).getDescription());
        Assertions.assertEquals("10-12-2023", transactions.get(1).getDate());
        Assertions.assertEquals(80000.0, transactions.get(1).getAmount());
        Assertions.assertEquals("Зарплата", transactions.get(1).getDescription());
    }

    @Test
    void testParseWithoutDataSource() throws Exception {
        List<Transaction> transactions = TransactionCSVParser.parse(
                new StringReader("01-02-2023,-50,Витрата"));
        Assertions.assertEquals(1, transactions.size());
        Assertions.assertEquals("01-02-2023", transactions.get(0).getDate());
        Assertions.assertEquals(-50.0, transactions.get(0).getAmount());
        Assertions.assertEquals("Витрата", transactions.get(0).getDescription());
    }
}
