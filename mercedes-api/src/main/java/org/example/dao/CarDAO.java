package org.example.dao;

import org.example.config.config;
import org.example.models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(config.getDbUrl(), config.getDbUser(), config.getDbPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CarDAO() throws SQLException {}

    public static List<Car> getAllCarsFromDB(Statement statement) {
        List<Car> cars = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cars");
            while (resultSet.next()) {
                Car car = new Car(
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

    public static void insertCar(Car car) {
        try {
            String sql = "INSERT INTO cars (id, name, description, hp, amg, coupe, year, img_url, category_id, price) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, car.getId());
            statement.setString(2, car.getName());
            statement.setString(3, car.getDescription());
            statement.setInt(4, car.getHp());
            statement.setBoolean(5, car.isAmg());
            statement.setBoolean(6, car.isCoupe());
            statement.setInt(7, car.getYear());
            statement.setString(8, car.getImg_url());
            statement.setInt(9, car.getCategoryID());
            statement.setInt(10, car.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCar(Car car) {
        try {
            String sql = "UPDATE cars SET name=?, description=?, hp=?, amg=?, coupe=?, year=?, img_url=?, category_id=?, price=? " +
                    "WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, car.getName());
            statement.setString(2, car.getDescription());
            statement.setInt(3, car.getHp());
            statement.setBoolean(4, car.isAmg());
            statement.setBoolean(5, car.isCoupe());
            statement.setInt(6, car.getYear());
            statement.setString(7, car.getImg_url());
            statement.setInt(8, car.getCategoryID());
            statement.setInt(9, car.getPrice());
            statement.setString(10, car.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
