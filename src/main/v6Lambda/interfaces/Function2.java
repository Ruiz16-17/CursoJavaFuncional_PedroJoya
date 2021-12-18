package main.v6Lambda.interfaces;

@FunctionalInterface
public interface Function2<T, R> {
    R apply(T value);
}
