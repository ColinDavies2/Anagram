package com.cjd.anagram.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class AlphabeticalAnagramTests {
  
    AlphabeticalAnagram anagram = new AlphabeticalAnagram();

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
    void isAnagram_returnsFalse_when_firstStringHasNoLetters() {

        boolean result = anagram.isAnagram(".@#{}[]-_)   (*&%^$£!?~'0123456789", "test");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_secondStringHasNoLetters() {

        boolean result = anagram.isAnagram("test", ".@#{}[]-_)   (*&%^$£!?~'0123456789");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_stringsNotSameLength() {

        boolean result = anagram.isAnagram("test", "testtest");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_stringsSameLength_but_NotSameLetters() {

        boolean result = anagram.isAnagram("test", "tess");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsTrue_when_stringsSame() {

        boolean result = anagram.isAnagram("test", "test");
        assertEquals(true, result);
    }

    @Test
    void isAnagram_returnsTrue_when_stringsSame_but_differentCase() {

        boolean result = anagram.isAnagram("abc", "ABC");
        assertEquals(true, result);
    }
 
    @Test
    void isAnagram_returnsTrue_when_stringsHaveSameLetters_but_alsoHaveOtherCharacters() {

        boolean result = anagram.isAnagram("test.@#{}[]-_)   (*&%^$£!?~'0123456789", ".@#{}[]-_)   (*&%^$£!?~'0123456789tset");
        assertEquals(true, result);
    }
}