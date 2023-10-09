package org.example.dao;

import org.example.models.ICar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    public static List<ICar> getAllCarsFromDB(Statement statement) {
        List<ICar> cars = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cars");
            while (resultSet.next()) {
                ICar car = new ICar(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("hp"),
                        resultSet.getBoolean("amg"),
                        resultSet.getBoolean("coupe"),
                        resultSet.getInt("createdAt"),
                        resultSet.getString("image_url"),
                        resultSet.getInt("category_id"),
                        resultSet.getInt("price")
                );
                cars.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }
}
