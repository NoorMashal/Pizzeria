/**
 * @author Noor Mashal, Neha Murthy
 */
package pizzeria.model;

/**
 * Pizzeria Interface provides blueprint for creating different types of pizzas
 */
public interface PizzaFactory {
    Pizza createDeluxe();
    Pizza createBBQChicken();
    Pizza createMeatzza();
    Pizza createBuildYourOwn();
}