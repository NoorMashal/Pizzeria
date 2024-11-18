/**
 * @author Noor Mashal, Neha Murthy
 */
package pizzeria.model;

import java.util.ArrayList;

/**
 * The pizza abstract class serves as a class for different types of pizza
 * Each pizza has a specific crust type, size and list of toppings
 */
public abstract class Pizza {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Constructor to create a pizza with specific crust type
     * @param crust type of crust for pizza
     */
    public Pizza(Crust crust) {
        this.crust = crust;
        this.toppings = new ArrayList<>();
    }

    /**
     * Gets the list of toppings that are on the pizza
     * @return an ArrayList of toppings
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Gets the crust type of the pizza
     * @return the crust type
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Gets the size of the pizza
     * @return the size of the pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the size of the pizza
     * @param size the size of the pizzas
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Adds a topping to the pizza
     * @param topping the topping that is being added
     */
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    /**
     * Abstract method to calculate the price of the pizza
     * @return the price of the pizza
     */
    public abstract double price();

    /**
     * Returns the string representation of the pizza such as crust, size, topping
     * @return string representation of the pizza
     */
    @Override
    public String toString() {
        return String.format("%s (%s - %s),%s,%s,$%.2f",this.getClass().getSimpleName(), getCrust().getPizzaFactory(), getCrust(), String.join(",", getToppings().stream().map(Topping::name).toArray(String[]::new)), getSize(), price());
    }
}

