/**
 * @author Noor Mashal
 */
package pizzeria.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pizzeria.model.OrderList;
import pizzeria.model.Pizza;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryController {

    @FXML
    private ComboBox<Integer> orderNumberComboBox;

    @FXML
    private ListView<Pizza> ordersListView;

    @FXML
    private Button exportButton;

    @FXML
    private Label totalLabel;


    @FXML
    private void initialize() {
        updateOrderDisplay();

        // Listener to update ListView based on selected order number
        orderNumberComboBox.valueProperty().addListener((obs, oldOrder, newOrder) -> {
            if (newOrder != null) {
                displayOrderDetails(newOrder);
            }
        });
    }

    private void updateOrderDisplay() {
        // Populate ComboBox with available order numbers
        ArrayList<Integer> orderNumbers = new ArrayList<>(OrderList.getOrderNumbers());
        orderNumberComboBox.setItems(FXCollections.observableArrayList(orderNumbers));

        // Display the first order in the ListView if available
        if (!orderNumbers.isEmpty()) {
            orderNumberComboBox.setValue(orderNumbers.get(0)); // Set the default selection
            displayOrderDetails(orderNumbers.get(0)); // Display the first order's details
        }
    }

    private void displayOrderDetails(int orderNumber) {
        // Retrieve and display the details of the selected order
        ObservableList<Pizza> orderDetails = FXCollections.observableArrayList(OrderList.getOrder(orderNumber));
        ordersListView.setItems(orderDetails);
        totalLabel.setText(String.format("$%.2f", OrderList.getTotal(orderNumber)));
    }

    @FXML
    private void handleCancelOrder() {
        Integer selectedOrderNumber = orderNumberComboBox.getValue();
        if (selectedOrderNumber != null) {
            OrderList.removeOrder(selectedOrderNumber);
            this.ordersListView.setItems(null);
            this.totalLabel.setText("");
            updateOrderDisplay();
            showAlert("Order #" + selectedOrderNumber + " has been cancelled.");
        } else {
            showAlert("Please select an order to cancel.");
        }
    }

    /**
     * Exports all completed orders to a specified file path. (user chooses)
     * Each order is written as a new line in the file.
     */
    @FXML
    private void handleExportOrders() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Order History");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());

        if (file != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                // Write header
                writer.println("PIZZA STORE ORDERS");
                writer.println("========================");
                writer.println();

                // Write each order
                for (Integer orderNum : OrderList.getOrderNumbers()) {
                    writer.println("Order #" + orderNum + ":");

                    // Get order details and process them
                    List<Pizza> orderDetails = OrderList.getOrderPizzas(orderNum);

                    // Process and write each pizza on its own line
                    for (Pizza detail : orderDetails) {
                        // Skip empty lines or separator lines
                        writer.println(detail);
                    }
                    writer.println(String.format("Subtotal: $%.2f", OrderList.getSubtotal(orderNum)));
                    writer.println(String.format("Tax: $%.2f", OrderList.getSalesTax(orderNum)));
                    writer.println(String.format("Total: $%.2f", OrderList.getTotal(orderNum)));
                    writer.println("------------------------");
                    writer.println();
                }

                showAlert("Orders successfully exported to " + file.getName());
            } catch (IOException e) {
                showAlert("Error exporting orders: " + e.getMessage());
            }
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