/**
 * @author Noor Mashal, Neha Murthy
 */
package pizzeria.model;

/**
 * The BuildYourOwn class represents the customization of the pizza
 * Customer can select toppings and price is determined based on size
 */
public class BuildYourOwn extends Pizza {
    private static final double SMALL_PRICE = 8.99;
    private static final double MEDIUM_PRICE = 10.99;
    private static final double LARGE_PRICE = 12.99;
    private static final double TOPPING_PRICE = 1.69;

    /**
     * Constructor for the BuildYourOwn class.
     * Initializes the BuildYourOwn pizza with crust
     * @param crust the crust type for the BuildYourOwn pizza
     */
    public BuildYourOwn(Crust crust) {
        super(crust);
    }

    /**
     * Calculates the price of BuildYourOwn pizza based on size and number of toppings
     * @return the total price of the pizza including the base price and additional charge per topping
     */
    @Override
    public double price() {
        double basePrice = switch (getSize()) {
            case SMALL -> SMALL_PRICE;
            case MEDIUM -> MEDIUM_PRICE;
            case LARGE -> LARGE_PRICE;
            default -> 0;
        };
        return basePrice + (TOPPING_PRICE * getToppings().size());
    }

    /**
     * Adds topping to the BuildYourOwn pizza
     * @param topping
     */
    public void addTopping(Topping topping) {
        getToppings().add(topping);
    }
}