// EXECUTION_SCAFFOLD: minimal invocation of the two original interface declarations.
public class InterfacesRunner {
    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = () -> 42;
        Supplier<Integer> supplier = () -> 42;
        if (callable.call() != 42 || supplier.get() != 42) throw new AssertionError();
        System.out.println("Callable.call(): " + callable.call());
        System.out.println("Supplier.get(): " + supplier.get());
    }
}
