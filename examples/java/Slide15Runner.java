// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide15Runner {
public static void main(String[] args) throws Exception {
Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5)
    .filter(n -> n > 2)
    .map(n -> n * 2);
numbers.forEach(System.out::println);
}
}
