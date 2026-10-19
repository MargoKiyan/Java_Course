class StudentRecordSystem {
    public static void processStudentData() {
        int[] studentScores = new int[3];
        System.out.println("Перед обробкою даних студентів.");
        studentScores[5] = 95;  // Вихід за межі масиву
        System.out.println("Це повідомлення не буде відображене.");
    }

    public static void main(String[] args) {
        try {
            processStudentData();
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println("Стандартне повідомлення: ");
            System.out.println(exc.toString());
            System.out.println("\nТрасування стека: ");
            exc.printStackTrace();
        }
        System.out.println("Після обробки винятку.");
    }
}
