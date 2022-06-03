package ru.job4j.design.lsp.parking;

public class PassengerCar implements  Car {

    public static final int PASSENGER_SIZE = 1;

    @Override
    public int getSize() {
        return PASSENGER_SIZE;
    }

}
