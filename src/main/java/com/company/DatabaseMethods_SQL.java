package main.java.com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DatabaseMethods_SQL extends DatabasePadre {

    public final String uri="jdbc:mysql://localhost/mydatabase?user=myuser&password=mypass";
    @Override
    void insert(String title, String synopsis) {
        try(Connection conn = DriverManager.getConnection(uri)){


            //INSERT
            PreparedStatement statement;

            if(!synopsis.equalsIgnoreCase("")){//si no es nulo
                statement = conn.prepareStatement("INSERT INTO movies(title, synopsis) VALUES(?,?)");
                statement.setString(2, synopsis);
            }
            else{statement = conn.prepareStatement("INSERT INTO movies VALUES(?)");}

            statement.setString(1, title);//para int seria setInteger

            statement.executeUpdate();//hace el statement

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    Stream<Pelicula> generalConsult(){//devuelve todos los row
        final List<Pelicula> peliculaList=new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(uri)){

            //consulta
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM movies");
            while (resultSet.next()) {
                Pelicula pelicula=new Pelicula(resultSet.getString("id"),resultSet.getString("title"),resultSet.getString("synopsis"));
                peliculaList.add(pelicula);
            }

            return  peliculaList.stream();
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }

    @Override
    Stream<Pelicula> specificSearch(String title) {//devuelve solo un solo tipo de result, si hay 3 con el mismo nombre devuelve 3
        final List<Pelicula> peliculaList=new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(uri)){

            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM movies where title='"+title+"'");


            while (resultSet.next()) {
                Pelicula pelicula=new Pelicula(resultSet.getString("id"),resultSet.getString("title"),resultSet.getString("synopsis"));
                peliculaList.add(pelicula);
            }

            return  peliculaList.stream();
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }

    @Override
    void specificDelete(String title) {//borra solo uno

        try(Connection conn = DriverManager.getConnection(uri)){

            PreparedStatement statement = conn.prepareStatement("delete FROM movies where title='"+title+"'");
            statement.executeUpdate();

            System.out.println("Deleted");
        } catch (SQLException e) {e.printStackTrace();}
    }

    @Override
    void deleteTableData() {
        try(Connection conn = DriverManager.getConnection(uri)){

            PreparedStatement statement = conn.prepareStatement("delete FROM movies");
            statement.executeUpdate();

            System.out.println("Deleted all");
        } catch (SQLException e) {e.printStackTrace();}
    }


}
