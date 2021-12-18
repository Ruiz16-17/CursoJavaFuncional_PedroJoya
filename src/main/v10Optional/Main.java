package main.v10Optional;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        new Main();
        //Esto se hace para evitar crear métodos estáticos
    }

    Random random = new Random();

    public Main() {

        System.out.println("Se desea filtrar los números primos");

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

        //double maxDouble = max.orElse(0).doubleValue();
        //double maxDouble = max.orElseGet(() -> getValorSiNoHayMax()).doubleValue();
        //System.out.println("Máximo = " + maxDouble);

        //max.ifPresent(value -> System.out.println("value = " + value));

//                .reduce(0, Integer::sum);

        //Estas referencias a métodos no recibe par+amatros ya que son implicitamente pasados por los otros procedimientos

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
