package main.superFunctionsClasses.classes;

import main.superFunctionsClasses.interfaces.Consumer2;

public class Printer2 implements Consumer2 {
    @Override
    public void accept(Integer value) {
        System.out.println("value = " + value);
    }
}
