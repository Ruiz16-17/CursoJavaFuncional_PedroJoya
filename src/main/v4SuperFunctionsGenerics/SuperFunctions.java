package main.v4SuperFunctionsGenerics;

import main.v4SuperFunctionsGenerics.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class SuperFunctions {
    public static <T> List<T> filter(Predicate<T> predicate, List<T> values){
        List<T> result = new ArrayList<>();
        for (T value : values) {
            if(predicate.apply(value)){
                result.add(value);
            }
        }
        return result;
    }

    public static <T> List<T> provide(int size, Provider<T> provider) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(provider.get());
        }
        return result;
    }

    public static <T, R> List<R> transformation(List<T> values, Function2<T, R> function2){
        List<R> result = new ArrayList<>();
        for (T value : values) {
            result.add(function2.apply(value));
        }
        return result;
    }

    public static <T> List<T> act(List<T> values, Consumer2<T> consumer2){
        for (T value : values) {
            consumer2.accept(value);
        }
        return values;
    }

    public static <T> void consume(List<T> squares, Consumer2<T> consumer2) {

        for (T value : squares) {
            consumer2.accept(value);
        }

    }

    public static <T> T reduce(List<T> values, T identity, OperadorBinario<T> binaryFunction2){
        T total = identity;
        for (T value : values) {
            total = binaryFunction2.apply(total,value);
        }

        return total;
    }
}
