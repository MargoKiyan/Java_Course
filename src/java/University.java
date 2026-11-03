record Student(String name, int id, double averageGrade) {}

public class University {
    public static void main(String[] args) {
        Student student = new Student("Олексій", 12345, 90.5);
        System.out.println("Ім'я: " + student.name());
        System.out.println("Номер: " + student.id());
        System.out.println("Середній бал: " + student.averageGrade());
    }
}
