package com.cjd.anagram.service;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import org.springframework.stereotype.Component;


@Component
public class StrictAnagram implements Anagram {
    
    @Override
    public boolean isAnagram(final String str1, final String str2) {

        if (isEmptyInput(str1, str2) || str1.length() != str2.length()) {
            return false;
        }

        Multiset<Character> multiset1 = HashMultiset.create();
        Multiset<Character> multiset2 = HashMultiset.create();
        for (int i = 0; i < str1.length(); i++) {
            multiset1.add(str1.charAt(i));
            multiset2.add(str2.charAt(i));
        }
        return multiset1.equals(multiset2);
    }

    protected boolean isEmptyInput(final String str1, final String str2) {
        return StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2);
    }

}