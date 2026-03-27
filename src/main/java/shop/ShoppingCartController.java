package shop;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ShoppingCartController {

    @FXML private Label languageLabel;
    @FXML private ComboBox<String> languageComboBox;
    @FXML private Button confirmLanguageButton;
    @FXML private Label itemCountLabel;
    @FXML private TextField itemCountField;
    @FXML private Button generateItemsButton;
    @FXML private VBox itemsContainer;
    @FXML private Button calculateButton;
    @FXML private Label totalLabel;
    @FXML private Label resultLabel;

    private final CartCalculator calculator = new CartCalculator();
    private ResourceBundle bundle;

    @FXML
    public void initialize() {
        languageComboBox.setItems(FXCollections.observableArrayList(
                "English", "Finnish", "Swedish", "Japanese", "Arabic"
        ));
        languageComboBox.setValue("English");

        bundle = ResourceBundle.getBundle("MessagesBundle", new Locale("en", "US"));
    }

    @FXML
    private void handleLanguageChange() {
        try {
            String selected = languageComboBox.getValue();
            Locale locale;

            switch (selected) {
                case "Finnish":
                    locale = new Locale("fi", "FI");
                    break;
                case "Swedish":
                    locale = new Locale("sv", "SE");
                    break;
                case "Japanese":
                    locale = new Locale("ja", "JP");
                    break;
                case "Arabic":
                    locale = new Locale("ar", "AR");
                    break;
                default:
                    locale = new Locale("en", "US");
            }

            Stage stage = (Stage) languageComboBox.getScene().getWindow();
            ShoppingCartApp.loadScene(stage, locale);

        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Language change failed");
        }
    }

    @FXML
    private void handleGenerateItems() {
        itemsContainer.getChildren().clear();

        try {
            int itemCount = Integer.parseInt(itemCountField.getText());

            String selected = languageComboBox.getValue();
            Locale locale;

            switch (selected) {
                case "Finnish":
                    locale = new Locale("fi", "FI");
                    break;
                case "Swedish":
                    locale = new Locale("sv", "SE");
                    break;
                case "Japanese":
                    locale = new Locale("ja", "JP");
                    break;
                case "Arabic":
                    locale = new Locale("ar", "AR");
                    break;
                default:
                    locale = new Locale("en", "US");
            }

            bundle = ResourceBundle.getBundle("MessagesBundle", locale);

            for (int i = 1; i <= itemCount; i++) {
                Label itemLabel = new Label(bundle.getString("item.label") + " " + i);

                TextField priceField = new TextField();
                priceField.setPromptText(bundle.getString("price.prompt"));

                TextField quantityField = new TextField();
                quantityField.setPromptText(bundle.getString("quantity.prompt"));

                HBox row = new HBox(10, itemLabel, priceField, quantityField);
                itemsContainer.getChildren().add(row);
            }

        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid number");
        }
    }

    @FXML
    private void handleCalculate() {
        List<Double> prices = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();

        try {
            for (Node node : itemsContainer.getChildren()) {
                if (node instanceof HBox row) {
                    TextField priceField = (TextField) row.getChildren().get(1);
                    TextField quantityField = (TextField) row.getChildren().get(2);

                    prices.add(Double.parseDouble(priceField.getText()));
                    quantities.add(Integer.parseInt(quantityField.getText()));
                }
            }

            double total = calculator.calculateCartTotal(prices, quantities);
            resultLabel.setText(String.format("%.2f", total));

        } catch (Exception e) {
            resultLabel.setText("Invalid input");
        }
    }
}