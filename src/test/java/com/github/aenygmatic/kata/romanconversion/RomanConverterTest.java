/*
 * Copyright 2014 Balazs Berkes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.aenygmatic.kata.romanconversion;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RomanConverterTest {

    private RomanConverter underTest = new RomanConverter();

    @Test
    public void testArabicToRoman1000s() {
        assertThat(romanVersionOf(1000), is("M"));
        assertThat(romanVersionOf(2000), is("MM"));
        assertThat(romanVersionOf(3000), is("MMM"));
    }

    @Test
    public void testArabicToRoman100s() {
        assertThat(romanVersionOf(100), is("C"));
        assertThat(romanVersionOf(200), is("CC"));
        assertThat(romanVersionOf(300), is("CCC"));
    }

    @Test
    public void testArabicToRoman1000sAnd100sMixed() {
        assertThat(romanVersionOf(1100), is("MC"));
        assertThat(romanVersionOf(2200), is("MMCC"));
    }

    @Test
    public void testArabicToRomanFourRule() {
        assertThat(romanVersionOf(4), is("IV"));
        assertThat(romanVersionOf(40), is("XL"));
        assertThat(romanVersionOf(400), is("CD"));
    }

    @Test
    public void testArabicToRomanNineRule() {
        assertThat(romanVersionOf(9), is("IX"));
        assertThat(romanVersionOf(90), is("XC"));
        assertThat(romanVersionOf(900), is("CM"));
    }

    @Test
    public void testArabicToRomanWithRealNumber() {
        assertThat(romanVersionOf(1988), is("MCMLXXXVIII"));
        assertThat(romanVersionOf(1998), is("MCMXCVIII"));
    }

    @Test
    public void testRomanToArabic() {

    }

    private String romanVersionOf(int arabic) {
        return underTest.arabicToRoman(arabic);
    }
}
