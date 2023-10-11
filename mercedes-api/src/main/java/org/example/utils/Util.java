package org.example.utils;

import com.google.gson.Gson;
import org.example.models.Car;

import java.util.List;

public class Util {
    //Метод для преобразования объекта в формат JSON
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    //Метод для поиска машины по ID
    public static Car findCarById(List<Car> cars, String id) {
        for (Car car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }
}
