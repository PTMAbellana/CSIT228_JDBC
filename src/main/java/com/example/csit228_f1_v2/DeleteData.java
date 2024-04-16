package com.example.csit228_f1_v2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    public static void main(String[] args) {
        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement statement = c.prepareStatement(
                    "DELETE FROM users WHERE id >=?"
            );
        ) {
            int starting_id = 2;
            statement.setInt(1,starting_id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Rows Deleted: " + rowsDeleted);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}