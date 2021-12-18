package main.v9MethodReferenceAdvance;

import java.util.Comparator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        new Main();
        //Esto se hace para evitar crear métodos estáticos
    }

        Random random = new Random();
    public Main() {

        System.out.println("Se desea filtrar los números primos");

        Integer totalFlujo = Flujo.provide(10, this::randomInt)
                .filter(NumberUtils::isPrime)
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
