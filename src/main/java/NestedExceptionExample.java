class NestedExceptionExample {
    public static void main(String[] args) {
        try {
            try {
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                throw new RuntimeException("Помилка обчислення", e);
            }
        } catch (RuntimeException e) {
            System.out.println("Основна причина: " + e.getCause());
        }
    }
}
