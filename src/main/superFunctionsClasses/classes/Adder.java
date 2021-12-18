package main.superFunctionsClasses.classes;

import main.superFunctionsClasses.interfaces.BinaryFunction2;

public class Adder implements BinaryFunction2 {

    @Override
    public Integer apply(Integer value1, Integer value2) {
        return value1 + value2;
    }

}
