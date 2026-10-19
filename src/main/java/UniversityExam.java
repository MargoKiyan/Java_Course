class UniversityExam {
    public static void main(String[] args) {
        try {
            System.out.println("Перед оператором throw.");
            throw new ArithmeticException("Помилка в обчисленнях!");
        } catch (ArithmeticException exc) {
            System.out.println("Виняток перехоплено: " + exc.getMessage());
        }
        System.out.println("Після блоку try/catch.");
    }
}
