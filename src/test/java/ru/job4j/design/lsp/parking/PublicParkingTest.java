package ru.job4j.design.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class PublicParkingTest {

    @Test
    public void whenParkedTwoPassengerCarsAndOneTruck() {
        Parking parking = new PublicParking(2, 1);
        Car passengerCar = new PassengerCar();
        Car passengerCar1 = new PassengerCar();
        Car passengerCar2 = new PassengerCar();
        Car truck = new Truck();
        Car truck1 = new Truck();
        assertTrue(parking.parkCar(passengerCar));
        assertTrue(parking.parkCar(passengerCar1));
        assertTrue(parking.parkCar(truck));
        assertFalse(parking.parkCar(passengerCar2));
        assertFalse(parking.parkCar(truck1));
    }

    @Test
    public void whenParkedTwoTrucksAndOnePassengerCar() {
        Parking parking = new PublicParking(5, 0);
        Car passengerCar = new PassengerCar();
        Car passengerCar1 = new PassengerCar();
        Car truck = new Truck();
        Car truck1 = new Truck();
        assertTrue(parking.parkCar(truck));
        assertTrue(parking.parkCar(truck1));
        assertTrue(parking.parkCar(passengerCar));
        assertFalse(parking.parkCar(passengerCar1));
    }

    @Test
    public void whenNothingParked() {
        Parking parking = new PublicParking(0, 0);
        Car passengerCar = new PassengerCar();
        Car passengerCar1 = new PassengerCar();
        Car truck = new Truck();
        Car truck1 = new Truck();
        assertFalse(parking.parkCar(truck));
        assertFalse(parking.parkCar(truck1));
        assertFalse(parking.parkCar(passengerCar));
        assertFalse(parking.parkCar(passengerCar1));
    }

    @Test
    public void whenParkedMonsterTruckAndTruck() {
        Parking parking = new PublicParking(2, 1);
        Car passengerCar = new PassengerCar();
        Car truck = new Truck();
        Car monsterTruck = new MonsterTruck();
        assertTrue(parking.parkCar(monsterTruck));
        assertTrue(parking.parkCar(truck));
        assertFalse(parking.parkCar(passengerCar));
    }

}