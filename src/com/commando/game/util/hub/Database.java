package com.commando.game.util.hub;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Timofti Gabriel
 */
public class Database {

    private ArrayList<Integer> allScores = new ArrayList<>();
    private ArrayList<DataForLoad> savedStates = new ArrayList<>();

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
                System.out.println("Error reading from DB -> queryPath \n");
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
                System.out.println("Error writing in DB -> saveScore \n");
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
            System.err.println( "Error in reading from DB -> loadScore" );
            System.exit(0);
        }
        System.out.println("Operation done successfully");

        return this.allScores;
    }

    public void saveState(String name, int level, int heroLife, int x_Pos_Hero, int y_Pos_Hero, int noOfLifes, String enemyInfo, int totalDamage, int timePassed) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:CommandoDB.db");
            connection.setAutoCommit(false);

            String sql = "INSERT INTO Saves (Name, Level, HeroLife, x_Pos_Hero, y_Pos_Hero, NoOfLifes, EnemyInfo, TotalDamage, TimePassed)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?); ";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, level);
                preparedStatement.setInt(3, heroLife);
                preparedStatement.setInt(4, x_Pos_Hero);
                preparedStatement.setInt(5, y_Pos_Hero);
                preparedStatement.setInt(6, noOfLifes);
                preparedStatement.setString(7, enemyInfo);
                preparedStatement.setInt(8, totalDamage);
                preparedStatement.setInt(9, timePassed);

                preparedStatement.executeUpdate();
                connection.commit();
            }
            catch (SQLException e) {
                System.out.println("Error writing to DB + \n");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Cant find Database or sqlite Driver + \n");
        } finally {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
        }
    }

    public ArrayList<DataForLoad> loadState() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:CommandoDB.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM Saves;" );
            while ( rs.next() ) {
                String name = rs.getString("Name");
                int level = rs.getInt("Level");
                int heroLife = rs.getInt("HeroLife");
                int x_Pos_Hero = rs.getInt("x_Pos_Hero");
                int y_Pos_Hero = rs.getInt("y_Pos_Hero");
                int noOfLifes = rs.getInt("NoOfLifes");
                String enemyInfo = rs.getString("EnemyInfo");
                int totalDamage = rs.getInt("TotalDamage");
                int timePassed = rs.getInt("TimePassed");

                DataForLoad dataForLoad = new DataForLoad(name, level, heroLife, x_Pos_Hero, y_Pos_Hero, noOfLifes, enemyInfo, totalDamage, timePassed);
                savedStates.add(dataForLoad);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( "Error in reading from DB -> loadStates \n" );
            System.exit(0);
        }
        System.out.println("Operation done successfully \n");

        return savedStates;
    }
}
