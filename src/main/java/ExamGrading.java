class ExamGrading {
    public static void main(String[] args) {
        int[] scores = { 10, 20, 30, 40, 50 };
        int[] divisors = { 2, 0, 5, 0, 10 };
        
        try {
            for (int i = 0; i < scores.length; i++) {
                try {
                    System.out.println(scores[i] + " / " + divisors[i] + " = " + scores[i] / divisors[i]);
                } catch (ArithmeticException e) {
                    System.out.println("Ділення на нуль неможливо!");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Елемент не знайдено в масиві! Програма завершена.");
        }
    }
}
