record CourseRecord(String courseName, int courseId, double creditHours) {
    public CourseRecord {
        if (courseName.isEmpty()) {
            throw new IllegalArgumentException("Назва курсу не може бути порожньою.");
        }
    }
    public CourseRecord(String courseName, int courseId, String creditHours) {
        this(courseName, courseId, Double.parseDouble(creditHours));
    }
}
