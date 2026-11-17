// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide08Runner {
public static void main(String[] args) throws Exception {
var result = List.of(1, 2, 3, 4)
    .stream()
    .map(it -> it * 2)
    .filter(it -> it > 4)
    .toList();
System.out.println(result);
}
}
