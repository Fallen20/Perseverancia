package main.java.com.company;

import java.util.Scanner;

public class Main2 {
    public static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        DatabasePadre databaseUsada=null;
        String titulo=null;

        System.out.println("Que base de datos quieres usar?");
        System.out.println("1. SQL");
        System.out.println("2. MongoDB");
        int sqlOption=sc.nextInt();
        //doingWhat(sqlOption);//manera personal
        if(sqlOption==1){databaseUsada=new DatabaseMethods_SQL();}
        if(sqlOption==2){databaseUsada=new DatabaseMethodsMongo();}

        boolean salir=false;
        int opcion=0;

        while(!salir){
            System.out.println("Que quieres hacer?");
            System.out.println("1. Consulta general");
            System.out.println("2. Consulta por título");
            System.out.println("3. Insertar");
            System.out.println("4. Delete");
            System.out.println("5. Delete all");
            System.out.println("6. Exit");
            opcion=sc.nextInt();

            switch (opcion){
                case 1:
                    databaseUsada.generalConsult();
                    break;
                case 2:
                    System.out.println("Dime el titulo:");
                    titulo=sc.next();

                    databaseUsada.specificSearch(titulo);
                    break;
                case 3:
                    System.out.println("Dime el titulo:");
                    titulo=sc.next();

                    databaseUsada.insert(titulo);
                    break;
                case 4:
                    System.out.println("Dime el titulo:");
                    titulo=sc.next();

                    databaseUsada.specificDelete(titulo);
                    break;
                case 5:
                    databaseUsada.deleteTableData();
                    break;
                case 6:
                    salir=true;
                    break;
            }
        }



    }
/*
    private static void doingWhat(int databasePadre){
        System.out.println("Que quieres hacer?");
        System.out.println("1. Consultar");
        System.out.println("2. Insertar");
        int opcion=sc.nextInt();


        if(opcion==1){DatabaseMethods_SQL.consultarPeliculas(databasePadre);}
        else if(opcion==2){
            System.out.println("Dime el titulo:");
            String titulo=sc.nextLine();

            databasePadre.insertarPelicula(titulo, sqlOption);
        }
        else{System.out.println("La opcion no es valida");}
    }
    private static void insertarPelicula(String titulo, int sqlOption) {

        if(sqlOption==1){DatabaseMethods_SQL.insertSQL(titulo);}
        else if(sqlOption==2){DatabaseMethods_Mongo.insertMongo(titulo);}
        else{System.out.println("La database no es valida");}
    }


    private static void consultarPeliculas(int sqlOption) {
        if(sqlOption==1){DatabaseMethods_SQL.consultarSQL();}
        else if(sqlOption==2){DatabaseMethods_Mongo.consultarMongo();}
        else{System.out.println("La database no es valida");}
    }*/


}
