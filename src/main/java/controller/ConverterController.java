package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.ConverterModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ConverterController implements Initializable {

    @FXML
    private ComboBox<String> fromComboBox;

    @FXML
    private ComboBox<String> toComboBox;

    @FXML
    private TextField inputField;

    @FXML
    private Label resultLabel;

    private ConverterModel model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new ConverterModel();

        fromComboBox.getItems().addAll("Проценты", "Коэффициенты", "Дроби");
        toComboBox.getItems().addAll("Проценты", "Коэффициенты", "Дроби");

        fromComboBox.setValue("Проценты");
        toComboBox.setValue("Коэффициенты");
    }

    @FXML
    private void handleConvert() {
        String input = inputField.getText().trim();
        String from = fromComboBox.getValue();
        String to = toComboBox.getValue();

        if (input.isEmpty()) {
            resultLabel.setText("Ошибка: Введите значение!");
            return;
        }

        try {
            String result = "";

            if (from.equals("Проценты") && to.equals("Коэффициент")) {
                double value = Double.parseDouble(input);
                result = String.format("%.4f", model.percentToKoef(value));
            } else if (from.equals("Коэффициент") && to.equals("Проценты")) {
                double value = Double.parseDouble(input);
                result = String.format("%.2f%%", model.koefToPercent(value));
            } else if (from.equals("Проценты") && to.equals("Дроби")) {
                double value = Double.parseDouble(input);
                result = model.percentToFraction(value);
            } else if (from.equals("Коэффициент") && to.equals("Дроби")) {
                double value = Double.parseDouble(input);
                result = model.koefToFraction(value);
            } else if (from.equals("Дроби") && to.equals("Проценты")) {
                result = String.format("%.2f%%", model.fractionToPercent(input));
            } else if (from.equals("Дроби") && to.equals("Коэффициент")) {
                result = String.format("%.4f", model.fractionToKoef(input));
            } else {
                result = String.format("%.4f", Double.parseDouble(input));
            }

            resultLabel.setText(result);

        } catch (NumberFormatException e) {
            resultLabel.setText("Ошибка: Неверный формат числа!");
        } catch (Exception e) {
            resultLabel.setText("Ошибка: " + e.getMessage());
        }
    }

    @FXML
    private void handleClear() {
        inputField.clear();
        resultLabel.setText("0");
    }
}