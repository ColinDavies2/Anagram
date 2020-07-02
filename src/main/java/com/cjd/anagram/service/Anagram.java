package com.cjd.anagram.service;

import org.springframework.stereotype.Component;


public interface Anagram {
    boolean isAnagram(String str1, String str2); 
}