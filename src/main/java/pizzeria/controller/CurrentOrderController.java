package pizzeria.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pizzeria.model.Order;
import pizzeria.model.OrderList;
import pizzeria.model.Pizza;

import java.io.IOException;

public class CurrentOrderController {

    @FXML
    private Label orderNumberLabel;
    @FXML
    private ListView<Pizza> pizzasListView;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label salesTaxLabel;
    @FXML
    private Label totalLabel;

    // This method will be called to set the current order
    @FXML
    public void initialize() {
        updateOrderDetails(); // Update UI after setting the order
    }

    private void updateOrderDetails() {
        if (Order.getPizzas() != null) {
            // Update the order number
            orderNumberLabel.setText("Order Number: #" + Order.getNumber());

            // Populate the ListView with current pizzas
            pizzasListView.setItems(FXCollections.observableArrayList(Order.getPizzas()));

            // Update totals
            subtotalLabel.setText("Subtotal: $" + String.format("%.2f", Order.getSubtotal()));
            salesTaxLabel.setText("Sales Tax: $" + String.format("%.2f", Order.getSalesTax()));
            totalLabel.setText("Total: $" + String.format("%.2f", Order.getTotal()));
        }
        else {
            pizzasListView.getItems().clear(); // Explicitly clear the ListView

            // Reset all labels to show zero values
            orderNumberLabel.setText("Order Number: #" + Order.getNumber());
            subtotalLabel.setText("Subtotal: $0.00");
            salesTaxLabel.setText("Sales Tax: $0.00");
            totalLabel.setText("Total: $0.00");
        }
    }

    @FXML
    private void clearOrder() {
        Order.clearOrder();
        updateOrderDetails();
    }

    @FXML
    private void removePizza() {
        Pizza selectedPizza = pizzasListView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            // Remove from the Order model
            Order.getPizzas().remove(selectedPizza);

            // Update the ListView and totals
            updateOrderDetails();
        } else {
            showAlert("Please select a pizza to remove.");
        }
    }

    @FXML
    private void placeOrder() {
        if (Order.getPizzas() != null && !Order.getPizzas().isEmpty()) {
            // Add current order to OrderList
            OrderList.addOrder(Order.getNumber(), Order.getPizzas());

            // Print order details to console
            System.out.println("Order placed: " + OrderList.getOrderDetails(Order.getNumber()));

            // Show confirmation to user
            showAlert("Order #" + Order.getNumber() + " has been placed!");

            // Increment order number and clear current order
            Order.increaseOrderNumber();
            clearOrder();
        } else {
            showAlert("Cart is Empty!");
        }
    }

    @FXML
    private void handleBackToMain(ActionEvent event) {
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

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
