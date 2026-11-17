// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide31Runner {
public static void main(String[] args) throws Exception {
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
    return 42;
}).thenApply(x -> x + 1);

CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 100);
Runnable action = () -> System.out.println("Один з CompletableFuture завершився");
future.runAfterEither(future2, action).join();
}
}
