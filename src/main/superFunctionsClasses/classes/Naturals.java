package main.superFunctionsClasses.classes;

import main.superFunctionsClasses.interfaces.Provider;

import java.util.Random;

public class Naturals implements Provider {

    private static int next = 0;

    public Integer get() {
        return next++;
    }
}
