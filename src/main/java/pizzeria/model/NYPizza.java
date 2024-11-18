/**
 * @author Noor Mashal, Neha Murthy
 */
package pizzeria.model;

/**
 * Class implements the interface to create different types of pizza
 */
public class NYPizza implements PizzaFactory {
    /**
     * Creates a New-York style Deluxe pizza with a Brooklyn Crust
     * @return a new pizza with Brooklyn crust
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN);
    }
    /**
     * Creates a New-York style BBQChicken pizza with a thin Crust
     * @return a new pizza with thin crust
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.THIN);
    }
    /**
     * Creates a New-York style Meatzza pizza with a hand-tossed Crust
     * @return a new pizza with hand-tossed crust
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.HAND_TOSSED);
    }
    /**
     * Creates a customizable NY style pizza with a hand-tossed Crust
     * @return a new BuildYourOwn pizza with a hand-tossed crust
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HAND_TOSSED);
    }
}