package main.java.com.company;

import org.bson.types.ObjectId;

public class Pelicula {
    private String idSQL;
    private ObjectId idMongo;
    private String title;
    private String synopsis;

    public Pelicula(ObjectId idMongo, String title, String synopsis) {
        this.idMongo = idMongo;
        this.title = title;
        this.synopsis = synopsis;
    }

    public Pelicula(String idSQL, String title, String synopsis) {
        this.idSQL = idSQL;
        this.title = title;
        this.synopsis = synopsis;
    }

    public ObjectId getIdMongo() {
        return idMongo;
    }

    public void setIdMongo(ObjectId idMongo) {
        this.idMongo = idMongo;
    }

    public String getIdSQL() {
        return idSQL;
    }

    public void setIdSQL(String idSQL) {
        this.idSQL = idSQL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }


    public String toStringSQL() {
        return "Pelicula{" +
                "id=" + idSQL +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }

    public String toStringMongo() {
        return "Pelicula{" +
                "id=" + idMongo +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}
