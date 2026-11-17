// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide05Runner {
public static Function<String, String> makePrefixer(String prefix) {
    return s -> prefix + ": " + s;
}

public static String doubleApplier(String input, Function<String, String> f) {
    return f.apply(f.apply(input));
}

public static void main(String[] args) {
    Function<String, String> f = makePrefixer("Університет");
    System.out.println(doubleApplier("Курс", f));
}
}
