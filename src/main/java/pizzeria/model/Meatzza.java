/**
 * @author Noor Mashal, Neha Murthy
 */
package pizzeria.model;

/**
 * The Meatzza class represents the tpe of pizza, meat toppings
 * Price depends on size
 */
public class Meatzza extends Pizza {
    private static final double SMALL_PRICE = 17.99;
    private static final double MEDIUM_PRICE = 19.99;
    private static final double LARGE_PRICE = 21.99;

    /**
     * Constructor for Meatzza class, initializes with specified crust and meat toppings
     * @param crust the crust type for Meatzza pizza
     */
    public Meatzza(Crust crust) {
        super(crust);
        getToppings().add(Topping.SAUSAGE);
        getToppings().add(Topping.PEPPERONI);
        getToppings().add(Topping.BEEF);
        getToppings().add(Topping.HAM);
    }

    /**
     * Calculate the price of the Meatzza pizza based on the size
     * @return the price of the pizza as a double based on the size
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