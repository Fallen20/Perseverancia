package main.java.com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DatabaseMethods_SQL extends DatabasePadre {

    public final String uri="jdbc:mysql://localhost/mydatabase?user=myuser&password=mypass";
    @Override
    void insertPelicula(String title, String synopsis) {
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
    Stream<Pelicula> generalConsultPelicula(){//devuelve todos los row
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
    Stream<Pelicula> specificSearchPelicula(String title) {//devuelve solo un solo tipo de result, si hay 3 con el mismo nombre devuelve 3
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
    void specificDeletePelicula(String title) {//borra solo uno

        try(Connection conn = DriverManager.getConnection(uri)){

            PreparedStatement statement = conn.prepareStatement("delete FROM movies where title='"+title+"'");
            statement.executeUpdate();

            System.out.println("Deleted");
        } catch (SQLException e) {e.printStackTrace();}
    }

    @Override
    void deleteTableDataPelicula() {
        try(Connection conn = DriverManager.getConnection(uri)){

            PreparedStatement statement = conn.prepareStatement("delete FROM movies");
            statement.executeUpdate();

            System.out.println("Deleted all");
        } catch (SQLException e) {e.printStackTrace();}
    }


    //-------------------------------------------
    //ACTOR
    @Override
    void insertActor(String name, int age) {//ok

        try(Connection conn = DriverManager.getConnection(uri)){

            //INSERT
            PreparedStatement statement= conn.prepareStatement("INSERT INTO actors (name_actor, age) VALUES(?,?)");

            statement.setString(1, name);
            statement.setInt(2, age);//para int seria setInt


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    Stream<Actor> generalConsultActor() {
        final List<Actor> actorList=new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(uri)){

            //consulta
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM actors");
            while (resultSet.next()) {
                Actor actor=new Actor(resultSet.getString("actor_id"),resultSet.getString("name_actor"),resultSet.getInt("age"));
                actorList.add(actor);
            }

            return  actorList.stream();
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }

    @Override
    Stream<Actor> specificSearchActor(String name) {
        final List<Actor> actorList=new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(uri)){

            //consulta
            ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM actors where name_actor='"+name+"'");
            while (resultSet.next()) {
                Actor pelicula=new Actor(resultSet.getString("actor_id"),resultSet.getString("name_actor"),resultSet.getInt("age"));
                actorList.add(pelicula);
            }

            return  actorList.stream();
        } catch (SQLException e) {e.printStackTrace();}
        return null;
    }

    @Override
    void specificDeleteActor(String name) {
        try(Connection conn = DriverManager.getConnection(uri)){

            PreparedStatement statement = conn.prepareStatement("delete FROM actors where name_actor='"+name+"'");
            statement.executeUpdate();

            System.out.println("Deleted");
        } catch (SQLException e) {e.printStackTrace();}


    }

    @Override
    void deleteTableDataActor() {
        try(Connection conn = DriverManager.getConnection(uri)){

            PreparedStatement statement = conn.prepareStatement("delete FROM actors");
            statement.executeUpdate();

            System.out.println("Deleted all");
        } catch (SQLException e) {e.printStackTrace();}

    }


}
