package pizzeria.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MainController handles navigation between different views in the RU Pizzeria JavaFX application.
 * It defines event handlers for button interactions and manages scene transitions.
 */
public class MainController {

    /* The button to navigate to the pizza selection screen, injected from the FXML file. */
    @FXML
    private Button pizzaSelectionButton;

    // Logger to log errors and information about the application's operation.
    private static final Logger logger = Logger.getLogger(MainController.class.getName());

    /**
     * Handles the event triggered when the user clicks the Pizza Selection button.
     * Loads and displays the Pizza Selection view.
     */
    @FXML
    public void handlePizzaSelection() {
        loadNewScene("PizzaSelectionView.fxml", "RU Pizzeria - Pizza Selection");
    }

    /**
     * Handles the event triggered when the user clicks the Current Order button.
     * Loads and displays the Current Order view.
     */
    @FXML
    public void handleCurrentOrder() {
        loadNewScene("CurrentOrderView.fxml", "RU Pizzeria - Current Order");
    }

    /**
     * Handles the event triggered when the user clicks the Order History button.
     * Loads and displays the Order History view.
     */
    @FXML
    public void handleOrderHistory() {
        loadNewScene("OrderHistoryView.fxml", "RU Pizzeria - Order History");
    }

    /**
     * Utility method to load and display a new scene based on the provided FXML file.
     *
     * @param fxmlFile the name of the FXML file to load.
     * @param title the title to set for the stage.
     */
    private void loadNewScene(String fxmlFile, String title) {
        try {
            // Load the FXML file and set it as the root of the new scene.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/view/" + fxmlFile));
            Parent root = loader.load();

            // Get the current stage and set the new scene.
            Stage stage = (Stage) pizzaSelectionButton.getScene().getWindow();
            stage.setScene(new Scene(root));

            // Set the stage title
            stage.setTitle(title);

            stage.show();
        } catch (IOException e) {
            // Log any exceptions that occur during the FXML file loading.
            logger.log(Level.SEVERE, "Failed to load FXML file: " + fxmlFile, e);
        }
    }
}