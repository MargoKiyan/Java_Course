package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {
    public static List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            URL url = new URL(filePath);
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(url.openStream(), "UTF-8"))) {
                transactions = TransactionCSVParser.parse(br);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
