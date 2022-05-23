package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;

public class StringGeneratorTest {

    @Ignore
    @Test
    public void whenGenerate() {
        Generator generator = new StringGenerator();
        HashMap<String, String> keys = new HashMap<>();
        keys.put("name", "Mikhail");
        keys.put("subject", "you");
        String actual = generator.produce("I am a ${name}, Who are ${subject}? ", keys);
        String expected = "I am a Mikhail, Who are you? ";
        assertEquals(expected, actual);
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenExtraKeyInMap() {
        Generator generator = new StringGenerator();
        HashMap<String, String> keys = new HashMap<>();
        keys.put("name", "Mikhail");
        keys.put("subject", "you");
        keys.put("age", "27");
        String actual = generator.produce("I am a ${name}, Who are ${subject}? ", keys);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenExtraKeyInPattern() {
        Generator generator = new StringGenerator();
        HashMap<String, String> keys = new HashMap<>();
        keys.put("name", "Mikhail");
        keys.put("subject", "you");
        String actual = generator.produce("I am a ${name}, ${age} y.o. Who are ${subject} ", keys);

    }

}