// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide17Runner {
public static void main(String[] args) throws Exception {
List<Integer> numbers = List.of(1, 2, 3);
List<Integer> updatedNumbers = Stream.concat(
    numbers.stream(), Stream.of(4)).toList();
System.out.println("Original: " + numbers);
System.out.println("Updated: " + updatedNumbers);
if (!numbers.equals(List.of(1, 2, 3)) || !updatedNumbers.equals(List.of(1, 2, 3, 4))) throw new AssertionError();
}
}
