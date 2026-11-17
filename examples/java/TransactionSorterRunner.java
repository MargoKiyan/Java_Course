// EXECUTION_SCAFFOLD: deterministic input and assertions for original slides 26-27.
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
public class TransactionSorterRunner {
    public static void main(String[] args) {
        for (int size : new int[]{5, 65}) {
            Transaction[] data = new Transaction[size];
            LocalDateTime base = LocalDateTime.of(2024, 9, 1, 0, 0);
            for (int i = 0; i < size; i++)
                data[i] = new Transaction("sender", "receiver", i, base.plusSeconds((i * 17) % size));
            Transaction[] expected = data.clone();
            Arrays.sort(expected);
            try (var pool = new ForkJoinPool(4)) {
                pool.invoke(new TransactionSorter(data, 0, data.length));
            }
            if (!Arrays.equals(data, expected)) throw new AssertionError("Wrong order or lost transaction");
            System.out.println("size=" + size + ": sorted by time, all transactions preserved: PASS");
            System.out.println("first=" + data[0].time() + ", last=" + data[size - 1].time());
        }
    }
}
