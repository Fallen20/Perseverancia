CREATE TABLE movies (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     title CHAR(255) NOT NULL,
     synopsis text,
     PRIMARY KEY (id)
);
create table actors(
    actor_id MEDIUMINT NOT NULL AUTO_INCREMENT,
    name_actor CHAR(255) NOT NULL,
    age int,
    PRIMARY KEY (actor_id)
);
--si añades o cambias cosas aqui, has de poner estas ordenes
--docker-compose down
--docker volume rm perseverancia_mysql (dentro de la carpeta del usuario)
--docker-compose up -d

