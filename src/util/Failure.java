package util;

public class Failure<T> implements Result<T> {
    private final T msg;
    public Failure(T msg) {
        this.msg = msg;
    }

    @Override
    public void bind(Effect<T> success, Effect<T> failure) {
        failure.apply(msg);
    }
}
