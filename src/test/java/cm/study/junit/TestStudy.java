package cm.study.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;

// @ExtendWith(FindSlowTestExtension.class)
class TestStudy {
    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Test
    @DisplayName("테스트")
    void test() {
        System.out.println("test");
    }

    @Test
    void test2() throws InterruptedException {
        Thread.sleep(1005L);
        System.out.println("test2");
    }

    @Test
    @Disabled
    void disabled_test() {
        System.out.println("disabled");
    }

    // 딱 한번만 실행, static void로 작성
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
        System.out.println();
    }

    @AfterAll
    static void afterAll() {
        System.out.println();
        System.out.println("after all");
    }

    // 모든 테스트가 실행되기 전에 한번씩 실행
    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }

}
