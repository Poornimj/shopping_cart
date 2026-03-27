package shop;

import java.util.List;

public class CartCalculator {

    public double calculateItemTotal(double price, int quantity) {
        return price * quantity;
    }

    public double calculateCartTotal(List<Double> prices, List<Integer> quantities) {
        double total = 0.0;

        for (int i = 0; i < prices.size(); i++) {
            total += calculateItemTotal(prices.get(i), quantities.get(i));
        }

        return total;
    }
}