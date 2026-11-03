record StudentGrade(String name, double grade) {
    public double getDiscountedGrade(double percentage) {
        return grade - (grade * percentage / 100.0);
    }
}
