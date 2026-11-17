// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide14Runner {
public static long tailrecFactorial(long n) {
    return helpFact(n, 1);
}

private static long helpFact(long i, long j) {
    if (i == 0) {
        return j;
    }
    return helpFact(i - 1, i * j);
}
public static void main(String[] args) {
    long value = tailrecFactorial(5);
    if (value != 120) throw new AssertionError(value);
    System.out.println("tailrecFactorial(5): " + value);
}
}
