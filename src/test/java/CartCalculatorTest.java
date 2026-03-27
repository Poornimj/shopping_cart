package shop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartCalculatorTest {

    private final CartCalculator calculator = new CartCalculator();

    @Test
    void testCalculateItemTotal() {
        assertEquals(30.0, calculator.calculateItemTotal(10.0, 3), 0.001);
    }

    @Test
    void testCalculateItemTotalWithZeroQuantity() {
        assertEquals(0.0, calculator.calculateItemTotal(10.0, 0), 0.001);
    }

    @Test
    void testCalculateItemTotalWithZeroPrice() {
        assertEquals(0.0, calculator.calculateItemTotal(0.0, 5), 0.001);
    }

    @Test
    void testCalculateItemTotalWithDecimalPrice() {
        assertEquals(25.0, calculator.calculateItemTotal(12.5, 2), 0.001);
    }

    @Test
    void testCalculateCartTotal() {
        double total = calculator.calculateCartTotal(
                List.of(10.0, 5.5, 20.0),
                List.of(2, 4, 1)
        );
        assertEquals(62.0, total, 0.001);
    }

    @Test
    void testCalculateCartTotalEmpty() {
        double total = calculator.calculateCartTotal(List.of(), List.of());
        assertEquals(0.0, total, 0.001);
    }

    @Test
    void testSingleItemCart() {
        double total = calculator.calculateCartTotal(List.of(15.0), List.of(2));
        assertEquals(30.0, total, 0.001);
    }
    @Test
    void testLargeCart() {
        double total = calculator.calculateCartTotal(
                List.of(100.0, 200.0, 300.0),
                List.of(1, 2, 3)
        );
        assertEquals(1400.0, total, 0.001);
    }

    @Test
    void testMixedValues() {
        double total = calculator.calculateCartTotal(
                List.of(0.0, 10.5, 2.5),
                List.of(5, 2, 4)
        );
        assertEquals(0 + 21 + 10, total, 0.001);
    }
}