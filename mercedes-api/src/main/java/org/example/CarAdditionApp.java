package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import org.example.models.Car;
import org.example.dao.CarDAO;

public class CarAdditionApp extends Application {
    private TextField nameField;
    private TextField descriptionField;
    private TextField hpField;
    private CheckBox amgCheckBox;
    private CheckBox coupeCheckBox;
    private TextField yearField;
    private TextField imageUrlField;
    private TextField categoryIdField;
    private TextField priceField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Добавление машины");

        // Создание компонентов для ввода данных
        nameField = new TextField();
        nameField.setPromptText("Название машины");
        descriptionField = new TextField();
        descriptionField.setPromptText("Описание");
        hpField = new TextField();
        hpField.setPromptText("Мощность (HP)");
        amgCheckBox = new CheckBox("AMG");
        coupeCheckBox = new CheckBox("Coupe");
        yearField = new TextField();
        yearField.setPromptText("Год выпуска");
        imageUrlField = new TextField();
        imageUrlField.setPromptText("URL изображения");
        categoryIdField = new TextField();
        categoryIdField.setPromptText("ID категории");
        priceField = new TextField();
        priceField.setPromptText("Цена");

        // Создание кнопки для добавления машины
        Button addButton = new Button("Добавить машину");
        addButton.setOnAction(e -> addCar());

        // Создание макета для компонентов
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Название:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Описание:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Мощность:"), 0, 2);
        grid.add(hpField, 1, 2);
        grid.add(amgCheckBox, 0, 3);
        grid.add(coupeCheckBox, 1, 3);
        grid.add(new Label("Год выпуска:"), 0, 4);
        grid.add(yearField, 1, 4);
        grid.add(new Label("URL изображения:"), 0, 5);
        grid.add(imageUrlField, 1, 5);
        grid.add(new Label("ID категории:"), 0, 6);
        grid.add(categoryIdField, 1, 6);
        grid.add(new Label("Цена:"), 0, 7);
        grid.add(priceField, 1, 7);
        grid.add(addButton, 0, 8, 2, 1);

        // Создание сцены и установка макета
        Scene scene = new Scene(grid, 400, 450);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void addCar() {
        String id = String.valueOf(Car.getCars().size() + 1);
        // Получение данных из полей ввода
        String name = nameField.getText();
        String description = descriptionField.getText();
        int hp = Integer.parseInt(hpField.getText());
        boolean amg = amgCheckBox.isSelected();
        boolean coupe = coupeCheckBox.isSelected();
        int year = Integer.parseInt(yearField.getText());
        String imageUrl = imageUrlField.getText();
        int categoryId = Integer.parseInt(categoryIdField.getText());
        int price = Integer.parseInt(priceField.getText());

        // Создание объекта Car и сохранение его в базу данных
        Car car = new Car(id, name, description, hp, amg, coupe, year, imageUrl, categoryId, price);
        CarDAO.insertCar(car);

        // Очистка полей ввода после добавления
        nameField.clear();
        descriptionField.clear();
        hpField.clear();
        amgCheckBox.setSelected(false);
        coupeCheckBox.setSelected(false);
        yearField.clear();
        imageUrlField.clear();
        categoryIdField.clear();
        priceField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
