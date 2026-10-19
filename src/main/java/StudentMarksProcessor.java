class StudentMarksProcessor {
    public static void main(String[] args) {
        int[] marks = { 50, 75, 100, 0, 85 };
        int[] totalMarks = { 10, 0, 25, 100, 5 };
        
        for (int i = 0; i < marks.length; i++) {
            try {
                System.out.println("Результат: " + marks[i] + " / " + totalMarks[i] + " = " + marks[i] / totalMarks[i]);
            } catch (ArithmeticException exc) {
                System.out.println("Ділення на нуль неможливо!");
            }
        }
    }
}
