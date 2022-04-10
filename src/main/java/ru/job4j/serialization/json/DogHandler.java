package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dog_handler")
public class DogHandler {
    @XmlAttribute
    String name;

    public DogHandler() {

    }

    public DogHandler(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DogHandler{"
                + "name='" + name + '\''
                + '}';
    }

    public String getName() {
        return name;
    }
}
