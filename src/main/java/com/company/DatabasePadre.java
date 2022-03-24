package main.java.com.company;

import main.java.com.company.Pelicula;

import java.util.List;

public abstract class DatabasePadre {
    abstract void insert(String title);
    abstract List<Pelicula> generalConsult();
    abstract void specificSearch(String title);
    abstract void specificDelete(String title);
    abstract void deleteTableData();//creo que no


}
