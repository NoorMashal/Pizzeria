/**
 * @author Noor Mashal
 */
package pizzeria;

import org.junit.jupiter.api.Test;
import pizzeria.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuildYourOwnTest {

    @Test
    public void testSmallPizza() {
        PizzaFactory pizzaFactory = new NYPizza(); /* Testing with NY Style Pizza. */
        Pizza pizza = pizzaFactory.createBuildYourOwn();
        pizza.setSize(Size.SMALL);
        double expectedPrice = 8.99; /* Base Price. */
        assertEquals(pizza.price(), expectedPrice, "Price should be " + expectedPrice); /* Testing price of base Small Pizza. */
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.BBQ_CHICKEN);
        expectedPrice += (2 * 1.69); /* Base price + 2 toppings. */
        assertEquals(pizza.price(), expectedPrice, "Price should be " + expectedPrice); /* Testing price of base Small Pizza + toppings. */
    }

    @Test
    public void testMediumPizza() {
        PizzaFactory pizzaFactory = new ChicagoPizza(); /* Testing with Chicago Style Pizza. */
        Pizza pizza = pizzaFactory.createBuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        double expectedPrice = 10.99;
        assertEquals(pizza.price(), expectedPrice, "Price should be " + expectedPrice); /* Testing price of base Medium Pizza. */
        pizza.addTopping(Topping.PEPPERONI);
        expectedPrice += 1.69; /* base price + 1 topping price. */
        assertEquals(pizza.price(), expectedPrice, "Price should be " + expectedPrice); /* Testing price of base Medium Pizza + topping. */
    }

    @Test
    public void testLargePizza() {
        PizzaFactory pizzaFactory = new NYPizza(); /* Testing with NY Style Pizza. */
        Pizza pizza = pizzaFactory.createBuildYourOwn();
        pizza.setSize(Size.LARGE);
        double expectedPrice = 12.99;
        assertEquals(pizza.price(), expectedPrice, "Price should be " + expectedPrice); /* Testing price of base Large Pizza. */
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.ONION);
        pizza.addTopping(Topping.GREEN_PEPPER);
        expectedPrice += (3 * 1.69); /* base price + 3 toppings. */
        assertEquals(pizza.price(), expectedPrice, "Price should be " + expectedPrice); /* Testing price of base Large Pizza + toppings. */
    }

    @Test
    public void testPizzaPrices() {
        /* Test Deluxe Pizza */
        PizzaFactory chicagoFactory = new ChicagoPizza();
        Pizza deluxePizza = chicagoFactory.createDeluxe();
        deluxePizza.setSize(Size.SMALL);
        assertEquals(16.99, deluxePizza.price(), "Deluxe Small price should be $16.99");

        deluxePizza.setSize(Size.MEDIUM);
        assertEquals(18.99, deluxePizza.price(), "Deluxe Medium price should be $18.99");

        deluxePizza.setSize(Size.LARGE);
        assertEquals(20.99, deluxePizza.price(), "Deluxe Large price should be $20.99");

        /* Test BBQ Chicken Pizza */
        PizzaFactory nyFactory = new NYPizza();
        Pizza bbqChickenPizza = nyFactory.createBBQChicken();
        bbqChickenPizza.setSize(Size.SMALL);
        assertEquals(14.99, bbqChickenPizza.price(), "BBQ Chicken Small price should be $14.99");

        bbqChickenPizza.setSize(Size.MEDIUM);
        assertEquals(16.99, bbqChickenPizza.price(), "BBQ Chicken Medium price should be $16.99");

        bbqChickenPizza.setSize(Size.LARGE);
        assertEquals(19.99, bbqChickenPizza.price(), "BBQ Chicken Large price should be $19.99");

        /* Test Meatzza Pizza */
        Pizza meatzzaPizza = chicagoFactory.createMeatzza();
        meatzzaPizza.setSize(Size.SMALL);
        assertEquals(17.99, meatzzaPizza.price(), "Meatzza Small price should be $17.99");

        meatzzaPizza.setSize(Size.MEDIUM);
        assertEquals(19.99, meatzzaPizza.price(), "Meatzza Medium price should be $19.99");

        meatzzaPizza.setSize(Size.LARGE);
        assertEquals(21.99, meatzzaPizza.price(), "Meatzza Large price should be $21.99");
    }
}


