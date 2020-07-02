package com.cjd.anagram.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class StrictAnagramTests {
  
    StrictAnagram anagram = new StrictAnagram();

    @Test
    void isAnagram_returnsFalse_when_firstStringNull() {

        boolean result = anagram.isAnagram(null, "test");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_secondStringNull() {

        boolean result = anagram.isAnagram("test", null);
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_firstStringEmpty() {

        boolean result = anagram.isAnagram("", "test");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_secondStringEmpty() {

        boolean result = anagram.isAnagram("test", "");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_stringsNotSameLength() {

        boolean result = anagram.isAnagram("[]{}", "[]{}}");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_stringsSameLength_but_NotSameCharacters() {

        boolean result = anagram.isAnagram("[]{}", "[]{*");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsTrue_when_stringsSame() {

        boolean result = anagram.isAnagram("[]{}", "[]{}");
        assertEquals(true, result);
    }

    @Test
    void isAnagram_returnsTrue_when_stringsSame_but_differentCase() {

        boolean result = anagram.isAnagram("[]{}a", "[]{}A");
        assertEquals(false, result);
    }
}