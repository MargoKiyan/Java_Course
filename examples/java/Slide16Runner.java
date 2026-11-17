// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide16Runner {
public static void main(String[] args) throws Exception {
BiFunction<Integer, LocalDate, String> bif =
    (i, d) -> "Лічильник для " + d + ": " + i;
Function<LocalDate, String> withCount =
    d -> bif.apply(42, d);
LocalDate today = LocalDate.now();
Function<Integer, String> forToday = i -> bif.apply(i, today);
System.out.println(withCount.apply(LocalDate.of(2024, 9, 1)));
System.out.println(forToday.apply(7));
if (!forToday.apply(7).equals("Лічильник для " + today + ": 7")) throw new AssertionError();
}
}
