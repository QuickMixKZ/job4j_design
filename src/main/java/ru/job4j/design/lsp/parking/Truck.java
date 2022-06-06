package ru.job4j.design.lsp.parking;


import static ru.job4j.design.lsp.parking.PassengerCar.PASSENGER_SIZE;

public class Truck implements Car {

    private int size;

    public Truck(int size) {
        if (size <= PASSENGER_SIZE) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

}
