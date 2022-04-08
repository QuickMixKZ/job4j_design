package ru.job4j.serialization.json;

public class DogHandler {
    String name;

    public DogHandler(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DogHandler{"
                + "name='" + name + '\''
                + '}';
    }
}
