/**
 * @author Noor Mashal, Neha Murthy
 */
package pizzeria.model;

/**
 * BBQChicken class represents specific type of Pizza
 * Comes with preset toppings and prices based on size.
 */
public class BBQChicken extends Pizza {
    private static final double SMALL_PRICE = 14.99;
    private static final double MEDIUM_PRICE = 16.99;
    private static final double LARGE_PRICE = 19.99;

    /**
     * Constructor for the BBQChicken class
     * Initializes BBQChicken with specified crust and adds the toppings
     * @param crust The crust type for BBQChicken pizza
     */
    public BBQChicken(Crust crust) {
        super(crust);
        getToppings().add(Topping.BBQ_CHICKEN);
        getToppings().add(Topping.GREEN_PEPPER);
        getToppings().add(Topping.PROVOLONE);
        getToppings().add(Topping.CHEDDAR);
    }

    /**
     * Calculates price of the BBQChicken Pizza based on the size of the pizza
     * @return The price of the pizza as a double based on the size
     */
    @Override
    public double price() {
        return switch (getSize()) {
            case SMALL -> SMALL_PRICE;
            case MEDIUM -> MEDIUM_PRICE;
            case LARGE -> LARGE_PRICE;
        };
    }
}