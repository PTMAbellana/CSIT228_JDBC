package com.example.csit228_f1_v2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReadData {
    public static boolean readData(String username, String password) {
        try (Connection c = MySQLConnection.getConnection();) {
            Statement statement = c.createStatement();
            String query = "SELECT * FROM users";
            ResultSet res = statement.executeQuery(query); //FOR READING
            while (res.next()) {
                String name = res.getString("username");
                String pass = res.getString("password");
                if (name.equals(username) && pass.equals(password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public static int readData(String username) {
        try (Connection c = MySQLConnection.getConnection();) {
            Statement statement = c.createStatement();
            String query = "SELECT * FROM users";
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("username");
                if (name.equals(username)) {
                    return id;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static List<Product> readProductsByUserId(int userId) {
        List<Product> products = new ArrayList<>();
        try (Connection c = MySQLConnection.getConnection()) {
            Statement statement = c.createStatement();
            String query = "SELECT * FROM products WHERE user_id = " + userId;
            ResultSet res = statement.executeQuery(query); // FOR READING
            while (res.next()) {
                int productId = res.getInt("prodid");
                String productName = res.getString("product_name");
                double productPrice = res.getDouble("price");
                String productDescription = res.getString("description");
                // Create a Product object and add it to the list
                products.add(new Product(productId, productName, productPrice, productDescription));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public static ResultSet all_products(){
        try{Connection c = MySQLConnection.getConnection();
            Statement s = c.createStatement();
            String query = "SELECT * FROM products WHERE userid="+HomeController.loggedInID;
            return s.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
