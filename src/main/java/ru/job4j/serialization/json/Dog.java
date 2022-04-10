package ru.job4j.serialization.json;

import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "dog")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dog {
    @XmlAttribute
    boolean vaccinated;
    @XmlAttribute
    int age;
    @XmlAttribute
    String name;
    @XmlElement
    DogHandler handler;
    @XmlElementWrapper(name = "children")
    @XmlElement(name = "child")
    Dog[] children;

    public Dog() {

    }

    public Dog(boolean vaccinated, int age, String name, DogHandler handler, Dog[] children) {
        this.vaccinated = vaccinated;
        this.age = age;
        this.name = name;
        this.handler = handler;
        this.children = children;
    }

    public Dog(boolean vaccinated, int age, String name, DogHandler handler) {
        this.vaccinated = vaccinated;
        this.age = age;
        this.name = name;
        this.handler = handler;
    }

    @Override
    public String toString() {
        return "Dog{"
                + "vaccinated=" + vaccinated
                + ", age=" + age
                + ", name='" + name + '\''
                + ", handler=" + handler
                + ", children=" + Arrays.toString(children)
                + "}";
    }

    public static void main(String[] args) {
        DogHandler dogHandler = new DogHandler("Mikhail");
        Dog puppy1 = new Dog(false, 1, "Barbos", dogHandler);
        Dog puppy2 = new Dog(false, 1, "Not Barbos", dogHandler);
        Dog[] puppies = {puppy1, puppy2};
        Dog dog = new Dog(true, 7, "Murzik", dogHandler, puppies);
        Gson gson = new GsonBuilder().create();
        String jsonDog = gson.toJson(dog);
        System.out.println(jsonDog);
        Dog dogFromJson = gson.fromJson(jsonDog, Dog.class);
        System.out.println(dogFromJson);
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public DogHandler getHandler() {
        return handler;
    }

    public Dog[] getChildren() {
        return children;
    }
}
