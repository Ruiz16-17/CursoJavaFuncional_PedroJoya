package main.imperative;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Main();
        //Esto se hace para evitar crear métodos estáticos
    }

    public Main() {
        List<Integer> numbers = createList();
        System.out.println(numbers);
        List<Integer> pairs = filterPairs(numbers);
        System.out.println(pairs);
        List<Integer> squares = squares(pairs);
        System.out.println("squares = " + squares);
        List<Integer> displayed = showList(squares);
        int total = sumListSquare(displayed);
        System.out.println("total = " + total);
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

    private List<Integer> filterPairs(List<Integer> numbers) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer i : numbers) {
            if (i % 2 == 0) {
                result.add(i);
            }
        }
        return result;
    }

    private List<Integer> squares(List<Integer> numbers) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer i : numbers) {
            result.add(i*i);
        }
        return result;
    }

    private List<Integer> createList() {
        return List.of(0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
    }
}
