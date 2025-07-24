package util;

public interface Result <T> {
    void bind(Effect<T> success, Effect<T> failure);

    static <T> Result<T> success(T value){
        return new Success<>(value);
    }

    static <T> Result<T> failure(T msg){
        return new Failure<>(msg);
    }
}
