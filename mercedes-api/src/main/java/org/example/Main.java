package org.example;

import org.example.dao.CarDAO;
import org.example.models.Car;
import org.example.utils.Util;
import org.example.config.config;
import spark.Request;
import spark.Spark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    private static Car parseCarFromRequest(Request req) {
        String id = req.queryParams("id");
        String name = req.queryParams("name");
        String description = req.queryParams("description");
        int hp = Integer.parseInt(req.queryParams("hp"));
        boolean amg = Boolean.parseBoolean(req.queryParams("amg"));
        boolean coupe = Boolean.parseBoolean(req.queryParams("coupe"));
        int year = Integer.parseInt(req.queryParams("year"));
        String imageUrl = req.queryParams("img_url");
        int categoryId = Integer.parseInt(req.queryParams("category_id"));
        int price = Integer.parseInt(req.queryParams("price"));

        return new Car(id, name, description, hp, amg, coupe, year, imageUrl, categoryId, price);
    }

    public static void main(String[] args) {
        Spark.port(8000);

        enableCORS("*", "GET,POST,PUT,DELETE", "Content-Type,Authorization");

        try {
            System.out.println("DB password: ");
            Scanner scan = new Scanner(System.in);
            config.setDbPassword(scan.nextLine());

            Connection connection = DriverManager.getConnection(config.getDbUrl(), config.getDbUser(), config.getDbPassword());
            Statement statement = connection.createStatement();

            Car.setCars(CarDAO.getAllCarsFromDB(statement));

            Spark.get("/cars", (req, res) -> {
                res.type("application/json");
                return Util.toJson(Car.getCars());
            });

            Spark.post("/cars", (req, res) -> {
                Car newCar = parseCarFromRequest(req);
                CarDAO.insertCar(newCar);
                res.status(201);
                return "Машина добавлена успешно!";
            });

            Spark.put("/cars/:id", (req, res) -> {
                String id = req.params("id");
                Car updatedCar = parseCarFromRequest(req);
                updatedCar.setId(id);
                CarDAO.updateCar(updatedCar);
                return "Машина обновлена успешно!";
            });

            Spark.get("/cars/:id", (req, res) -> {
                String id = req.params("id");
                Car car = Util.findCarById(Car.getCars(), id);
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
