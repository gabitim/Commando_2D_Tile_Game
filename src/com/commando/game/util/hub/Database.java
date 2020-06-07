package com.commando.game.util.hub;

import java.sql.*;

/**
 * @author Timofti Gabriel
 */
public class Database {

    public static String query(String query, String name) {

        Connection connection;
        PreparedStatement statement;

        String path = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:CommandoDB.db");

            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, name);
                rs = ps.executeQuery();
                path = rs.getString(1);

                System.out.println("Loading from Database: " + path);

            } catch (SQLException e) {
                System.out.println("Error reading from DB + \n");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Cant find Database or sqlite Driver + \n");
        }

        return path;
    }
}
