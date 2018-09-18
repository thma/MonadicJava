import java.util.Optional;
import java.util.function.Function;

public class Result<T> {
    private Optional<T> value;
    private Optional<String> error;

    private Result(T value, String error) {
        this.value = Optional.ofNullable(value);
        this.error = Optional.ofNullable(error);
    }

    public static <U> Result<U> ok(U value) {
        return new Result<>(value, null);
    }

    public static <U> Result<U> error(String error) {
        return new Result<>(null, error);
    }

    public<U> Result<U> flatMap(Function<T, Result<U>> mapper) {

        if(this.isError()) {
            return Result.error(this.getError());
        }

        return mapper.apply(value.get());
    }

    public boolean isError() {
        return error.isPresent();
    }

    public T getValue() {
        return value.get();
    }

    public String getError() {
        return error.get();
    }
}