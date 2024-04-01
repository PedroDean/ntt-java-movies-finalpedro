-- Criação da tabela de junção entre MovieModel e DirectorModel
CREATE TABLE movie_director (
                                movie_id BIGINT NOT NULL,
                                director_id BIGINT NOT NULL,
                                PRIMARY KEY (movie_id, director_id),
                                FOREIGN KEY (movie_id) REFERENCES tb_movie(id),
                                FOREIGN KEY (director_id) REFERENCES tb_director(id)
);

-- Inserindo gêneros
INSERT INTO tb_genre (genre_name) VALUES ('Ação');
INSERT INTO tb_genre (genre_name) VALUES ('Comédia');
INSERT INTO tb_genre (genre_name) VALUES ('Drama');

-- Inserindo estúdios
INSERT INTO tb_studio (name, country) VALUES ('Warner Bros.', 'Estados Unidos');
INSERT INTO tb_studio (name, country) VALUES ('Universal Pictures', 'Estados Unidos');
INSERT INTO tb_studio (name, country) VALUES ('Paramount Pictures', 'Estados Unidos');

-- Inserindo franquias
INSERT INTO tb_franchise (name, genre_id, studio_id) VALUES ('Marvel Cinematic Universe', 1, 1);
INSERT INTO tb_franchise (name, genre_id, studio_id) VALUES ('Harry Potter', 3, 2);
INSERT INTO tb_franchise (name, genre_id, studio_id) VALUES ('Star Wars', 1, 3);

-- Inserindo serviços de streaming
INSERT INTO tb_streaming (name, url) VALUES ('Netflix', 'https://www.netflix.com');
INSERT INTO tb_streaming (name, url) VALUES ('Amazon Prime Video', 'https://www.primevideo.com');
INSERT INTO tb_streaming (name, url) VALUES ('Disney+', 'https://www.disneyplus.com');

-- Inserindo diretores
INSERT INTO tb_director (name) VALUES ('Christopher Nolan');
INSERT INTO tb_director (name) VALUES ('Quentin Tarantino');
INSERT INTO tb_director (name) VALUES ('Steven Spielberg');

-- Inserindo filmes
INSERT INTO tb_movie (title, genre_id, studio_id) VALUES ('Inception', 1, 1);
INSERT INTO tb_movie (title, genre_id, studio_id) VALUES ('Pulp Fiction', 2, 2);
INSERT INTO tb_movie (title, genre_id, studio_id) VALUES ('The Irishman', 3, 3);

-- Inserindo usuários
INSERT INTO tb_user (name, email, password) VALUES ('João', 'joao@example.com', 'senha123');
INSERT INTO tb_user (name, email, password) VALUES ('Maria', 'maria@example.com', 'senha456');
INSERT INTO tb_user (name, email, password) VALUES ('Pedro', 'pedro@example.com', 'senha789');

-- Associando filmes aos diretores
INSERT INTO movie_director (movie_id, director_id) VALUES (1, 1); -- Inception, Christopher Nolan
INSERT INTO movie_director (movie_id, director_id) VALUES (2, 2); -- Pulp Fiction, Quentin Tarantino
INSERT INTO movie_director (movie_id, director_id) VALUES (3, 3); -- The Irishman, Steven Spielberg;

-- Inserindo atores
INSERT INTO tb_actor (name) VALUES ('Leonardo DiCaprio');
INSERT INTO tb_actor (name) VALUES ('Brad Pitt');
INSERT INTO tb_actor (name) VALUES ('Tom Hanks');

INSERT INTO tb_role (NAME) VALUES('STUDIO_SELECT');
INSERT INTO tb_role (NAME) VALUES('STUDIO_SAVE');
INSERT INTO tb_role (NAME) VALUES('STUDIO_UPDATE');
INSERT INTO tb_role (NAME) VALUES('STUDIO_DELETE');
