class UniversityProcessor {
    public static void main(String[] args) {
        int[] grades = new int[5];
        System.out.println("Перед генерацією винятку.");
        grades[8] = 95;  // Викликає ArrayIndexOutOfBoundsException
        System.out.println("Цей рядок не буде виконаний.");
    }
}
