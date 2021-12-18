package main.superFunctionsClasses.classes;

import main.superFunctionsClasses.interfaces.Predicate;

public class OnlyPairs implements Predicate {
    @Override
    public boolean apply(Integer value) {
        return value % 2 == 0;
    }
}
