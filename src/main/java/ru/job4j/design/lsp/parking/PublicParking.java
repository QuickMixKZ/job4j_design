package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.design.lsp.parking.PassengerCar.PASSENGER_SIZE;

public class PublicParking implements Parking {

    private int passengerCarSpace;
    private int truckSpace;
    private final List<Car> storage;
    private static final int ZERO = 0;

    public PublicParking(int passengerCarSpace, int truckSpace) {
        this.passengerCarSpace = passengerCarSpace;
        this.truckSpace = truckSpace;
        storage = new ArrayList<>(passengerCarSpace + truckSpace);
    }

    @Override
    public boolean isFreeSpace(Car car) {
        boolean result = false;
        int carSize = car.getSize();
        if (carSize == PASSENGER_SIZE && passengerCarSpace >= PASSENGER_SIZE) {
            result = true;
            --passengerCarSpace;
        } else if (carSize > PASSENGER_SIZE && truckSpace != ZERO) {
                result = true;
                --truckSpace;
        } else if (carSize > PASSENGER_SIZE && passengerCarSpace >= carSize) {
                result = true;
                passengerCarSpace -= carSize;
        }
        return result;
    }

    @Override
    public boolean parkCar(Car car) {
        boolean result = isFreeSpace(car);
        if (result) {
            storage.add(car);
        }
        return result;
    }
}
