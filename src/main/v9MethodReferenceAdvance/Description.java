package main.v9MethodReferenceAdvance;

public class Description {

    private final Integer value;

    public Description(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "V(" + value + ")";
    }
}
