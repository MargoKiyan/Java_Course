public class DifferenceProvider implements ServiceProvider {
    public UniversityService get() { return new CalculateDifference(); }
}
