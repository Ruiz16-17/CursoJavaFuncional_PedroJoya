package main.superFunctionsClasses.classes;

import main.superFunctionsClasses.interfaces.Provider;

import java.util.Random;

public class Random2 implements Provider {

    Random random = new Random();

    @Override
    public Integer get() {
        return random.nextInt(1000);
    }
}
