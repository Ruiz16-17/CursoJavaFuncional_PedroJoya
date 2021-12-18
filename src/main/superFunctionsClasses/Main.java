package main.superFunctionsClasses;

import main.superFunctionsClasses.classes.*;
import main.superFunctionsClasses.interfaces.Function2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Main();
        //Esto se hace para evitar crear métodos estáticos
    }

    public Main() {
        List<Integer> numbers = SuperFunctions.provide(10, new Random2());
        System.out.println(numbers);
        List<Integer> pairs = SuperFunctions.filter(new OnlyPairs(), numbers);
        System.out.println(pairs);
        List<Integer> squares = SuperFunctions.transformation(pairs, new ToSquare());//Se pasa una clase que implemente la interfaz función
        System.out.println("squares = " + squares);
        List<Integer> displayed = SuperFunctions.act(squares, new Printer2()); //retornando la lista
        SuperFunctions.consume(squares, new Printer2()); //Sin retornar
        int total = SuperFunctions.reduce(displayed, 0, new Adder());
        System.out.println("total of reduce = " + total);
    }

    private int sumListSquare(List<Integer> displayed) {
        int total = 0;
        for (Integer i : displayed) {
            total += i;
        }

        return total;
    }

    private List<Integer> showList(List<Integer> squares) {
        for (Integer i : squares) {
            System.out.println(i);
        }
        return squares;
    }

    private List<Integer> squares(List<Integer> numbers) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer i : numbers) {
            result.add(i*i);
        }
        return result;
    }
}
