package com.grizzly.jsearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class WordTest {

    @Test
    void shouldFilterStopWords() {

        Word word = new Word();
        List<String> result = word.parse("Java 9898 coding is very cool Don't");

        assertTrue(result.contains("java"));
        assertTrue(result.contains("coding"));
        assertTrue(result.contains("cool"));
        assertFalse(result.contains("don"));
        assertFalse(result.contains("is"));
        assertFalse(result.contains("t"));
        assertFalse(result.contains("very"));
    }
}