package org.example.dao;

import org.example.models.ICategory;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public static List<ICategory> getAllCategoriesFromDB(Statement statement) {
        List<ICategory> categories = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            while (resultSet.next()) {
                ICategory category = new ICategory(
                        resultSet.getInt("id"),
                        resultSet.getString("type")
                );
                categories.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
}
