import org.junit.Test;

public class MonadMusings2Test {

    public Result<Integer> readIntFromFile(String file) {
        return Result.ok(4);
    }

    public Result<Integer> adjustValue(Integer value) {
        if (value > 5) {
            Result.error("Value " + value + " should no be greater than 5");
        }
        return Result.ok(5 - value);
    }

    public Double calculateAverage(Integer val1, Integer val2) {
        return (val1 + val2)/2d;
    }

    /**
     * Read ints from two files, Adjust the first one and then calculate average.
     * Returns a Result wrapping the positive outcome or any error.
     */
    public Result<Double> businessOperation(String fileName, String fileName2) {

        Result<Integer> adjustedValue = readIntFromFile(fileName)
                .flatMap(this::adjustValue);

        return adjustedValue.flatMap( val1 ->
                readIntFromFile(fileName2).flatMap( val2 ->
                        Result.ok(calculateAverage(val1, val2))
                ));
    }

    private Log<Integer> start(int val) {
        return Log.trace(val, "initial value");
    }

    private Log<Integer> operation2(Integer val) {
        return  Log.trace(val + 2, "2a -> add 2").flatMap( val2 ->
                Log.trace((Integer)val2 + 5, "2b -> add 5")

        );
    }

    private Log<Double> operation3(Integer val) {
        return Log.trace(val/4d, "divided by 4");
    }

    public void run() {
        Log<Double> log = start(5)
                .flatMap(this::operation2)
                .flatMap(this::operation3)
                .flatMap( val ->
                        Log.trace( val * 2, "Multiplied by two")
                );

        System.out.println("Value: " + log.getValue());
        System.out.println("Trace: " + log.getTrace());
    }

    @Test
    public void testLog() {
        MonadMusings2Test mm = new MonadMusings2Test();
        mm.run();
    }

}
