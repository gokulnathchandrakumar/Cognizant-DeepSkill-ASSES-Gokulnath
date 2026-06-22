public class ForecastTest {
    public static void main(String[] args) {

        ForecastService service = new ForecastService();

        System.out.println("---- Flat Growth Rate Forecast ----");
        double presentValue = 10000.0; // starting investment
        double growthRate = 0.08;      // 8% annual growth
        int years = 5;

        double futureValue = service.calculateFutureValue(presentValue, growthRate, years);
        System.out.printf("Future Value after %d years: %.2f%n", years, futureValue);

        System.out.println("\n---- Variable Growth Rate Forecast ----");
        double[] yearlyRates = {0.06, 0.04, 0.09, 0.03, 0.07}; // one rate per year
        double variableFutureValue =
                service.calculateFutureValueVariableRate(presentValue, yearlyRates, yearlyRates.length);
        System.out.printf("Variable-rate Future Value after %d years: %.2f%n",
                yearlyRates.length, variableFutureValue);

        System.out.println("\n---- Memoized Forecast (repeated queries) ----");
        for (int y = 1; y <= 5; y++) {
            double fv = service.calculateFutureValueMemoized(presentValue, growthRate, y);
            System.out.printf("Year %d -> Future Value: %.2f%n", y, fv);
        }
        System.out.println("\nRe-querying Year 5 (should hit cache, no recomputation):");
        double cachedResult = service.calculateFutureValueMemoized(presentValue, growthRate, 5);
        System.out.printf("Year 5 -> Future Value: %.2f%n", cachedResult);
    }
}
