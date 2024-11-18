/**
 * @author Noor Mashal, Neha Murthy
 */
package pizzeria.model;

/**
 * ChicagoPizza implements the PizzaFactory interface to create different type of pizzas
 */
public class ChicagoPizza implements PizzaFactory {
    /**
     * Creates a Chicago style Deluxe pizza with a deep-ish crust
     * @return a new Deluxe pizza with a Deep_Dish crust
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEP_DISH);
    }

    /**
     * Creates a Chicago-style BBQ chicken pizza with a pan crust
     * @return a new BBQChicken pizza with a PAN crust
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN);
    }

    /**
     * Creates a Chicago-style BBQ chicken pizza with a stuffed crust
     * @return a new Meatzza pizza with stuffed crust
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED);
    }

    /**
     * Creates a customizable Chicago-style BuildYourOwn pizza with a a crust
     * @return a new BuildYourOwn pizza with PAN crust
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN);
    }
}