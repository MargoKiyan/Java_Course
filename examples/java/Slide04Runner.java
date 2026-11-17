// EXECUTION_SCAFFOLD: wrapper/imports and observable checks only.
import java.time.*;
import java.util.*;
import java.util.function.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.*;
public class Slide04Runner {
public static void main(String[] args) throws Exception {
LocalDate dateOfAdmission = LocalDate.of(2023, Month.SEPTEMBER, 1);
LocalDate updatedDate = dateOfAdmission.withYear(2024);
System.out.println(updatedDate);
if (!dateOfAdmission.equals(LocalDate.of(2023, 9, 1))) throw new AssertionError("Original date changed");
System.out.println("Original: " + dateOfAdmission);
}
}
