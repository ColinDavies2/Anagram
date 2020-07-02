package com.cjd.anagram.domain;


public enum AnagramMode {
    STRICT,
    ALPHABETICAL,
    ALPHANUMERIC;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}