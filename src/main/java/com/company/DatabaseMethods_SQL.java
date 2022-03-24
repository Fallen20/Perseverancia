package main.java.com.company;

import lombok.Value;

import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class DatabaseMethods_SQL extends DatabasePadre {


    @Override
    void insert(String title) {
        try(Connection conn = DriverManager.getConnection(System.getProperty("sqlurl"))){

            //INSERT
            PreparedStatement statement = conn.prepareStatement("INSERT INTO movies(title) VALUES(?)");
            statement.setString(1, title);
            //para int seria setInteger
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    List<Pelicula> generalConsult(){//devuelve todos los row

        try(Connection conn = DriverManager.getConnection(System.getProperty("sqlurl"))){

            //consulta
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM movies");
            while (resultSet.next()) {
                System.out.println("Id: "+resultSet.getString("id")
                        +", title: "+resultSet.getString("title")
                        +", synopsis: "+resultSet.getString("synopsis"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    void specificSearch(String title) {//devuelve solo un row
        try(Connection conn = DriverManager.getConnection(System.getProperty("sqlurl"))){

            //consulta
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM movies where title='"+title+"'");
            while (resultSet.next()) {
                System.out.println("Id: "+resultSet.getString("id")
                        +", title: "+resultSet.getString("title")
                        +", synopsis: "+resultSet.getString("synopsis"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    void specificDelete(String title) {//borra solo uno
        try(Connection conn = DriverManager.getConnection(System.getProperty("sqlurl"))){

            PreparedStatement statement = conn.prepareStatement("delete FROM movies where title='"+title+"'");
            statement.executeUpdate();

            System.out.println("Deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    void deleteTableData() {
        try(Connection conn = DriverManager.getConnection(System.getProperty("sqlurl"))){

            PreparedStatement statement = conn.prepareStatement("delete FROM movies");
            statement.executeUpdate();

            System.out.println("Deleted all");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
