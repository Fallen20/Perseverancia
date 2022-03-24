CREATE TABLE movies (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     title CHAR(255) NOT NULL,
     synopsis text,
     PRIMARY KEY (id)
);
--si añades o cambias cosas aqui, has de poner estas ordenes
--docker-compose down
--docker volume rm persistenciademo_mysql (dentro de la carpeta del usuario)
--docker-compose up -d

