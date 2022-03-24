package main.java.com.company;

public class Pelicula {
    private int id;
    private String title;
    private String synopsis;

    public Pelicula(int id, String title, String synopsis) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
