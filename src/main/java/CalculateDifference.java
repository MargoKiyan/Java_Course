public class CalculateDifference implements UniversityService {
    public String getName() { return "calculateDifference"; }
    public int calculate(int mark1, int mark2) { return Math.abs(mark1 - mark2); }
}
