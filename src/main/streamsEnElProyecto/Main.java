package main.streamsEnElProyecto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    Random random = new Random();

    public Main() {

        //int result = random.ints(10, 1, 11)
        random.ints(10, 1, 11)
                .boxed()
                .filter(value -> value >= 5)
                .sorted()
                .map(value -> value * value)
                .map(Description::new)
                //.peek(System.out::println)
                .map(Description::getValue)
                .forEach(value -> System.out.println(value + " "));
                //.reduce(0,(acumulator, value) -> acumulator + value);
        //.max(Comparator.naturalOrder())
        //.mapToInt(Integer::intValue)
        //.sum();
                /*.ifPresentOrElse(
                        value -> System.out.println("value.doubleValue() = " + value.doubleValue()),
                        () -> System.out.println("No hay máximo porque el stream está vacío")
                );*/

        //System.out.println("result = " + result);
    }

    private Stream<Integer> getRandomNumbers(Integer value) {
        return random.ints(value, 0, 10).boxed();
    }

    private Stream<String> getFilterNames(List<String> names) {
        return names.stream()
                .filter(name -> name.contains("P"));
    }

    private Integer getValorSiNoHayMax() {
        return 0;
    }

    private int randomInt() {
        return random.nextInt(10);
    }

            /*new Provider<Integer>() {
            Random random = new Random();
                    @Override
                    public Integer get() {
                        return random.nextInt(10);
                    }
                }*/

}
