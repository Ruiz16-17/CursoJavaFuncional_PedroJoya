package main.v11Streams;

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
        //Esto se hace para evitar crear métodos estáticos
    }

    Random random = new Random();

    public Main() {

        //1. Función generadora de un stream
        List<String> result = Stream.of("Sebastian", "Ruiz", "Gallego")//Otra forma de hacerlo
                //2. Prporcionar operaciones intermedias(Las operaciones intermedias retornan valores, por lo que se
                // les pueden encadenar más operaciones)
                .filter(name -> name.contains("R"))
                //3. Operación terminal (No retorna un Stream, pero puede retornar otros tipos de valores) después de
                //una llamada a una operación terminal, no se puede voilever a llamar este stream
                .collect(Collectors.toList());
        //.forEach(System.out::println);

        List<String> names2 = new ArrayList<>(List.of("Sebastian", "Ruiz", "Gallego"));
        List<String> result2 = getFilterNames(names2).limit(1).collect(Collectors.toList());
        System.out.println("result2 = " + result2);

        //Esta debe ser siempre la estructura de los streams

        /*System.out.println("Se desea filtrar los números primos");

        Flujo.provide(10, this::randomInt)
                .filter(value -> value >= 10)
                .sort(Integer::compareTo)
                //compareTo no es un método estático pero la forma en que se hace la operación permite que sea declarado
                //de esta forma, al primer argumento se le asigna el método y es segun argumento queda como parámetro
                //del método llamado, de la siguiente manera (valor1, valor2) -> valor1.compareYo(valor2)
                .transformation(NumberUtils::toSquare)
                //.transformation(value -> new Description(value))
                .transformation(Description::new)//Referencia a un método constructor
                .act(System.out::println)
                //.transformation(description -> description.getValue())
                .transformation(Description::getValue)
                .max(Comparator.naturalOrder())
                .ifPresentOrElse(
                        value -> System.out.println("value = " + value),
                        () -> System.out.println("No hay máximo porque el flujo está vacío")
                );
*/
        //double maxDouble = max.orElse(0).doubleValue();
        //double maxDouble = max.orElseGet(() -> getValorSiNoHayMax()).doubleValue();
        //System.out.println("Máximo = " + maxDouble);

        //max.ifPresent(value -> System.out.println("value = " + value));

//                .reduce(0, Integer::sum);

        //Estas referencias a métodos no recibe par+amatros ya que son implicitamente pasados por los otros procedimientos

        //-----------------------------------------Funciones generadoras------------------------------
        List<Integer> resultadoGenerado = Stream.generate(() -> {
                    int nextInt = random.nextInt(10);
                    System.out.println("Se ha generado el " + nextInt);
                    return nextInt;
                })
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("resultadoGenerado = " + resultadoGenerado);

        //En este, el segundo argumento va a ser la operación que va a calcular los datos a partir del primer valor (seed)
        List<Integer> resultadoIterados = Stream.iterate(1, value -> value * 5)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("resultadoIterados = " + resultadoIterados);

        //En este, el tercer argumento va a ser la condición para que deje de generar los valores (predicate)
        List<Integer> resultadoIterados2 = Stream.iterate(1, value -> value < 1000, value -> value * 5)
                .collect(Collectors.toList());

        System.out.println("resultadoIterados2 = " + resultadoIterados2);

        //Vamos a generar números aleatorios
        //boxed convierte al tipo correspondiente de un intStream a un Stream<Integer>
        List<Integer> resultadoRandom = random.ints(5, 0, 10)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("resultadoRandom = " + resultadoRandom);

        //Vamos a generar números en un rango definido
        //boxed convierte al tipo correspondiente de un intStream a un Stream<Integer>
        List<Integer> resultadoRange = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("resultadoRange = " + resultadoRange);

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
