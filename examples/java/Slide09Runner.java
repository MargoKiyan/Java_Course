// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide09Runner {
public static Function<Integer, Function<Integer, Integer>> carryAdd() {
    return a -> b -> a + b;
}

public static void main(String[] args) {
    Function<Integer, Integer> add5 = carryAdd().apply(5);
    System.out.println("5 + 10 = " + add5.apply(10));
}
}
