package cm.study.junit;

import org.junit.jupiter.api.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class ClassOrderedTest {
    @Test
    @Order(2)
    void secondTest() {
        System.out.println(1);
    }

    @Test
    @Order(3)
    void thirdTest() {
        System.out.println(3);
    }

    @Test
    @Order(1)
    void primaryTest() {
        System.out.println(1);
    }

    @Nested
    @Order(5)
    class SecondaryTests {
        @Test
        void test2() {
            System.out.println(5);
        }
    }

    @Nested
    @Order(4)
    class PrimaryTests {
        @Test
        void test1() {
            System.out.println(4);
        }
    }
}
