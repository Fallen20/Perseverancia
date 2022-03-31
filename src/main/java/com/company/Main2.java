package main.java.com.company;

import java.util.Scanner;

public class Main2 {
    public static Scanner sc = new Scanner(System.in).useDelimiter("\\n");
    private static boolean salir = false;

    public static void main(String[] args) {
        DatabasePadre databaseUsada = null;
        String titulo = null;

        System.out.println("Que base de datos quieres usar?");
        System.out.println("1. SQL");
        System.out.println("2. MongoDB");
        int sqlOption = sc.nextInt();
        //doingWhat(sqlOption);//manera personal
        if (sqlOption == 1) {
            databaseUsada = new DatabaseMethods_SQL();
        }
        if (sqlOption == 2) {
            databaseUsada = new DatabaseMethodsMongo();
        }

        final DatabasePadre database2 = databaseUsada;

        System.out.println("Que tabla quieres consultar?");
        System.out.println("1. Movies");
        System.out.println("2. Actors");
        int tabla=sc.nextInt();

        if(tabla==1){
            while (!salir) {PeliculasMenu(databaseUsada, database2);}

        }
        else if(tabla==2){
            while (!salir) {ActorMenu(databaseUsada, database2);}
        }


    }


    private static void PeliculasMenu(DatabasePadre databaseUsada, DatabasePadre database2) {
        int opcion = 0;
        String titulo = "";

        System.out.println("-----MOVIE------");
        System.out.println("Que quieres hacer?");
        System.out.println("1. Consulta general");
        System.out.println("2. Consulta por título");
        System.out.println("3. Insertar");
        System.out.println("4. Specific delete");
        System.out.println("5. Delete all");
        System.out.println("6. Exit");

        opcion = sc.nextInt();

        switch (opcion) {
            case 1:

                databaseUsada.generalConsultPelicula().forEach(result -> {
                    if (database2 instanceof DatabaseMethods_SQL) {
                        System.out.println(result.toStringSQL());
                    } else if (database2 instanceof DatabaseMethodsMongo) {
                        System.out.println(result.toStringMongo());
                    }

                });
                break;


            case 2:
                System.out.println("Dime el nombre:");
                titulo = sc.next();

                databaseUsada.specificSearchPelicula(titulo).forEach(result -> {
                    if (database2 instanceof DatabaseMethods_SQL) {
                        System.out.println(result.toStringSQL());
                    } else if (database2 instanceof DatabaseMethodsMongo) {
                        System.out.println(result.toStringMongo());
                    }
                });
                break;


            case 3:

                System.out.println("Dime el titulo:");
                titulo = sc.next();

                System.out.println("Quieres añadir una sinopsis? Y/N");
                String sinopsisOpcion = sc.next();
                String sinopsis = "";

                if (sinopsisOpcion.equalsIgnoreCase("Y")) {
                    System.out.println("Dime la sinopsis:");
                    sinopsis = sc.next();
                }

                databaseUsada.insertPelicula(titulo, sinopsis);//sino ponen nada ya se pone como ""
                break;


            case 4:
                System.out.println("Dime el titulo:");
                titulo = sc.next();

                databaseUsada.specificDeletePelicula(titulo);
                break;


            case 5:
                databaseUsada.deleteTableDataPelicula();
                break;


            case 6:
                salir = true;
                break;

        }
    }


    private static void ActorMenu(DatabasePadre databaseUsada, DatabasePadre database2) {
        int opcion = 0;
        String nombre = "";
        int age =0;

        System.out.println("-----ACTOR------");
        System.out.println("Que quieres hacer?");
        System.out.println("1. Consulta general");
        System.out.println("2. Consulta por nombre");
        System.out.println("3. Insertar");
        System.out.println("4. Specific delete");
        System.out.println("5. Delete all");
        System.out.println("6. Exit");

        opcion = sc.nextInt();

        switch (opcion) {
            case 1:

                databaseUsada.generalConsultActor().forEach(result -> {
                    if (database2 instanceof DatabaseMethods_SQL) {
                        System.out.println(result.toStringSQL());
                    } else if (database2 instanceof DatabaseMethodsMongo) {
                        System.out.println(result.toStringMongo());
                    }

                });
                break;


            case 2:
                System.out.println("Dime el nombre:");
                nombre = sc.next();

                databaseUsada.specificSearchActor(nombre).forEach(result -> {
                    if (database2 instanceof DatabaseMethods_SQL) {
                        System.out.println(result.toStringSQL());
                    } else if (database2 instanceof DatabaseMethodsMongo) {
                        System.out.println(result.toStringMongo());
                    }
                });
                break;


            case 3:

                System.out.println("Dime el nombre:");
                nombre = sc.next();
                System.out.println("Dime la edad:");
                age = sc.nextInt();

                databaseUsada.insertActor(nombre, age);//sino ponen nada ya se pone como ""
                break;


            case 4:
                System.out.println("Dime el nombre:");
                nombre = sc.next();

                databaseUsada.specificDeleteActor(nombre);
                break;


            case 5:
                databaseUsada.deleteTableDataActor();
                break;


            case 6:
                salir = true;
                break;

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
