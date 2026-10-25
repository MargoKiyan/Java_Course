package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVParser {
    public static List<Transaction> parse(Reader reader) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        BufferedReader br = new BufferedReader(reader);
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            Transaction transaction = new Transaction(values[0],
                    Double.parseDouble(values[1]), values[2]);
            transactions.add(transaction);
        }
        return transactions;
    }
}
