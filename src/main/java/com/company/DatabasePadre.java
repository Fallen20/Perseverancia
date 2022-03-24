package main.java.com.company;


import java.util.stream.Stream;

public abstract class DatabasePadre {
    abstract void insert(String title);
    abstract Stream<Pelicula> generalConsult();
    abstract void specificSearch(String title);
    abstract void specificDelete(String title);
    abstract void deleteTableData();//creo que no


}
