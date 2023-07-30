![jmeter](https://github.com/crongcm/test/assets/113030711/1dd4dfab-66fb-4784-aa94-9402873ce750)# test

# JUnit

JUnit 플랫폼은 JVM에서 테스트 프레임워크를 시작하는 토대 역할을 합니다.

플랫폼에서 실행되는 테스트 프레임워크를 개발하기 위한 테스트 엔진 API

이 플랫폼은 명령행에서 플랫폼을 시작할 수 있는 콘솔 런처와 플랫폼에서 하나 이상의 테스트 엔진을 사용하여 사용자 정의 테스트 세트를 실행할 수 있는 JUnit Platform Suite 엔진을 제공.

JUnit5 = `JUnit Platform` + `JUnit Jupiter` + `JUnit Vintage` 이 세개를 합친 것이다.

### **JUnit Platform**

JUnit Platform은 **JVM에서 테스트 프레임워크를 실행하는데 기초**를 제공한다. 또한 TestEngine API를 제공해 테스트 프레임워크를 개발할 수 있다.

### **JUnit Jupiter**

JUnit Jupiter는 JUnit 5에서 테스트를 작성하고 확장을 하기 위한 새로운 프로그래밍 모델과 확장 모델의 조합이다. (JUnit5 서비스 구현체)

### **JUnit Vintage**

JUnit Vintage는 **하위 호환성을** 위해 JUnit3과 JUnt4를 기반으로 돌아가는 플랫폼에 `테스트 엔진`을 제공해준다.

❗ JUnit5은 java 8부터 지원하며, 이전 버전으로 작성된 테스트 코드여도 컴파일이 정상적으로 지원된다.

JUnit properties Config

/src/test/resources/junit-platform.properties

```makefile
#테스트 인스턴스 라이프사이클 설정
junit.jupiter.testinstance.lifecycle.default=per_class
#확장팩 자동 감지 기능
junit.jupiter.extensions.autodetection.enabled=true
#@Disabled 무시하고 실행하기
junit.jupiter.conditions.deactivate=org.junit.*DisabledCondition
#테스트 이름 표기 전략 설정
junit.jupiter.displayname.generator.default=\
  org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores
```

## Annotation

테스트를 구성하고, 프레임워크를 상속하기 위해서 다음과 같은 어노테이션을 지원한다.

따로 명시하지 않으면 대부분 junit-jupiter-api 모듈 안의 `org.junit.jupiter.api` 패키지안에 존재한다.

| Annotation                                                                                                            | Description                                                                   |
| --------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------- |
| @Test                                                                                                                 | Method가 Test Method임을 나타낸다.                                            |
| @ParameterizedTest                                                                                                    | 매개변수 테스트 (org.junit.jupiter.params.provider.\* Annotation 사용)        |
| @RepeatedTest                                                                                                         | 반복 실행 테스트                                                              |
| @TestFactory                                                                                                          | 동적 테스트를 위한 TestFactory<br/>Return Type으로 반드시 Stream, Collection, Iterator, Single DynamicNode, Array of DynamicNode를 반환해야 합니다. |
| @TestTemplate                                                                                                         | Provider가 반환하는 호출 컨텍스트의 수에 따라 여러변 호출되도록 설계된 테스트 <br/>하나 이상의 TestTemplateInvocationContextProvider가 등록된 경우에만 실행 |
| @DisplayName                                                                                                          | 테스트 클래스나 테스트 메소드에 테스트 완료시 표시 할 이름을 설정             |
| @DisplayNameGeneration                                                                                                | @DisplayName의 생성 방법을 지정                                               |
| @TestClassOrder                                                                                                       | 중첩된 @Nested Test Class에 대한 실행 순서를 구성하는데 사용                  |
| @TestMethodOrder                                                                                                      | Test Class 내부의 Test Method의 순서를 설정 <br/>JUnit4의 @FixMethodOrder와 유사 |
| @TestInstance                                                                                                         | Test Instance 수명 주기를 구성하는데 사용                                     |
| @BeforeEach                                                                                                           | 각각 테스트 메소드가 실행되기전에 실행되어야 하는 메소드를 명시해준다. <br/>@Test , @RepeatedTest , @ParameterizedTest , @TestFactory 가 붙은 테스트 메소드가 실행하기 전에 실행된다. <br/>JUnit4 → @Before |
| @AfterEach														| @Test , @RepeatedTest , @ParameterizedTest , @TestFactory 가 붙은 테스트 메소드가 실행되고 난 후 실행된다. JUnit4 → @After |
| @BeforeAll														| 테스트가 시작하기 전 한 번만 실행<br/>JUnit4 → @BeforeClass |
| @AfterAll														| 테스트가 완전히 끝난 후 한 번만 실행<br/>JUnit4 → @AfterAll |
| @Nested														| 중첩 테스트 클래스, 즉 Inner Class여야만 한다.<br/>테스트 인스턴스 라이플사이클이 per-class 로 설정되어 있지 않다면 @BeforeAll , @AfterAll 가 동작안하니 주의하자. |
| @Tag															| 테스트를 필터링할 때 사용, 클래스 또는 메소드레벨에 사용<br/>JUnit4 → @Category(Class) |
| @Disabled														| 테스트 클래스나 메소드의 테스트를 비활성화 한다.<br/>JUnit4 → @Ignore |
| @Timeout														| 지정된 시간을 초과하는 경우 Test, TestFactory, TestTemplate, Lifecycle Method가 실패한다. 해당 Annotation은 상속 된다. |
| @ExtendWith														| extension을 등록, 이 어노테이션은 상속 된다.<br/>JUnit4 → @RunWith(Runner), TestRule, MethodRule |
| @RegisterExtension														| 필드를 통해 extension을 등록한다. 이런 필드는 private이 아니라면 상속 |
| @TempDir														| 필드 주입이나 파라미터 주입을 통해 임시적인 디렉토리를 제공할 때 사용 |

### @Test, @DisplayName, @Disabled

@BeforeAll, @AfterAll, @BeforeEach, @AfterEach

```java
class TestStudy {
	@Test
    @DisplayName("테스트")
    void test() {
        System.out.println("test");
    }

    @Test
    void test2() {
        System.out.println("test2");
    }

    @Test
    @Disabled
    void disabled() {
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
        System.out.println("after all");
    }

    // 모든 테스트가 실행되기 전에 한번씩 실행
    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
        System.out.println();
    }

    @AfterEach
    void afterEach() {
        System.out.println();
        System.out.println("afterEach");
        System.out.println();
    }
}
```

`Result`

![junit](https://github.com/crongcm/junit/assets/113030711/de90ab5a-70d4-4984-8c15-e8e2094693b0)

### @TestInstance

| Lifecycle                         | Description          |
| --------------------------------- | -------------------- |
| TestInstance.Lifecycle.PER_CLASS  | 클래스 단위 생명주기 |
| TestInstance.Lifecycle.PER_METHOD | 메소드 단위 생명주기 |

```java
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
}
```

### @ParameterizedTest

| Sources Annotaion   | Description                                                         |
| ------------------- | ------------------------------------------------------------------- |
| @ValueSource        | short, byte, int, long, float, double, char, boolean, String, Class |
| @NullSource         | 매개변수에 빈 값을 주입                                             |
| @EmptySource        | Object, Array, Collections의 빈 값을 주입                           |
| @NullAndEmptySource | @NullSource와 @EmptySource를 결합                                   |
| @EnumSource         | Enum을 주입                                                         |

```java
@ParameterizedTest
@ValueSource(strings = {"", " "})
void valueSourceTest(String input) {
    assertTrue(StringUtils.isBlank(input));
}

@ParameterizedTest
@EnumSource(ChronoUnit.class)
void enumSourceTest(ChronoUnit unit) {
    assertNotNull(unit);
}

@ParameterizedTest
@ValueSource(strings = {"cm", "sm"})
void stringSourceTest(@ConvertWith(PersonConverter.class) Person person) {
    System.out.println(person.getName());
}

static class PersonConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        assertEquals(Person.class, targetType, "Can only convert to Person");
        return new Person(source.toString());
    }
}

@ParameterizedTest
@CsvSource({"cm, 30", "sm, 20"})
void cvsSourceTest(@AggregateWith(PersonAggregator.class) Person person) {
    System.out.println("person = " + person.toString());
}

static class PersonAggregator implements ArgumentsAggregator {
    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
        return new Person(accessor.getString(0), accessor.getInteger(1));
    }
}

@ParameterizedTest
@ValueSource(strings = {"apple", "macbook", "iphone"})
void typedConverterTest(@ConvertWith(TypedPersonConverter.class) Integer length) {
    System.out.println("length = " + length);
}

// 타입 변경을 위한 Converter
static class TypedPersonConverter extends TypedArgumentConverter<String, Integer> {
    protected TypedPersonConverter() {
        super(String.class, Integer.class);
    }

    @Override
    protected Integer convert(String source) throws ArgumentConversionException {
        return (source != null ? source.length() : 0);
    }
}

@ParameterizedTest
@ValueSource(strings = {"01.01.2017", "31.12.2017"})
void testWithExplicitJavaTimeConverter(
        @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {
    assertEquals(2017, argument.getYear());
}
```

### @Nested

### @DisplayNameGeneration

| Class               | Description                                                          |
| ------------------- | -------------------------------------------------------------------- |
| Standard            | 기존 클래스, 메소드 명을 사용                                        |
| Simple              | 괄호를 제외                                                          |
| ReplaceUnderscores  | \_(underscore)를 공백으로 치환                                       |
| IndicativeSentences | @IndicativeSentencesGeneration Annotation을 이용하여 커스텀하게 변경 |

```java
@Nested
@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
class StandardTestClass {
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
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ReplaceUnderscoresTestClass {
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
@IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
class IndicativeSentencesGenerationTestClass {
    @Test
    void test_display_name_generation() {}
}
```

`Result`

![junit-1](https://github.com/crongcm/junit/assets/113030711/fa7d15be-3cd0-48e8-b7e3-7af0451496a9)

### **@RepeatTest**

- @RepeatTest Annotation name value에 테스트 결과 custom 가능
  {displayName}, {currentRepetition}, {totalRepetitions}
- RepetitionInfo 클래스로 메소드 내에서 반복 테스트 인자 사용 가능

```java
@RepeatedTest(5)
void repeatedTest() {
	...
}

@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
@DisplayName("반복 테스트")
void repeatTest(RepetitionInfo repetitionInfo) throws Exception {
    System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
}
```

### @TestTemplate

```java
final List<String> fruits = Arrays.asList("apple", "banana", "lemon");

@TestTemplate
@ExtendWith(MyTestTemplateInvocationContextProvider.class)
void testTemplate(String fruit) {
    assertTrue(fruits.contains(fruit));
}

public class MyTestTemplateInvocationContextProvider
        implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
            ExtensionContext context) {

        return Stream.of(invocationContext("apple"), invocationContext("banana"));
    }

    private TestTemplateInvocationContext invocationContext(String parameter) {
        return new TestTemplateInvocationContext() {
            @Override
            public String getDisplayName(int invocationIndex) {
                return parameter;
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return Collections.singletonList(new ParameterResolver() {
                    @Override
                    public boolean supportsParameter(ParameterContext parameterContext,
                            ExtensionContext extensionContext) {
                        return parameterContext.getParameter().getType().equals(String.class);
                    }

                    @Override
                    public Object resolveParameter(ParameterContext parameterContext,
                            ExtensionContext extensionContext) {
                        return parameter;
                    }
                });
            }
        };
    }
}
```

`Result`

```java
└─ testTemplate(String) ✔
   ├─ apple ✔
   └─ banana ✔
```

### @TestClassOrder

Default → @TestMethodOrder(OrderAnnotation.class)

| ClassOrderer Class | Description                            |
| ------------------ | -------------------------------------- |
| ClassName          | Class Name을 영숫자 기준으로 정렬      |
| DisplayName        | @DisplayName을 영숫자 기준으로 정렬    |
| OrderAnnotation    | @Order Annotation 설정을 기준으로 정렬 |
| Random             | -                                      |

Example

```java
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class OrderedTest {
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
```

![junit-2](https://github.com/crongcm/junit/assets/113030711/f30ee4de-8fc0-4d3f-936f-36e1804738dd)


### @TestMethodOrder

| MethodOrderer Class | Description                                             |
| ------------------- | ------------------------------------------------------- |
| DisplayName         | -                                                       |
| MethodName          | -                                                       |
| OrderAnnotation     | -                                                       |
| Random              | -                                                       |
| Alphanumeric        | Deprecated → MethodName 사용, JUnit 6.0에서 제거될 예정 |

```java
@TestMethodOrder(OrderAnnotation.class)
class OrderedTest {
    @Test
    @Order(1)
    void firstMethod() {}

    @Test
    @Order(2)
    void secondMethod() {}

    @Test
    @Order(3)
    void thirdMethod() {}
}
```

![junit-3](https://github.com/crongcm/junit/assets/113030711/2f9ea6b0-31b8-4efe-82d4-3645b656fc70)

### @Tag

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
@Test
public @interface FastTest {}
```

```java
@FastTest
void fastRunTest() {
    ...
}
```

### @Timeout

| Parameter value | Equivalent annotaion                      |
| --------------- | ----------------------------------------- |
| 42              | @Timeout(42)                              |
| 42 ns           | @Timeout(value = 42, unit = NANOSECONDS)  |
| 42 μs           | @Timeout(value = 42, unit = MICROSECONDS) |
| 42 ms           | @Timeout(value = 42, unit = MILLISECONDS) |
| 42 s            | @Timeout(value = 42, unit = SECONDS)      |
| 42 m            | @Timeout(value = 42, unit = MINUTES)      |
| 42 h            | @Timeout(value = 42, unit = HOURS)        |
| 42 d            | @Timeout(value = 42, unit = DAYS)         |

```java
class TimeoutTest {
    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void timeoutTest() {
        // 0.1초를 초과하는 테스트는 실패
    }
}
```

### @ExtendWith

```java
@ExtendWith(RandomParametersExtension.class)
@Test
void test(@Random int i) {
  // ...
}

@ExtendWith(RandomParametersExtension.class)
class MyTests() {}

@ExtendWith({DatabaseExtension.class,WebServerExtension.class})
class MyFirstTests() {}

@ExtendWith(DatabaseExtension.class)
@ExtendWith(WebServerExtension.class)
class MySecondTests() {}
```

### @RegisterExtension

- static field로 등록된 extension

```java
class WebServerDemo {
    @RegisterExtension
    static WebServerExtension server = WebServerExtension.builder()
        .enableSecurity(false)
        .build();

    @Test
    void getProductList() {
        WebClient webClient = new WebClient();
        String serverUrl = server.getServerUrl();
        // Use WebClient to connect to web server using serverUrl and verify response
        assertEquals(200, webClient.get(serverUrl + "/products").getResponseStatus());
    }
}
```

- instance field로 등록된 extention

```java
class DocumentationDemo {
    static Path lookUpDocsDir() {
        // return path to docs dir
    }

    @RegisterExtension
    DocumentationExtension docs = DocumentationExtension.forPath(lookUpDocsDir());

    @Test
    void generateDocumentation() {
        // use this.docs ...
    }
}
```

## Assertions

| Assertions								| Description 							|
| --------------------------------------------------------------------- | ------------------------------------------------------------- |
| assertEquals(expected, actual)<br/>assertNotEquals(expected, actual) 	| 예상값과 실제값이 같은지 비교 					|
| assertArrayEquals(expected, actual) 					| 두 배열이 같은지 비교<br/>(두 배열이 모두 null일 경우 동일한 것으로 간주) 	|
| assertNull(actual)<br/>assertNotNull(actual) 				| Null인지 확인 							|
| assertSame(expected, actual)<br/>assertNotSame(expected, actual) 	| 두 객체가 같은지 비교<br/>(두 객체가 모두 null일 경우 동일한 것으로 간주) 	|
| assertTrue(expected, actual)<br/>assertFalse(expected, actual) 	| 특정 조건이 true, false인지 확인 					|
| asertAll(?heading, executables) 					| excutables에 포함된 assert문들을 비교<br/>실행된 전체 테스트의 성공, 실패 결과를 확인 할 수 있다. |
| assertIterableEquals(expected, actual) 				| 순서와 return value가 일치하는 iterble 요소를 비교<br/>동일한 타입이 아니어도 순서와 값만 일치하면 된다. |
| assertLinesMatch(expected, actual) 					| 1. 예상값과 실제값이 일치하는지 비교<br/>2. 예상값(정규표헌식)과 실제값이 String.matches()일치 하는지 확인<br/>3. check if the expected line is a fast-forward marker. If yes apply fast-forward and repeat the algorithm from the step 1 |
| assertThrows(expectedType, exception) 				| 예상 exception Type과 발생한 예외를 비교 |
| assertTimeout(expected, actual) 					| 예상 시간동안 테스트가 성공하는지 여부<br/>해당 테스트가 종료될때까지 성공여부를 알 수 없다. |
| assertTimeoutPreemptively(expected, actual) 				| assertTimeout과 동일한 비교<br/>예상시간을 초과하면 테스트를 바로 실패한다. |
| fail(message) 							| 테스트를 실패하게 만든다. |

# Mockito

Mockito : Mock 객체를 쉽게 만들고 관리하고 검증할 수 있는 방법을 제공한다.

Mock : 진짜 객체와 비슷하게 동작하지만 프로그래머가 직접 그 객체의 행동을 관리하는 객체

- Mock을 만드는 방법
- Mock이 어떻게 동작해야 하는지 관리하는 방법
- Mock의 행동을 검증하는 방법

### Mock 객체 만들기

Mockito.mock() Method로 만드는 방법

```java
MemberService memberService = mock(MemberService.class);
StudyService studyService = mock(StudyRepository.class);
```

@Mock Annotation으로 만드는 방법

- JUnit5 Extention으로 MockitoExtention을 사용해야 한다.
- 필드
- 메소드 매개변수

```java
@ExtendWith(MockitoExtention.class)
class MockitoTest {
	@Mock MemberService memberService;
	@Mock StudyRepository studyRepository;
}
```

```java
@ExtendWith(MockitoExtention.class)
class MockitoTest {
	@Test
	void mockCreateTest(@Mock MemberService memberService,
										@Mock StudyRepository studyRepository) {
			....
	}
}
```

### Mock 객체 Stubbing

- Null을 Return (Optional 타입은 Optional.empty Return)
- Primitive 타입은 기본 Primitive 값
- Collection은 Empty Collection
- void method는 예외를 던지지 않고 아무런 일도 발생하지 않는다.

```java
Study study = new Study(10, "테스트");

when(memberService.findById(1L)).thenReturn(Optional.of(member));
when(studyRepository.save(study)).thenReturn(study);

studyService.createNewStudy(1L, study);

studyService.createNewStudy(1L, study);
assertEquals(member, study.getOwner());
```

### Mock 객체 확인

Mockito.verify() 객체 검증

InOrder.verify() 순서 검증

### Mockito BDD 스타일 API

- Behavior Driven Development style of writing tests
- uses **//given //when //then** comments as fundamental parts of your test methods

| Method               | Description                  |
| -------------------- | ---------------------------- |
| given(Method call)   | Mockito → when(Object)       |
| then(Mock object)    | Mock 객체의 행위를 검증      |
| will(Answer)         | Mockito → doAnswer(Answer)   |
| willAnswer(Answer)   | Mockito → doAnswer(Answer)   |
| willCallRealMethod() | Mockito → doCallRealMethod() |
| willDoNothing()      | Mockito → doNothing()        |
| willReturn(Object)   | Mockito → doReturn(Object)   |
| willThrow(Class)     | Mockito → doThrow(Class)     |

```java
When -> Given
when(memberService.findById(1L)).thenReturn(Optional.of(member));
when(studyRepository.save(study)).thenReturn(study);

given(memberService.findById(1L)).willReturn(Optional.of(member));
given(studyRepository.save(study)).willReturn(study);

Verify -> Then
verify(memberService, times(1)).notify(study));
verifyNoMoreInteractions(memberService);

then(memberService).should(times(1)).notify(study);
then(memberService).shouldHaveNoMoreInteractions();
```

```java
given(studyRepository.save(study)).willReturn(study);

assertEquals(StudyStatus.OPENED, study.getStatus());
assertNotNull(study.getOpenedDateTime());
then(memberService).should().notify(study);
```

Interface Answer

Mock 객체의 답변을 생성한다.

```java
when(mock.someMethod(anyString())).thenAnswer(
   new Answer() {
       public Object answer(InvocationOnMock invocation) {
           Object[] args = invocation.getArguments();
           Object mock = invocation.getMock();
           return "called with arguments: " + Arrays.toString(args);
       }
		}
);

 //Following prints "called with arguments: [foo]"
 System.out.println(mock.someMethod("foo"));
```

# JMeter

성능 측정 및 부하 테스트 기능을 제공하는 오픈 소스 자바 애플리케이션

### 다양한 형태의 애플리케이션 테스트 지원

- Web - HTTP, HTTPS (Java, NodeJS, PHP, ASP.NET, …)
- SOAP / REST Webservices
- FTP
- Database via JDBC
- LDAP
- Message-oriented middleware (MOM) via JMS
- Mail - SMTP(S), POP3(S) and IMAP(S)
- Native commands or shell scripts
- TCP
- Java Objects

### CLI Mode 지원

| CLI Option | Description |
| --- | --- |
| -n | JMeter가 CLI 모드에서 실행되도록 지정 |
| -t | 테스트 계획이 포함된 JMX 파일의 이름 |
| -l | 샘플 결과를 기록할 JTL 파일의 이름 |
| -j | JMeter 실행 로그 파일 이름 |
| -r | Jmeter 속성 “remote_hosts”로 지정된 서버에서 테스트 실행 |
| -R | [원격 서버 목록] 지정된 원격 서버에서 테스트 실행 |
| -g | [CSV 파일 경로] 보고서 대시보드만 생성 |
| -e | 부하 테스트 후 보고서 대시보드 생성 |
| -o | 부하테스트 후 보고서 대시보드를 생성할 출력 폴더.
폴더는 존재하지 않거나 비어있어야 한다. |
| -H | [프록시 서버 호스트 이름 또는 IP 주소] |
| -p | [프록시 서버 포트] |

```bash
jmeter -n -t my_test.jmx -l log.jtl -H my.proxy.server -P 8000
```

### 주요 개념

- Thread Group : 한 쓰레드 당 유저 한명
- Sampler : 어떤 유저가 해야 하는 액션
- Listener : 응답을 받았을때 할 일 (리포팅, 검증, 그래프 그리기 등)
- Configuration : Sampler 또는 Listener가 사용할 설정 값 (쿠키, JDBC 커넥션 등)
- Assertion : 응답이 성공적인지 확인하는 방법 (응답 코드, 본문 내용 등)

### Thread Group Config

![jmeter](https://github.com/crongcm/test/assets/113030711/622f98b1-f014-4f88-a94d-ae3c680560f6)

**Action to be taken after a Sampler error**

Sampler에서 에러 발생시 액션 설정 (Assertion으로 검증)

**Number of Threads (users)**

생성할 쓰레드의 수 (가상 유저 수)

**Ramp-up period (seconds)**

한번의 실행을 몇 초 동안 완료 시킬 것인지 설정

**Loop Count**

반복하고자 하는 횟수, Infinite (무제한)

### Sampler Config (HTTP Request)

![jmeter-1](https://github.com/crongcm/test/assets/113030711/963e4df1-e088-4bc4-b7d5-84f9bf51d019)

### Listener Config

![jmeter-2](https://github.com/crongcm/test/assets/113030711/ffe2a01a-1301-42ca-bc5a-ccd5a3c4603d)

### Configuration

![jmeter-3](https://github.com/crongcm/test/assets/113030711/721897c3-5c09-4971-80e4-772b24a2803d)

### Assertion Config

Response Assertion

![jmeter-4](https://github.com/crongcm/test/assets/113030711/78a0e85b-f836-48c7-b61e-d31c314dec11)

JSON Assertion

![jmeter-5](https://github.com/crongcm/test/assets/113030711/9f2a41bd-35c6-4c03-9708-1b0c9360e05c)

# Reference

[더 자바, 애플리케이션을 테스트하는 다양한 방법](https://www.inflearn.com/course/the-java-application-test)
