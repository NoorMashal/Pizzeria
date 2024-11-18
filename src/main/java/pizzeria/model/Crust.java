/**
 * @author Noor Mashal, Neha Murthy
 */
package pizzeria.model;

/**
 * Crust enum represents the various crust types available for pizzas
 */
public enum Crust {
    /**Deep-Dish crust*/
    DEEP_DISH,
    /**Stuffed crust*/
    STUFFED,
    /**Pan crust*/
    PAN,
    /**Thin crust*/
    THIN,
    /**Hand Tossed crust*/
    HAND_TOSSED,
    /**Brooklyn crust*/
    BROOKLYN;

    public String getPizzaFactory() {
        return switch (this) {
            case DEEP_DISH, STUFFED, PAN -> "Chicago"; // Chicago style factory
            case THIN, HAND_TOSSED, BROOKLYN -> "New York"; // New York style factory
            default -> throw new IllegalArgumentException("Unknown crust type: " + this);
        };
    }
}
