import java.util.HashMap;
import java.util.Map;

public class ForecastService {

    
    public double calculateFutureValue(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return calculateFutureValue(presentValue, growthRate, years - 1) * (1 + growthRate);
    }

    public double calculateFutureValueVariableRate(double presentValue, double[] growthRates, int years) {
        if (years == 0) {
            return presentValue;
        }
        double valueAfterPreviousYears =
                calculateFutureValueVariableRate(presentValue, growthRates, years - 1);
        double thisYearRate = growthRates[years - 1]; // year n's rate
        return valueAfterPreviousYears * (1 + thisYearRate);
    }

    private Map<Integer, Double> memo = new HashMap<>();
    public double calculateFutureValueMemoized(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        if (memo.containsKey(years)) {
            return memo.get(years);
        }
        double result = calculateFutureValueMemoized(presentValue, growthRate, years - 1) * (1 + growthRate);
        memo.put(years, result);
        return result;
    }
}
