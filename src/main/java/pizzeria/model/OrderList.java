/**
 * @author Neha Murthy, Noor Mashal
 */
package pizzeria.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class manages the Pizza orders made by the user
 */
public class OrderList {
    private static Map<Integer, List<Pizza>> orderMap = new HashMap<>();

    // Add a new order with its order number
    public static void addOrder(int orderNumber, List<Pizza> pizzas) {
        orderMap.put(orderNumber, new ArrayList<>(pizzas)); // Create a copy of the pizza list
    }

    // Get an order by its number
    public static List<Pizza> getOrder(int orderNumber) {
        return orderMap.get(orderNumber);
    }

    // Remove an order by its number
    public static void removeOrder(int orderNumber) {
        orderMap.remove(orderNumber);
    }

    // Get all order numbers
    public static List<Integer> getOrderNumbers() {
        return new ArrayList<>(orderMap.keySet());
    }

    // Get all orders
    public static Map<Integer, List<Pizza>> getAllOrders() {
        return orderMap;
    }

    // Calculate subtotal for a specific order
    public static double getSubtotal(int orderNumber) {
        List<Pizza> pizzas = orderMap.get(orderNumber);
        if (pizzas == null) return 0.0;
        return pizzas.stream().mapToDouble(Pizza::price).sum();
    }

    // Calculate sales tax for a specific order
    public static double getSalesTax(int orderNumber) {
        return getSubtotal(orderNumber) * 0.08; // 8% sales tax
    }

    // Calculate total for a specific order
    public static double getTotal(int orderNumber) {
        return getSubtotal(orderNumber) + getSalesTax(orderNumber);
    }

    // Clear all orders
    public static void clearAllOrders() {
        orderMap.clear();
    }

    // Check if an order number exists
    public static boolean hasOrder(int orderNumber) {
        return orderMap.containsKey(orderNumber);
    }

    // Get number of orders
    public static int getOrderCount() {
        return orderMap.size();
    }

    // Get order details as a string
    public static String getOrderDetails(int orderNumber) {
        List<Pizza> pizzas = orderMap.get(orderNumber);
        if (pizzas == null) return "Order not found";

        StringBuilder details = new StringBuilder();
        details.append("Order Number: ").append(orderNumber).append("\n");
        details.append("Pizzas:\n");
        for (Pizza pizza : pizzas) {
            details.append("- ").append(pizza.toString()).append("\n");
        }
        details.append("Subtotal: $").append(String.format("%.2f", getSubtotal(orderNumber))).append("\n");
        details.append("Sales Tax: $").append(String.format("%.2f", getSalesTax(orderNumber))).append("\n");
        details.append("Total: $").append(String.format("%.2f", getTotal(orderNumber)));

        return details.toString();
    }
    public static List<Pizza> getOrderPizzas(int orderNumber) {
        return orderMap.get(orderNumber);
    }
}