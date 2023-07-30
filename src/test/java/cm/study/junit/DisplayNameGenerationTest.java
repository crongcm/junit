package cm.study.junit;

import org.junit.jupiter.api.*;

class DisplayNameGenerationTest {

    @Nested
    @IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
    class IndicativeSentencesGenerationTestClass {
        @Test
        void test_display_name_generation() {}
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
    class IndicativeSentencesTestClass {
        @Test
        void test_display_name_generation() {}
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class ReplaceUnderscoresTestClass {
        @Test
        void test_display_name_generation() {}
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.Simple.class)
    class SimpleTestClass {
        @Test
        void test_display_name_generation() {}
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.Standard.class)
    class StandardTestClass {
        @Test
        void test_display_name_generation() {}
    }

}
