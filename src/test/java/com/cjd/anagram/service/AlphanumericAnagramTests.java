package com.cjd.anagram.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class AlphanumericAnagramTests {
  
    AlphanumericAnagram anagram = new AlphanumericAnagram();

    @Test
    void isAnagram_returnsFalse_when_firstStringNull() {

        boolean result = anagram.isAnagram(null, "test1");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_secondStringNull() {

        boolean result = anagram.isAnagram("test1", null);
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_firstStringEmpty() {

        boolean result = anagram.isAnagram("", "test1");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_secondStringEmpty() {

        boolean result = anagram.isAnagram("test1", "");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_firstStringHasNoLettersOrDigits() {

        boolean result = anagram.isAnagram(".@#{}[]-_)   (*&%^$£!?~'", "test");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_secondStringHasNoLettersOrDigits() {

        boolean result = anagram.isAnagram("test", ".@#{}[]-_)   (*&%^$£!?~'");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_stringsNotSameLength() {

        boolean result = anagram.isAnagram("test1", "test12");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsFalse_when_stringsSameLength_but_NotSameLettersAndDigits() {

        boolean result = anagram.isAnagram("test1", "test2");
        assertEquals(false, result);
    }

    @Test
    void isAnagram_returnsTrue_when_stringsSame() {

        boolean result = anagram.isAnagram("test1", "test1");
        assertEquals(true, result);
    }

    @Test
    void isAnagram_returnsTrue_when_stringsSame_but_differentCase() {

        boolean result = anagram.isAnagram("abc", "ABC");
        assertEquals(true, result);
    }
 
    @Test
    void isAnagram_returnsTrue_when_stringsHaveSameLettersAndDigits_but_alsoHaveOtherCharacters() {

        boolean result = anagram.isAnagram("test1.@#{}[]-_)   (*&%^$£!?~'", ".@#{}[]-_)   (*&%^$£!?~'t1set");
        assertEquals(true, result);
    }
}