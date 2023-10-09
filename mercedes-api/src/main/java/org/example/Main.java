package org.example;

import org.example.dao.CarDAO;
import org.example.dao.CategoryDAO;
import org.example.models.ICar;
import org.example.models.ICategory;
import org.example.utils.Util;
import org.example.config.config;
import spark.Request;
import spark.Spark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Spark.port(8000);

        enableCORS("*", "GET,POST,PUT,DELETE", "Content-Type,Authorization");

        try {
            System.out.println("DB password: ");
            Scanner scan = new Scanner(System.in);
            config.setDbPassword(scan.nextLine());

            Connection connection = DriverManager.getConnection(config.getDbUrl(), config.getDbUser(), config.getDbPassword());
            Statement statement = connection.createStatement();

            ICar.setCars(CarDAO.getAllCarsFromDB(statement));
            ICategory.setCategories(CategoryDAO.getAllCategoriesFromDB(statement));

            Spark.get("/cars", (req, res) -> {
                res.type("application/json");
                return Util.toJson(ICar.getCars());
            });

            Spark.get("/categories", (req, res) -> {
                res.type("application/json");
                return Util.toJson(ICategory.getCategories());
            });

            Spark.get("/cars/:id", (req, res) -> {
                String id = req.params("id");
                ICar car = Util.findCarById(ICar.getCars(), id);
                if (car != null) {
                    res.type("application/json"); // Устанавливаем тип контента в JSON
                    return Util.toJson(car);
                } else {
                    res.status(404);
                    return "Машина не найдена";
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void enableCORS(final String origin, final String methods, final String headers) {
        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
        });
    }
}
