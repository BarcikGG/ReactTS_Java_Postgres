package org.example.models;

import java.util.List;

public class ICategory {
    private int id;
    private String name;

    public ICategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private static List<ICategory> categories;

    public static List<ICategory> getCategories() {
        return categories;
    }

    public static void setCategories(List<ICategory> categories) {
        ICategory.categories = categories;
    }
}


