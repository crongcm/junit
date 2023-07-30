package cm.study.junit;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TestInstance Annotation은 설정에 따라 하나의 클래스에서 인스턴스 생명주기를 공유하게 한다.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InstanceTest {
    // 기본적으로 테스트 메소드별 자원을 공유하지 않는다.
    int value = 1;

    // 테스트 순서를 보장하지 않는다.
    @Test
    void test1() {
        System.out.println(this);
        System.out.println(++value);    // default : 2, PER_CLASS.actual : 2
    }

    @Test
    void test2() {
        System.out.println(this);
        System.out.println(value++);    // default : 1, PER_CLASS.actual : 2
    }

    public enum OrderStatus {
        PREPARING(1, "준비"),
        ORDERING(2, "주문"),
        SHIPPING(3, "출고"),
        DELIVERY(4, "배송중"),
        DELIVERED(5, "배송완료");

        private final Integer type;
        private final String description;

        OrderStatus(Integer type, String description) {
            this.type = type;
            this.description = description;
        }
//        public static Integer getType(OrderStatus orderStatus) {
//            EnumMap<OrderStatus, Integer> typeMap = new EnumMap<>(OrderStatus.class);
//            Arrays.stream(OrderStatus.values())
//                    .forEach(os -> typeMap.put(os, os.type));
//            return typeMap.get(orderStatus);
//        }
//
//        public static String getDescription(OrderStatus orderStatus) {
//            EnumMap<OrderStatus, String> descMap = new EnumMap<>(OrderStatus.class);
//            Arrays.stream(OrderStatus.values())
//                    .forEach(os -> descMap.put(os, os.description));
//            return descMap.get(orderStatus);
//        }


        public Integer getType() {
            return type;
        }

        public String getDescription() {
            return description;
        }
    }

    @Test
    @DisplayName("enumTest")
    void enumTest() throws Exception {
        Thread.sleep(100);
        long start3 = System.currentTimeMillis();
        EnumMap<OrderStatus, Integer> enumMap = new EnumMap<>(OrderStatus.class);
        for (OrderStatus orderStatus : OrderStatus.values()) {
            enumMap.put(orderStatus, orderStatus.getType());
        }
        for (Integer integer : enumMap.values()) {
            System.out.println("integer = " + integer);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("end3 - start3 = " + (end3 - start3));

        long start2 = System.currentTimeMillis();
        EnumMap<OrderStatus, Integer> typeMap = new EnumMap<>(OrderStatus.class);
        Arrays.stream(OrderStatus.values())
                .forEach(os -> typeMap.put(os, os.type));
        for (Integer integer : typeMap.values()) {
            System.out.println("integer = " + integer);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("end2 - start2 = " + (end2 - start2));

        // given
        long start = System.currentTimeMillis();
        Map<OrderStatus, Integer> orderStatusIntegerMap =
                Collections.unmodifiableMap(Stream.of(OrderStatus.values())
                .collect(Collectors.toMap(Function.identity(), OrderStatus::getType)));
        long end = System.currentTimeMillis();
        for (Integer integer : orderStatusIntegerMap.values()) {
            System.out.println("integer = " + integer);
        }
        System.out.println("end - start = " + (end - start));


        // when

        // then
    }
}
