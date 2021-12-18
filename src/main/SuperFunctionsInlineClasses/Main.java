package main.SuperFunctionsInlineClasses;

import main.SuperFunctionsInlineClasses.interfaces.*;
import main.superFunctionsClasses.classes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        new Main();
        //Esto se hace para evitar crear métodos estáticos
    }

    public Main() {
        List<Integer> numbers = SuperFunctions.provide(10, new Provider() {

            Random random = new Random();

            @Override
            public Integer get() {
                return random.nextInt(1000);

            }
        });
        System.out.println(numbers);
        List<Integer> pairs = SuperFunctions.filter(new Predicate() {
            @Override
            public boolean apply(Integer value) {
                return value % 2 == 0;
            }
        }, numbers);
        System.out.println(pairs);
        List<Integer> squares = SuperFunctions.transformation(pairs, new Function2() {
            @Override
            public Integer apply(Integer value) {
                return value * value;
            }
        });//Se pasa una clase que implemente la interfaz función
        System.out.println("squares = " + squares);

        Consumer2 printer = new Consumer2() {
            @Override
            public void accept(Integer value) {
                System.out.println("value = " + value);
            }
        };

        List<Integer> displayed = SuperFunctions.act(squares, printer);

        SuperFunctions.consume(squares, new Consumer2() {
            @Override
            public void accept(Integer value) {
                System.out.println("value = " + value);
            }
        });
        int total = SuperFunctions.reduce(displayed, 0, new BinaryFunction2() {
            @Override
            public Integer apply(Integer value1, Integer value2) {
                return value1 + value2;
            }
        });
        System.out.println("total of reduce = " + total);
    }

}
