package guru.qa;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorTests {
    @BeforeAll
    static void beforeAll() {
        System.out.println("This is @BeforeAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("This is @BeforeEach");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("This is @AfterAll");
    }
    
    @CsvSource(value = {
            "1, 2, '+', 3",
            "0.5, 0.5, '-', 0",
            "-0.8, -1, '*', 0.8"
    })
    @ParameterizedTest(name = "Testing of calculator with data {0}, {1}, {2}, {3}")
    void testCalculator(double a, double b, char operation, double expectedResult) {
        double result = calculate(a, b, operation);
        assertThat(result).isEqualTo(expectedResult);
    }

    public static double calculate(double a, double b, char operation) {
        switch (operation) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @CsvSource(value = {
            "1, 2, 2",
            "25, 16, 400"
    })
    @ParameterizedTest(name = "Testing of calculate rectangle area with data {0}, {1}, {2}")
    void testCalculateAreaRectangle(int a, int b, int expectedResult) {
        double result = (a * b);
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b  + " = " + result);
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Arguments> calculatorTestDataProvider() {
        return Stream.of(
                Arguments.of(1, 2, '+', 3),
                Arguments.of(-0.8, -1, '*', 0.8)
        );
    }

    @Disabled
    @Test
    void testDisabled() {
        assertThat(true).isEqualTo(true);
    }

    @DisplayName("A special test case")
    @Test
    void testNamed() {
        assertThat(8).isEqualTo(8);
    }
}