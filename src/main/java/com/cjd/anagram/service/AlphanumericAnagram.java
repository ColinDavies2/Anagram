package com.cjd.anagram.service;


import org.springframework.stereotype.Component;

@Component
public class AlphanumericAnagram extends StrictAnagram {
    
    @Override
    public  boolean isAnagram(final String str1, final String str2) {

        if (isEmptyInput(str1, str2)) {
            return false;
        }

        return super.isAnagram(preprocess(str1), preprocess(str2));
    }

    private String preprocess(final String source) {
        return source.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
}