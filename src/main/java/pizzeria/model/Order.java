/**
 * @author Noor Mashal
 */
package pizzeria.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Order class represents customer's order at the pizzeria
 * Unique order, list of pizza, and calculates the total and tax
 */
public class Order {
    private static List<Pizza> pizzas = null; // List of pizzas in the order
    private static int orderNumber = 1;

    private static void initializeOrder() {
        if (pizzas == null) {
            pizzas = new ArrayList<>();
        }
    }
    /**
     * Gets unique order number
     * @return the unique order number for this order
     */
    public static int getNumber() {
        return orderNumber;
    }

    /**
     * List of pizza
     * @return Arraylist of Pizza objects in the order
     */
    public static List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Adds pizza to the order
     * @param pizza the pizza object to add to the order
     */
    public static void addPizza(Pizza pizza) {
        initializeOrder();
        pizzas.add(pizza);
    }

    /**
     * Removes the specific pizza from the order
     * @param pizza pizza object to remove from the order
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }
    /**
     * Calculates the subtotal for the order by summing the prices
     * @return subtotal cost as a double
     */
    public static double getSubtotal() {
        return pizzas.stream().mapToDouble(Pizza::price).sum();
    }

    /**
     * Calcualtes the sale tax for the order based on the tax rate
     * @return the sales tax amount as a double
     */
    public static double getSalesTax() {
        return getSubtotal() * 0.06625;
    }

    /**
     * Calculates the total cost for the order by summing the saletax and total cost
     * @return the total cost as a double
     */
    public static double getTotal() {
        return getSubtotal() + getSalesTax();
    }

    /**
     * Clears the current order by setting the list of pizzas to null.
     * This effectively removes all pizzas from the order.
     */
    public static void clearOrder() {
        pizzas = null;
    }

    /**
     * Increments the order number by 1.
     * This is used to keep track of sequential orders.
     */
    public static void increaseOrderNumber() {
        orderNumber += 1;
    }

    /**
     * Returns a string representation of the order - order number, list of pizza, and total cost
     * @return string describing the order details
     */
    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder("Order Number: " + orderNumber + "\n");
        orderDetails.append("Pizzas in the order:\n");
        for (Pizza pizza : pizzas) {
            orderDetails.append(pizza.toString()).append("\n");
        }
        orderDetails.append("Subtotal: $").append(String.format("%.2f", getSubtotal())).append("\n");
        orderDetails.append("Sales Tax: $").append(String.format("%.2f", getSalesTax())).append("\n");
        orderDetails.append("Total: $").append(String.format("%.2f", getTotal()));
        return orderDetails.toString();
    }
}
