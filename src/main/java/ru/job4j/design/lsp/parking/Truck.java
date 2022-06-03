package ru.job4j.design.lsp.parking;


public class Truck implements Car {

    public static final int MIN_SIZE = 2;

    private int size;

    public Truck(int size) {
        if (size < MIN_SIZE) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

}
