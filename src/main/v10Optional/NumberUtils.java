package main.v10Optional;

public class NumberUtils {

    private NumberUtils() {
    }

    //Se crea el constructor privado para que no se pueda instanciar esta clase debido a que solo va a contener métodos
    // estáticos, esto porque es una clase de utilidades

    public static boolean isPrime(int value){
        for (int i = 2; i < value; i++) {
            if(value % i == 0){
                return false;
            }
        }
        return true;
    }

    public static int toSquare(int value){
        return value * value;
    }

}
