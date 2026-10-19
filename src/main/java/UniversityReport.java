class UniversityReport {
    public static void main(String[] args) {
        int[] scores = { 10, 20, 30, 40, 50 };
        int[] divisors = { 2, 0, 5, 0, 10 };
        
        for (int i = 0; i < scores.length; i++) {
            try {
                System.out.println(scores[i] + " / " + divisors[i] + " = " + scores[i] / divisors[i]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Елемент не знайдено в масиві!");
            } catch (Throwable e) {
                System.out.println("Виникла помилка: " + e.getMessage());
            }
        }
    }
}
