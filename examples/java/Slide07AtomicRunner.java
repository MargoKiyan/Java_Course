// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide07AtomicRunner {
public static Function<Integer, Integer> closure() {
    var universityYear = new AtomicInteger(2024);
    return i -> universityYear.addAndGet(i);
}

public static void main(String[] args) {
    Function<Integer, Integer> incrementYear = closure();
    System.out.println("Наступний рік навчання: " + incrementYear.apply(1));  // 2025
}
}
