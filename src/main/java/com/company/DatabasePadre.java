package main.java.com.company;


import java.util.stream.Stream;

public abstract class DatabasePadre {
    abstract void insert(String title, String sinopsis);
    abstract Stream<Pelicula> generalConsult();
    abstract Stream<Pelicula> specificSearch(String title);
    abstract void specificDelete(String title);
    abstract void deleteTableData();


}
