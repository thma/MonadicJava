import java.util.function.Function;

public class Wrap<T> {

    private final T value;

    private Wrap(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Wrap(" + value + ")";
    }

    public static  <T> Wrap<T> of(T value) {
        return new Wrap<T>(value);
    }

    public <R> Wrap<R> map(Function<T, R> mapper) {
        return Wrap.of(mapper.apply(value));
    }

    public <R> Wrap<R> flatMap(Function<T, Wrap<R>> mapper) {
        return mapper.apply(value);
    }

    Wrap<Integer> inc(Integer x) {
        return Wrap.of(x + 1);
    }

}
