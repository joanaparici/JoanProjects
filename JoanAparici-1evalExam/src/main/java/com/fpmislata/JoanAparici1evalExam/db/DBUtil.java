package com.fpmislata.JoanAparici1evalExam.db;


import com.fpmislata.JoanAparici1evalExam.exception.SQLStatmentException;

import java.sql.*;
import java.util.List;


public class DBUtil {
    // final static String DBDRIVER = "jdbc:mysql";
    // final static String DBHOST = "tiedapadel-1.covjfghjx3kw.us-east-1.rds.amazonaws.com";
    // final static String DBNAME = "tiedapadel";
    // final static String DBUSER = "root";
    // final static String DBPASS = "TiedaPadel";

    // static String connectionString = String.format("%s://%s/%s?user=%s&password=%s", DBDRIVER, DBHOST, DBNAME, DBUSER, DBPASS);

    final static String DRIVER = "jdbc:mariadb";
    final static String URL = "localhost:3310";
    final static String DB = "library";
    final static String USER = "root";
    final static String PASSWORD = "root";

    static String connectionString = String.format("%s://%s/%s?user=%s&password=%s", DRIVER, URL, DB, USER, PASSWORD);

    public static Connection open(boolean autoCommit){
        try {
            System.out.println("Parámetros de conexión: ");
            Connection connection = DriverManager.getConnection(connectionString);
            connection.setAutoCommit(autoCommit);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("Error al conectar con la bbdd");
        }
    }


    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexión con la bbdd");
        }
    }

    public static ResultSet select(Connection connection, String sql, List<Object> values){
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static int insert(Connection connection, String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return resultSet.getInt(1);
            } else {
                throw new RuntimeException("Cannot read last generated id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLStatmentException("SQL: " + sql);
        }
    }

    public static int update(Connection connection, String sql, List<Object> values){
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public static int delete(Connection connection, String sql, List<Object> values){
        try {
            PreparedStatement preparedStatement = setParameters(connection, sql, values);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement setParameters(Connection connection, String sql, List<Object> values){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (values != null) {
                for (int i = 0; i < values.size(); i++) {
                    Object value = values.get(i);
                    preparedStatement.setObject(i + 1, value);
                }
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

