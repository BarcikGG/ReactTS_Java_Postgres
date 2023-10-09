package org.example.utils;

import com.google.gson.Gson;
import org.example.models.ICar;

import java.util.List;

public class Util {
    //Метод для преобразования объекта в формат JSON
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    //Метод для поиска машины по ID
    public static ICar findCarById(List<ICar> cars, String id) {
        for (ICar car : cars) {
            if (car.getId().equals(id)) {
                return car;
            }
        }
        return null;
    }
}
