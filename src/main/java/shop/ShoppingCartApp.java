package shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShoppingCartApp extends Application {

    private static Locale currentLocale = new Locale("en", "US");

    @Override
    public void start(Stage stage) throws IOException {
        loadScene(stage, currentLocale);
    }

    public static void loadScene(Stage stage, Locale locale) throws IOException {
        currentLocale = locale;

        ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        FXMLLoader loader = new FXMLLoader(
                ShoppingCartApp.class.getResource("/shop-view.fxml"),
                bundle
        );

        Scene scene = new Scene(loader.load(), 700, 500);

        if ("ar".equals(locale.getLanguage())) {
            scene.getRoot().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else {
            scene.getRoot().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }

        stage.setTitle("Poornima Jayamanna - Shopping Cart App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}