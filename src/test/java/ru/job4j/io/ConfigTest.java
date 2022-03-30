package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Mikhail"));
    }

    @Test
    public void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Mikhail"));
        assertThat(config.value("year"), is("2022"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPattern() {
        String path = "./data/pair_with_wrong_pattern.properties";
        Config config = new Config(path);
        config.load();
    }
}