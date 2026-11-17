// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide30Runner {
public static void main(String[] args) throws Exception {
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    // Симуляція обчислення
    return 42;
});

CompletableFuture<Integer> f2 = future.thenApply(x -> x + 1);
CompletableFuture<Integer> f3 = future.thenApplyAsync(x -> x * 3);

// Виведення результатів
System.out.println("F2: " + f2.get());
System.out.println("F3: " + f3.get());
}
}
