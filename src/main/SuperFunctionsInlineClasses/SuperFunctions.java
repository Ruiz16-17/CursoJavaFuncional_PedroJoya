package main.SuperFunctionsInlineClasses;

import main.SuperFunctionsInlineClasses.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class SuperFunctions {
    public static List<Integer> filter(Predicate predicate, List<Integer> values){
        List<Integer> result = new ArrayList<Integer>();
        for (Integer value : values) {
            if(predicate.apply(value)){
                result.add(value);
            }
        }
        return result;
    }

    public static List<Integer> provide(int size, Provider provider) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            result.add(provider.get());
        }
        return result;
    }

    public static List<Integer> transformation(List<Integer> values, Function2 function2){
        List<Integer> result = new ArrayList<Integer>();
        for (Integer value : values) {
            result.add(function2.apply(value));
        }
        return result;
    }

    public static List<Integer> act(List<Integer> values, Consumer2 consumer2){
        for (Integer value : values) {
            consumer2.accept(value);
        }
        return values;
    }

    public static void consume(List<Integer> squares, Consumer2 consumer2) {

        for (Integer value : squares) {
            consumer2.accept(value);
        }

    }

    public static Integer reduce(List<Integer> values, Integer identity, BinaryFunction2 binaryFunction2){
        int total = identity;
        for (Integer value : values) {
            total += binaryFunction2.apply(total,value);
        }

        return total;
    }
}
