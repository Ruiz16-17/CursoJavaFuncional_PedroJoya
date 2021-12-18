package main.v6Lambda;

import main.v6Lambda.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Flujo<T> {

    private final List<T> values;

    public Flujo(List<T> values) {
        this.values = values;
    }

    public static <T> Flujo<T> provide(int size, Provider<T> provider) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(provider.get());
        }
        return new Flujo<>(result);
    }

    public Flujo<T> filter(Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for (T value : values) {
            if(predicate.apply(value)){
                result.add(value);
            }
        }
        return new Flujo<>(result);
    }


    public <R> Flujo<R> transformation(Function2<T, R> function2){
        List<R> result = new ArrayList<>();
        for (T value : values) {
            result.add(function2.apply(value));
        }
        return new Flujo<>(result);
    }

    public Flujo<T> act(Consumer2<T> consumer2){
        for (T value : values) {
            consumer2.accept(value);
        }
        return new Flujo<>(values);
    }

    public void consume(Consumer2<T> consumer2) {

        for (T value : values) {
            consumer2.accept(value);
        }

    }

    public T reduce(T identity, OperadorBinario<T> binaryFunction2){
        T total = identity;
        for (T value : values) {
            total = binaryFunction2.apply(total,value);
        }

        return total;
    }

    @Override
    public String toString() {
        return "Flujo{" +
                "values=" + values +
                '}';
    }
}
