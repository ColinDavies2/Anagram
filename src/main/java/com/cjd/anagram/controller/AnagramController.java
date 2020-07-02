package com.cjd.anagram.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.EnumMap;

import com.cjd.anagram.service.StrictAnagram;
import com.cjd.anagram.service.AlphabeticalAnagram;
import com.cjd.anagram.service.AlphanumericAnagram;
import com.cjd.anagram.domain.AnagramMode;
import com.cjd.anagram.service.Anagram;


@RestController
public class AnagramController {
 
    StrictAnagram strictAnagram;
    AlphabeticalAnagram alphabeticalAnagram;
    AlphanumericAnagram alphanumericAnagram;

    Map<AnagramMode, Anagram> anagrams = new EnumMap<>(AnagramMode.class);

    @Autowired
    public AnagramController(final StrictAnagram strictAnagram, final AlphabeticalAnagram alphabeticalAnagram,
            final AlphanumericAnagram alphanumericAnagram) {
        anagrams.put(AnagramMode.STRICT, strictAnagram);
        anagrams.put(AnagramMode.ALPHABETICAL, alphabeticalAnagram);
        anagrams.put(AnagramMode.ALPHANUMERIC, alphanumericAnagram);
    }
 
    @GetMapping("/is-anagram")
    public String isAnagram(@RequestParam(name = "string-1", required = true) final String str1,
                            @RequestParam(name = "string-2", required = true) final String str2,
                            @RequestParam(name = "mode", required = false, defaultValue = "ALPHABETICAL") final AnagramMode mode) {

        Anagram anagram = anagrams.get(mode);
        boolean isAnagram = anagram.isAnagram(str1, str2);
        return buildResponseString(mode, str1, str2, isAnagram);
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    String handleTypeMismatch(final Throwable ex) {
        return "Invalid mode supplied";
    }

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    String handleMissingParameter(final Throwable exception) {
        final MissingServletRequestParameterException ex = (MissingServletRequestParameterException) exception;
        return ex.getParameterName() + " not supplied";
    }
  
    private String buildResponseString(final AnagramMode mode, final String str1, final String str2, boolean isAnagram) {

        final StringBuilder builder = new StringBuilder();

        builder.append(str1);
        builder.append(" and ");
        builder.append(str2);
        builder.append(" are ");
        if (!isAnagram) {
            builder.append("not ");
        }

        builder.append(mode.toString());
        builder.append(" mode anagrams");

        return builder.toString();
    }

}