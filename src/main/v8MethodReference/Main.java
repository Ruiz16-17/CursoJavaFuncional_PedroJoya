package main.v8MethodReference;

import java.util.Random;

import static main.v8MethodReference.NumberUtils.isPrime;
import static main.v8MethodReference.NumberUtils.toSquare;

public class Main {
    public static void main(String[] args) {
        new Main();
        //Esto se hace para evitar crear métodos estáticos
    }

        Random random = new Random();
    public Main() {

        System.out.println("Se desean filtrar los números primos");

        Integer totalFlujo = Flujo.provide(10, this::randomInt)
                .filter(NumberUtils::isPrime)
                .transformation(NumberUtils::toSquare)
                .act(System.out::println)
                .reduce(0, Integer::sum);

        //Estas referencias a métodos no recibe par+amatros ya que son implicitamente pasados por los otros procedimientos

        System.out.println("totalFlujo = " + totalFlujo);
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
