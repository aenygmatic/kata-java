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

import java.util.Iterator;

public class RomanConverter {

    public String arabicToRoman(int number) {
        return new ArabicToRomanConverter(number).arabicToRoman();
    }

    public int romanToArabic(String symbol) {
        return 0;
    }

    private enum RomanGlyphs {

        I(1, null),
        V(5, RomanGlyphs.I),
        X(10, RomanGlyphs.I),
        L(50, RomanGlyphs.X),
        C(100, RomanGlyphs.X),
        D(500, RomanGlyphs.C),
        M(1000, RomanGlyphs.C);

        private int value;
        private RomanGlyphs magnitude;

        private RomanGlyphs(int value, RomanGlyphs magnitude) {
            this.value = value;
            this.magnitude = magnitude;
        }
    }

    private static class ArabicToRomanConverter {

        private StringBuilder roman = new StringBuilder();
        private int root;

        public ArabicToRomanConverter(int root) {
            this.root = root;
        }

        private String arabicToRoman() {
            for (RomanGlyphs glyph : new ReservedEnumIterator<>(RomanGlyphs.values())) {
                appendGlyph(glyph);
            }
            return roman.toString();
        }

        private void appendGlyph(RomanGlyphs glyph) {
            int divisor = root / glyph.value;
            if (divisor > 0) {
                if (isRuleFourApplied(glyph, divisor)) {
                    appendUpperMinusGlyph(glyph, divisor);
                } else if (isRuleNineApplied(glyph)) {
                    appendUpperMinusMagnitude(glyph);
                } else {
                    appendGlyphMultiplied(divisor, glyph);
                }
            }
        }

        private void appendGlyphMultiplied(int divisor, RomanGlyphs glyph) {
            for (int i = 0; i < divisor; i++) {
                roman.append(glyph);
            }
            decrementRootBy(divisor * glyph.value);
        }

        private void appendUpperMinusMagnitude(RomanGlyphs glyph) {
            roman.append(glyph.magnitude).append(upper(glyph));
            decrementRootBy(9 * glyph.magnitude.value);
        }

        private void appendUpperMinusGlyph(RomanGlyphs glyph, int divisor) {
            roman.append(glyph).append(upper(glyph));
            decrementRootBy(divisor * glyph.value);
        }

        private boolean isRuleNineApplied(RomanGlyphs glyph) {
            return hasUpper(glyph) && hasMagnitude(glyph) && root / glyph.magnitude.value == 9;
        }

        private static boolean hasMagnitude(RomanGlyphs glyph) {
            return glyph.magnitude != null;
        }

        private boolean isRuleFourApplied(RomanGlyphs glyph, int divisor) {
            return hasUpper(glyph) && divisor == 4;
        }

        private void decrementRootBy(int i) {
            root -= i;
        }

        private static RomanGlyphs upper(RomanGlyphs glyph) {
            return RomanGlyphs.values()[glyph.ordinal() + 1];
        }

        private boolean hasUpper(RomanGlyphs glyph) {
            return glyph.ordinal() < RomanGlyphs.values().length - 1;
        }
    }

    private static class ReservedEnumIterator<E extends Enum<E>> implements Iterator<E>, Iterable<E> {

        private E[] values;
        private int index;

        public ReservedEnumIterator(E[] values) {
            this.values = values;
            this.index = values.length;
        }

        @Override
        public boolean hasNext() {
            return index > 0;
        }

        @Override
        public E next() {
            return values[--index];
        }

        @Override
        public Iterator<E> iterator() {
            return this;
        }
    }
}
