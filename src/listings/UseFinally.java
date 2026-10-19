class UseFinally {
    public static void main(String[] args) {
        try {
            // Відкриття з'єднання з базою даних
            // Випадковий код, що може викликати помилку
        } catch (SQLException e) {
            System.out.println("Помилка при доступі до бази даних.");
        } finally {
            // Закриття з'єднання з базою даних
            System.out.println("З'єднання з базою даних закрито.");
        }
    }
}
