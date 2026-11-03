public class StudentFacultyDemo {
    enum Faculty { Engineering, Law, Medicine, Arts, Other }

    public static void main(String[] args) {
        String[] students = {
            "Іван Іваненко", "Ольга Петрів", "Сергій Ковальчук", "Марія Шевченко",
            "Андрій Олійник", "Галина Гончар", "Тарас Довженко", "Микола Вернигора",
            "Людмила Сокіл", "Ірина Кравець", "Павло Сидоренко", "Катерина Яремчук",
            "Іван Коваленко"
        };

        for (String student : students) {
            Faculty faculty = switch (student) {
                case "Іван Іваненко", "Ольга Петрів", "Сергій Ковальчук" -> Faculty.Engineering;
                case "Марія Шевченко", "Андрій Олійник", "Галина Гончар" -> Faculty.Law;
                case "Тарас Довженко", "Микола Вернигора" -> Faculty.Medicine;
                case "Людмила Сокіл", "Ірина Кравець", "Павло Сидоренко", "Катерина Яремчук" -> Faculty.Arts;
                default -> Faculty.Other;
            };
            if (faculty == Faculty.Other)
                System.out.println(student + " не належить до відомого факультету");
            else
                System.out.println(student + " належить до факультету " + faculty);
        }
    }
}
