package com.cjd.anagram;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cjd.anagram.domain.AnagramMode;

@Component
public class StringToAnagramModeConverter implements Converter<String, AnagramMode> {

	@Override
    public AnagramMode convert(String source) {
        return AnagramMode.valueOf(source.toUpperCase());
    }
}

