package ru.job4j.design.lsp.parking;

public class Truck implements Car {

    @Override
    public int getSize() {
        return 2;
    }

}
