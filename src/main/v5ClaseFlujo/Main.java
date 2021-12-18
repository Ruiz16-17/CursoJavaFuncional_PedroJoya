package main.v5ClaseFlujo;

import main.v5ClaseFlujo.interfaces.*;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        new Main();
        //Esto se hace para evitar crear métodos estáticos
    }

    public Main() {
        Flujo<Integer> numbers = Flujo.provide(10, new Provider() {

            Random random = new Random();

            @Override
            public Integer get() {
                return random.nextInt(1000);

            }
        });

        System.out.println(numbers);
        Flujo<Integer> pairs = numbers.filter(new Predicate<>() {
            @Override
            public boolean apply(Integer value) {
                return value % 2 == 0;
            }
        });
        System.out.println(pairs);

        Flujo<Integer> squares = pairs.transformation(new OperadorUnario<>() {
            @Override
            public Integer apply(Integer value) {
                return value * value;
            }
        });//Se pasa una clase que implemente la interfaz función

        System.out.println("squares = " + squares);

        Consumer2<Integer> printer = new Consumer2<>() {
            @Override
            public void accept(Integer value) {
                System.out.println("value = " + value);
            }
        };

        Flujo<Integer> displayed = squares.act(printer);

        squares.consume(new Consumer2<>() {
            @Override
            public void accept(Integer value) {
                System.out.println("value = " + value);
            }
        });
        Integer total = displayed.reduce(0, new OperadorBinario<>() {
            @Override
            public Integer apply(Integer value1, Integer value2) {
                return value1 + value2;
            }
        });
        System.out.println("total of reduce = " + total);

        //---------------------------------------------------------------------------------------------------------
        //Obtener cada número convertido en cadena

        Flujo<String> stringConverted = pairs.transformation(new Function2<Integer, String>() {
            @Override
            public String apply(Integer value) {
                return "Valor: " + value;
            }
        });

        System.out.println("stringConverted = " + stringConverted);

        //--------------------------------------------------------------------------ENCADENANDO
        System.out.println("------------------------ENCADENADO----------------------------------");
        Integer totalFlujo = new Flujo<Integer>(List.of(1,2,3,4,5,6,7,8))
                .filter(new Predicate<>() {
                    @Override
                    public boolean apply(Integer value) {
                        return value % 2 == 0;
                    }
                })
                .transformation(new OperadorUnario<>() {
                    @Override
                    public Integer apply(Integer value) {
                        return value * value;
                    }
                })
                .act(printer)
                .reduce(0, new OperadorBinario<>() {
                    @Override
                    public Integer apply(Integer value1, Integer value2) {
                        return value1 + value2;
                    }
                });

        System.out.println("totalFlujo = " + totalFlujo);
    }

}
