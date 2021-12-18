package main.superFunctionsClasses.classes;

import main.superFunctionsClasses.interfaces.Function2;

public class ToSquare implements Function2 {
    @Override
    public Integer apply(Integer value) {
        return value*value;
    }
}
