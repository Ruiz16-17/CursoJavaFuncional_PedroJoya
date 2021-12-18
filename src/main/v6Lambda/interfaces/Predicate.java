package main.v6Lambda.interfaces;

@FunctionalInterface
public interface Predicate <T> {
    boolean apply(T value);
}
