package cm.study.junit;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MethodOrderedTest {
    @Test
    @Order(2)
    void secondMethod() {}

    @Test
    @Order(3)
    void thirdMethod() {}

    @Test
    @Order(1)
    void firstMethod() {}
}
