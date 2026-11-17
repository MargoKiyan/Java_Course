// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide07FinalRunner {
public static void main(String[] args) throws Exception {
int i = 42;
Function<String, String> f = s -> s + i;
System.out.println(f.apply("Привіт, "));
}
}
