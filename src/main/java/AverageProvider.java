public class AverageProvider implements ServiceProvider {
    public UniversityService get() { return new CalculateAverage(); }
}
