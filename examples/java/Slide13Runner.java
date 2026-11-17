// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide13Runner {
public static void main(String[] args) throws Exception {
Function<String, Function<String, String>> prefixer = 
    prefix -> s -> prefix + ": " + s;

Function<String, String> addUniversityPrefix = prefixer.apply("Університет");
System.out.println(addUniversityPrefix.apply("Програмування"));
}
}
