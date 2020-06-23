import java.math.BigDecimal;

public class MeasureCalculator {

    final static int METERS         = 1;
    final static int CENTIMETERS    = 2;
    final static int MILIMETERS     = 3;
    final static int KILOGRAMS      = 1;
    final static int GRAMS          = 2;
    final static int MILIGRAMS      = 3;

    private MeasureCalculator(){}

    public static String[] calculateMetric(String inputString, int inputType) {
        String[] values = new String[3];
        BigDecimal inputNumber = new BigDecimal(inputString);
        switch(inputType){
            case METERS -> {
                values[0] = inputString;
                values[1] = inputNumber.multiply(BigDecimal.valueOf(100)).toString();
                values[2] = inputNumber.multiply(BigDecimal.valueOf(1000)).toString();
            }
            case CENTIMETERS -> {
                values[0] = inputNumber.divide(BigDecimal.valueOf(100)).toString();
                values[1] = inputString;
                values[2] = inputNumber.multiply(BigDecimal.valueOf(10)).toString();
            }
            case MILIMETERS -> {
                values[0] = inputNumber.divide(BigDecimal.valueOf(1000)).toString();
                values[1] = inputNumber.divide(BigDecimal.valueOf(10)).toString();
                values[2] = inputString;
            }
        }
        return values;
    }

    public static String[] calculateWeight(String inputString, int inputType) {
        String[] values = new String[3];
        BigDecimal inputNumber = new BigDecimal(inputString);
        switch(inputType){
            case KILOGRAMS -> {
                values[0] = inputString;
                values[1] = inputNumber.multiply(BigDecimal.valueOf(1_000)).toString();
                values[2] = inputNumber.multiply(BigDecimal.valueOf(1_000_000)).toString();
            }
            case GRAMS -> {
                values[0] = inputNumber.divide(BigDecimal.valueOf(1_000)).toString();
                values[1] = inputString;
                values[2] = inputNumber.multiply(BigDecimal.valueOf(1_000)).toString();
            }
            case MILIGRAMS -> {
                values[0] = inputNumber.divide(BigDecimal.valueOf(1_000_000)).toString();
                values[1] = inputNumber.divide(BigDecimal.valueOf(1_000)).toString();
                values[2] = inputString;
            }
        }
        return values;
    }
}
