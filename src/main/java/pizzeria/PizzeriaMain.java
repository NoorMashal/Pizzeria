/**
 * @author Noor Mashal
 */
package pizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PizzeriaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            /* Load The Main View */
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/pizzeria/view/MainView.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setTitle("RU Pizzeria - Main Menu");
            primaryStage.getIcons().add(new Image("pizzeria/images/pizzaIcon.png")); /* Set the Icon */
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
