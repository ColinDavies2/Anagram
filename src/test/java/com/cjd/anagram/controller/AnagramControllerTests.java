package com.cjd.anagram.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import com.cjd.anagram.domain.AnagramMode;
import com.cjd.anagram.service.StrictAnagram;
import com.cjd.anagram.service.AlphabeticalAnagram;
import com.cjd.anagram.service.AlphanumericAnagram;
import com.cjd.anagram.StringToAnagramModeConverter;

public class AnagramControllerTests {

    private final AnagramController anagramController = new AnagramController(new StrictAnagram(),
            new AlphabeticalAnagram(), new AlphanumericAnagram());

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        final FormattingConversionService formattingConversionService = new FormattingConversionService();
        formattingConversionService.addConverter(new StringToAnagramModeConverter());

        this.mockMvc = MockMvcBuilders.standaloneSetup(anagramController)
                .setConversionService(formattingConversionService).build();
    }

    @Test
    public void noMode_withMatchingStrings() throws Exception {

        final AnagramMode mode = null; 
        final String str1 = "test";
        final String str2 = "test";
        final String url = buildUrl(mode, str1, str2);
        final String expected = buildExpectedString(mode, str1, str2, true);
        
        this.mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void strictMode_withMatchingStrings() throws Exception {

        final AnagramMode mode = AnagramMode.STRICT; 
        final String str1 = "[]{}";
        final String str2 = "[]{}";
        final String url = buildUrl(mode, str1, str2);
        final String expected = buildExpectedString(mode, str1, str2, true);

        this.mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void alphabeticalMode_withMatchingStrings() throws Exception {

        final AnagramMode mode = AnagramMode.ALPHABETICAL; 
        final String str1 = "test";
        final String str2 = "ttes";
        final String url = buildUrl(mode, str1, str2);
        final String expected = buildExpectedString(mode, str1, str2, true);

        this.mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void alphanumericMode_withMatchingStrings() throws Exception {

        final AnagramMode mode = AnagramMode.ALPHANUMERIC; 
        final String str1 = "1234";
        final String str2 = "4321";
        final String url = buildUrl(mode, str1, str2);
        final String expected = buildExpectedString(mode, str1, str2, true);

        this.mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void noMode_withNonMatchingStrings() throws Exception {

        final AnagramMode mode = null; 
        final String str1 = "test";
        final String str2 = "tess";
        final String url = buildUrl(mode, str1, str2);
        final String expected = buildExpectedString(mode, str1, str2, false);

        this.mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void strictMode_withNonMatchingStrings() throws Exception {

        final AnagramMode mode = AnagramMode.STRICT; 
        final String str1 = "[]{}";
        final String str2 = "[]{}*";
        final String url = buildUrl(mode, str1, str2);
        final String expected = buildExpectedString(mode, str1, str2, false);

        this.mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void alphabeticalMode_withNonMatchingStrings() throws Exception {

        final AnagramMode mode = AnagramMode.ALPHABETICAL; 
        final String str1 = "test";
        final String str2 = "tess";
        final String url = buildUrl(mode, str1, str2);
        final String expected = buildExpectedString(mode, str1, str2, false);

        this.mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void alphanumericMode_withNonMatchingStrings() throws Exception {

        final AnagramMode mode = AnagramMode.ALPHANUMERIC; 
        final String str1 = "test1";
        final String str2 = "test2";
        final String url = buildUrl(mode, str1, str2);
        final String expected = buildExpectedString(mode, str1, str2, false);

        this.mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(content().string(expected));
    }

    @Test
    public void missingString1() throws Exception {

        final AnagramMode mode = null; 
        final String str1 = null;
        final String str2 = "test";
        final String url = buildUrl(mode, str1, str2);

        this.mockMvc.perform(get(url)).andExpect(status().isBadRequest())
                .andExpect(content().string("string-1 not supplied"));
    }

    @Test
    public void missingString2() throws Exception {

        final AnagramMode mode = null; 
        final String str1 = "test";
        final String str2 = null;
        final String url = buildUrl(mode, str1, str2);

        this.mockMvc.perform(get(url)).andExpect(status().isBadRequest())
                .andExpect(content().string("string-2 not supplied"));
    }

    @Test
    public void invalidMode() throws Exception {

        this.mockMvc.perform(get("/is-anagram?mode=invalid&string-1=test&string-2=test"))
                .andExpect(status().isBadRequest()).andExpect(content().string("Invalid mode supplied"));
    }

    private String buildUrl(final AnagramMode mode, final String str1, final String str2) {

        final List<String> queryParams = buildQueryParameterList(mode, str1, str2);
        final StringBuilder builder = new StringBuilder();
        
        builder.append("/is-anagram");
        
        for (int i = 0; i < queryParams.size(); i++) {
            if (i == 0) {
                builder.append("?");
            }
            else {
                builder.append("&");
            }
            builder.append(queryParams.get(i));
        }

 
        return builder.toString();
    }

    private List<String> buildQueryParameterList(final AnagramMode mode, final String str1, final String str2) {
        final List<String> queryParams = new ArrayList<>();

        if (mode != null) {
            queryParams.add("mode=" + mode.toString());
        }

        if (str1 != null) {
            queryParams.add("string-1=" + str1);
        }

        if (str2 != null) {
            queryParams.add("string-2=" + str2);
        }

        return queryParams;
    }

    private String buildExpectedString(final AnagramMode mode, final String str1, final String str2, boolean isAnnulus) {

        final StringBuilder builder = new StringBuilder();

        builder.append(str1);
        builder.append(" and ");
        builder.append(str2);
        builder.append(" are ");
        if (!isAnnulus) {
            builder.append("not ");
        }

        if (mode == null) {
            builder.append(AnagramMode.ALPHABETICAL.toString());
        }
        else {
            builder.append(mode.toString());
        }

        builder.append(" mode anagrams");

        return builder.toString();
    }


}