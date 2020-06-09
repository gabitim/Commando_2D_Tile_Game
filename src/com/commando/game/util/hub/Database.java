package com.commando.game.util.hub;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Timofti Gabriel
 */
public class Database {

    private ArrayList<Integer> allScores = new ArrayList<Integer>();

    public String queryPath(String query, String name) throws SQLException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String path = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:CommandoDB.db");

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
        } finally {
            assert rs != null;
            rs.close();
            ps.close();
            connection.close();
        }

        return path;
    }

    public void saveScore(int score) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:CommandoDB.db");
            connection.setAutoCommit(false);

            String sql = "INSERT INTO SCORES (Score) VALUES(?); ";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, score);
                preparedStatement.executeUpdate();
                connection.commit();
            }
            catch (SQLException e) {
                System.out.println("Error reading from DB + \n");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Cant find Database or sqlite Driver + \n");
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
    }

    public ArrayList<Integer> loadScore() throws SQLException {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:CommandoDB.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT Score FROM Scores;" );
            while ( rs.next() ) {
                int score = rs.getInt("Score");
                //System.out.println( "Score = " + score );
                allScores.add(score);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return this.allScores;
    }

    public void saveState(int score) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:CommandoDB.db");
            connection.setAutoCommit(false);

            String sql = "INSERT INTO SCORES (Score) VALUES(?); ";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, score);
                preparedStatement.executeUpdate();
                connection.commit();
            }
            catch (SQLException e) {
                System.out.println("Error reading from DB + \n");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Cant find Database or sqlite Driver + \n");
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
    }
}
