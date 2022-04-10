package ru.job4j.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.json.Dog;
import ru.job4j.serialization.json.DogHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        DogHandler dogHandler = new DogHandler("Mikhail");
        Dog puppy1 = new Dog(false, 1, "Barbos", dogHandler);
        Dog puppy2 = new Dog(false, 1, "Not Barbos", dogHandler);
        Dog[] puppies = {puppy1, puppy2};
        Dog dog = new Dog(true, 7, "Murzik", dogHandler, puppies);
        JAXBContext context = JAXBContext.newInstance(Dog.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(dog, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Dog result = (Dog) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
