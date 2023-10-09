package org.example.config;

public class config {
    private static String dbUrl = "jdbc:postgresql://localhost/Mercedes";
    private static String dbUser = "postgres";
    private static String dbPassword = "";

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbPassword() {
        return dbPassword;
    }

    public static void setDbPassword(String dbPassword) {
        config.dbPassword = dbPassword;
    }
}
