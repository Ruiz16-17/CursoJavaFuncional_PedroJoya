package main.v9MethodReferenceAdvance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class Flujo<T> {

    private final List<T> values;

    public Flujo(List<T> values) {
        this.values = values;
    }

    public static <T> Flujo<T> provide(int size, Supplier<T> provider) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(provider.get());
        }
        return new Flujo<>(result);
    }

    public Flujo<T> filter(Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for (T value : values) {
            if(predicate.test(value)){
                result.add(value);
            }
        }
        return new Flujo<>(result);
    }


    public <R> Flujo<R> transformation(Function<T, R> function2){
        List<R> result = new ArrayList<>();
        for (T value : values) {
            result.add(function2.apply(value));
        }
        return new Flujo<>(result);
    }

    public Flujo<T> act(Consumer<T> consumer2){
        for (T value : values) {
            consumer2.accept(value);
        }
        return new Flujo<>(values);
    }

    public void consume(Consumer<T> consumer2) {

        for (T value : values) {
            consumer2.accept(value);
        }

    }

    public T reduce(T identity, BinaryOperator<T> binaryFunction2){
        T total = identity;
        for (T value : values) {
            total = binaryFunction2.apply(total,value);
        }

        return total;
    }

    public Flujo<T> sort(Comparator<T> comparator){
        List<T> valuesSorted = new ArrayList<>(values);
        valuesSorted.sort(comparator);
        return new Flujo<>(valuesSorted);
    }

    public T max(Comparator<T> comparator){
        return Collections.max(values, comparator);
    }

    @Override
    public String toString() {
        return "Flujo{" +
                "values=" + values +
                '}';
    }
}
