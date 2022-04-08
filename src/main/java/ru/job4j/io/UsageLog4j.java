package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Mikhail";
        int age = 27;
        byte fingers = 10;
        short teeth = 32;
        char sex = 'M';
        long moneys = 9223372036854775807L;
        double height = 182.2;
        float weight = 85.4f;
        boolean isGood = true;
        LOG.debug("User info name : {}, age : {}, number of fingers: {}, number of teeth: {}, sex: {}, amount of money($): {}, height: {}, weight: {}, good man: {}",
                name, age, fingers, teeth, sex, moneys, height, weight, isGood);
    }
}