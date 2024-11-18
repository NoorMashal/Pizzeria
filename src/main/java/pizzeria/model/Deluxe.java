/**
 * @author Noor Mashal, Neha Murthy
 */
package pizzeria.model;

/**
 * Deluxe class represents the Deluxe Pizza
 * Comes with list of toppings and price based on the size
 */
public class Deluxe extends Pizza {
    private static final double SMALL_PRICE = 16.99;
    private static final double MEDIUM_PRICE = 18.99;
    private static final double LARGE_PRICE = 20.99;

    /**
     * Constructor for the Deluxe class. Initializing specified crusts and toppings
     * @param crust crust type for the Deluxe pizza
     */
    public Deluxe(Crust crust) {
        super(crust);
        // Set default toppings for pizzeria.Deluxe
        getToppings().add(Topping.SAUSAGE);
        getToppings().add(Topping.PEPPERONI);
        getToppings().add(Topping.GREEN_PEPPER);
        getToppings().add(Topping.ONION);
        getToppings().add(Topping.MUSHROOM);
    }

    /**
     * Calculates the price of the pizza based on size
     * @return the price of the pizza as a double, varies by size
     */
    @Override
    public double price() {
        switch (getSize()) {
            case SMALL: return SMALL_PRICE;
            case MEDIUM: return MEDIUM_PRICE;
            case LARGE: return LARGE_PRICE;
            default: return 0;
        }
    }
}