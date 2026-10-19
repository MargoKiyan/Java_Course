public class MethodBoxing {
    // Метод, що приймає параметр типу Integer
    static void processStudentID(Integer id) {
        System.out.println("Оброблено студентський ID: " + id);
    }
    // Метод, що повертає примітивний тип int
    static int getBonusPoints() {
        return 5;
    }
    // Метод, що повертає тип Integer
    static Integer calculateFinalGrade() {
        return 95;
    }
    public static void main(String[] args) {
        // Автоупаковка: int перетворюється на Integer
        processStudentID(12345);

        // Авторозпаковка: Integer перетворюється на int
        int bonus = getBonusPoints();
        System.out.println("Бонусні бали: " + bonus);

        // Автоупаковка та авторозпаковка під час повернення результату
        int finalGrade = calculateFinalGrade();
        System.out.println("Остаточна оцінка студента: " + finalGrade);
    }
}


