package org.example.models;

import java.util.List;

public class Car {
    private String id;
    private String name;
    private String description;
    private int hp;
    private boolean amg;
    private boolean coupe;
    private int year;
    private String img_url;
    private int categoryID;
    private int price;

    public Car(String id, String name, String description, int hp, boolean amg, boolean coupe, int year, String img_url, int categoryID, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.amg = amg;
        this.coupe = coupe;
        this.year = year;
        this.img_url = img_url;
        this.categoryID = categoryID;
        this.price = price;
    }

    private static List<Car> cars;

    public static List<Car> getCars() {
        return cars;
    }

    public static void setCars(List<Car> cars) {
        Car.cars = cars;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHp() {
        return hp;
    }

    public boolean isAmg() {
        return amg;
    }

    public boolean isCoupe() {
        return coupe;
    }

    public int getYear() {
        return year;
    }

    public String getImg_url() {
        return img_url;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }
}

