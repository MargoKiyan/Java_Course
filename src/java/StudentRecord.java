record StudentRecord(String name, int studentID, double gpa) {
    public StudentRecord(String name, int studentID, String gpa) {
        this(name, studentID, Double.parseDouble(gpa));
    }
}
