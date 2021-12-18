package main.v7InterfacesFuncionalesEstandar;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        new Main();
        //Esto se hace para evitar crear métodos estáticos
    }

        Random random = new Random();
    public Main() {

        System.out.println("------------------------ENCADENADO----------------------------------");
        Integer totalFlujo = Flujo.provide(10, () -> random.nextInt(10))
                .filter(value -> value % 2 == 0)
                .transformation(value -> value * value)
                .act(value -> System.out.println(value))
                .reduce(0, (value1, value2) -> value1 + value2);

        System.out.println("totalFlujo = " + totalFlujo);
    }
            /*new Provider<Integer>() {
            Random random = new Random();
                    @Override
                    public Integer get() {
                        return random.nextInt(10);
                    }
                }*/

}
