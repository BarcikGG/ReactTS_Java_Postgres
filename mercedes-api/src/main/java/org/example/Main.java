package org.example;

import org.example.dao.CarDAO;
import org.example.dao.CategoryDAO;
import org.example.dao.MailDAO;
import org.example.models.Car;
import org.example.models.Category;
import org.example.utils.Util;
import org.example.config.config;
import spark.Spark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Spark.port(8000);

        enableCORS("*", "GET, POST, OPTIONS", "Content-Type,Authorization");

        try {
            System.out.println("DB password: ");
            Scanner scan = new Scanner(System.in);
            config.setDbPassword(scan.nextLine());

            Connection connection = DriverManager.getConnection(config.getDbUrl(), config.getDbUser(), config.getDbPassword());
            Statement statement = connection.createStatement();
            System.out.println("Сервер запущен!");

            Car.setCars(CarDAO.getAllCarsFromDB(statement));
            Category.setCategories(CategoryDAO.getAllCategoriesFromDB(statement));

            Spark.get("/cars", (req, res) -> {
                res.type("application/json");
                return Util.toJson(Car.getCars());
            });

            Spark.get("/categories", (req, res) -> {
                res.type("application/json");
                return Util.toJson(Category.getCategories());
            });

            Spark.get("/cars/:id", (req, res) -> {
                String id = req.params("id");
                Car car = Util.findCarById(Car.getCars(), id);
                if (car != null) {
                    res.type("application/json");
                    return Util.toJson(car);
                } else {
                    res.status(404);
                    return "Машина не найдена";
                }
            });

            Spark.get("/sendEmail", (request, response) -> {
                try {
                    String fullName = request.queryParams("fullName");
                    String email = request.queryParams("email");
                    String phoneNumber = request.queryParams("phoneNumber");
                    String carName = request.queryParams("carName");

                    MailDAO.sendEmail(fullName, email, phoneNumber, carName);

                    response.status(200);
                    return "Письмо отправлено успешно";
                } catch (Exception e) {
                    e.printStackTrace();
                    response.status(500);
                    return "Ошибка при отправке письма";
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
