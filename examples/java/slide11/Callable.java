// EXECUTION_SCAFFOLD: split first original declaration into its own file.
@FunctionalInterface
public interface Callable<V> {
    V call() throws Exception;
}
