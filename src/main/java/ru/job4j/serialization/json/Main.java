package ru.job4j.serialization.json;

import org.json.JSONObject;

public class Main {


    public static void main(String[] args) {
        DogHandler dogHandler = new DogHandler("Mikhail");
        Dog puppy1 = new Dog(false, 1, "Barbos", dogHandler);
        Dog puppy2 = new Dog(false, 1, "Not Barbos", dogHandler);
        Dog[] puppies = {puppy1, puppy2};
        Dog dog = new Dog(true, 7, "Murzik", dogHandler, puppies);
        JSONObject jsonDog = new JSONObject(dog);
        String jsonString = jsonDog.toString();
        System.out.println(jsonString);
    }
}
