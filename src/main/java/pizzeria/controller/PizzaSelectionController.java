/**
 * @author Noor Mashal
 */
package pizzeria.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pizzeria.model.*;

import java.io.IOException;

public class PizzaSelectionController {

    @FXML
    private ComboBox<String> styleComboBox;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private ComboBox<Size> sizeComboBox;
    @FXML
    private ListView<Topping> availableToppingsList;
    @FXML
    private ListView<Topping> selectedToppingsList;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label crustLabel; /* Label to show the current type of crust based on selections. */
    @FXML
    private Button addToppingButton;
    @FXML
    private Button removeToppingButton;

    private Pizza currentPizza;
    private PizzaFactory pizzaFactory;
    private Order currentOrder; /* Reference to the current order */

    @FXML
    public void initialize() {
        currentOrder = new Order(); /* Initialize the order instance */
        styleComboBox.setItems(FXCollections.observableArrayList("Chicago", "New York"));
        sizeComboBox.setItems(FXCollections.observableArrayList(Size.values()));
        availableToppingsList.setItems(FXCollections.observableArrayList(Topping.values()));

        styleComboBox.setOnAction(event -> loadPizzaFactory());
        typeComboBox.setOnAction(event -> loadPizza());
        sizeComboBox.setOnAction(event -> updateSubtotal());

        /* Initial button states */
        addToppingButton.setDisable(true);
        removeToppingButton.setDisable(true);

        /* Only enable add/remove toppings based on type of Pizza */
        typeComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            if ("Build Your Own".equals(newValue)) {
                addToppingButton.setDisable(false);
                removeToppingButton.setDisable(false);
            } else {
                addToppingButton.setDisable(true);
                removeToppingButton.setDisable(true);
            }
        });
    }

    private void loadPizzaFactory() {
        if ("Chicago".equals(styleComboBox.getValue())) {
            pizzaFactory = new ChicagoPizza();
        } else {
            pizzaFactory = new NYPizza();
        }
        typeComboBox.setItems(FXCollections.observableArrayList("Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"));
    }

    private void loadPizza() {
        String type = typeComboBox.getValue();
        if (pizzaFactory != null && type != null) {
            availableToppingsList.setItems(FXCollections.observableArrayList(Topping.values()));
            switch (type) {
                case "Deluxe" -> currentPizza = pizzaFactory.createDeluxe();
                case "BBQ Chicken" -> currentPizza = pizzaFactory.createBBQChicken();
                case "Meatzza" -> currentPizza = pizzaFactory.createMeatzza();
                case "Build Your Own" -> currentPizza = pizzaFactory.createBuildYourOwn();
            }
            selectedToppingsList.setItems(FXCollections.observableArrayList(currentPizza.getToppings()));
            availableToppingsList.getItems().removeAll(currentPizza.getToppings());

            /* Update crust label */
            crustLabel.setText(currentPizza.getCrust().toString());

            /* Enable or disable topping buttons based on pizza type */
            boolean isBuildYourOwn = "Build Your Own".equals(type);
            addToppingButton.setDisable(!isBuildYourOwn);
            removeToppingButton.setDisable(!isBuildYourOwn);

            updateSubtotal();
        }
    }

    @FXML
    private void addTopping() {
        if (currentPizza != null && selectedToppingsList.getItems().size() < 7) {
            Topping selectedTopping = availableToppingsList.getSelectionModel().getSelectedItem();
            if (selectedTopping != null) {
                currentPizza.addTopping(selectedTopping);
                selectedToppingsList.getItems().add(selectedTopping);
                availableToppingsList.getItems().remove(selectedTopping);
                updateSubtotal();
            }
        }
    }

    @FXML
    private void removeTopping() {
        Topping selectedTopping = selectedToppingsList.getSelectionModel().getSelectedItem();
        if (selectedTopping != null) {
            currentPizza.getToppings().remove(selectedTopping);
            selectedToppingsList.getItems().remove(selectedTopping);
            availableToppingsList.getItems().add(selectedTopping);
            updateSubtotal();
        }
    }

    @FXML
    private void updateSubtotal() {
        if (currentPizza != null && sizeComboBox.getValue() != null) {
            currentPizza.setSize(sizeComboBox.getValue());
            subtotalLabel.setText(String.format("$%.2f", currentPizza.price()));
        }
    }

    @FXML
    private void addToOrder() {
        if (currentPizza != null && styleComboBox.getValue() != null && typeComboBox.getValue() != null && sizeComboBox.getValue() != null) {
            Order.addPizza(currentPizza); /* Add the current pizza to the order */
            System.out.println("Added pizza to order: " + currentPizza);
            updateSubtotal(); /* Update the subtotal if necessary */

            /* Clear current pizza selection after adding */
            selectedToppingsList.setItems(null);
            availableToppingsList.setItems(FXCollections.observableArrayList(Topping.values()));
            styleComboBox.setValue(null);
            typeComboBox.setValue(null);
            sizeComboBox.setValue(null);
            currentPizza = null;
            System.out.println(currentOrder);
        } else {
            /* Show an alert if no pizza is selected */
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all the fields before adding to the order.");
            alert.showAndWait();
        }
    }

    @FXML
    private void goBackToMain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/view/MainView.fxml"));
            Scene mainScene = new Scene(loader.load());

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(mainScene);

            // Set the stage title
            currentStage.setTitle("RU Pizzeria - Main Menu");

            currentStage.show();
        } catch (IOException e) {
            System.err.println("Error loading main view: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
