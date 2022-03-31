package main.java.com.company;

import org.bson.types.ObjectId;

public class Actor {
    private String idSQL;
    private ObjectId idMongo;
    private String name_actor;
    private int age;


    public Actor(ObjectId idMongo, String name_actor, int age) {
        this.idMongo = idMongo;
        this.name_actor = name_actor;
        this.age = age;
    }

    public Actor(String idSQL, String name_actor, int age) {
        this.idSQL = idSQL;
        this.name_actor = name_actor;
        this.age = age;
    }


    public String getIdSQL() {
        return idSQL;
    }

    public void setIdSQL(String idSQL) {
        this.idSQL = idSQL;
    }

    public ObjectId getIdMongo() {
        return idMongo;
    }

    public void setIdMongo(ObjectId idMongo) {
        this.idMongo = idMongo;
    }

    public String getName_actor() {
        return name_actor;
    }

    public void setName_actor(String name_actor) {
        this.name_actor = name_actor;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toStringSQL() {
        return "Actor{" +
                "id='" + idSQL + '\'' +
                ", name_actor='" + name_actor + '\'' +
                ", age=" + age +
                '}';
    }

    public String toStringMongo() {
        return "Actor{" +
                "id=" + idMongo +
                ", name_actor='" + name_actor + '\'' +
                ", age=" + age +
                '}';
    }
}
