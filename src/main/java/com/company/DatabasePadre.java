package main.java.com.company;


import java.util.stream.Stream;

public abstract class DatabasePadre {
    abstract void insertPelicula(String title, String sinopsis);
    abstract Stream<Pelicula> generalConsultPelicula();
    abstract Stream<Pelicula> specificSearchPelicula(String title);
    abstract void specificDeletePelicula(String title);
    abstract void deleteTableDataPelicula();

    abstract void insertActor(String name, int age);
    abstract Stream<Actor> generalConsultActor();
    abstract Stream<Actor> specificSearchActor(String name);
    abstract void specificDeleteActor(String name);
    abstract void deleteTableDataActor();

}
