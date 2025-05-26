
-------USUARIOS---------
CREATE TABLE USUARIOS(
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255),
                         email VARCHAR(255),
                         senha VARCHAR(255),
                         endereco VARCHAR(255),
                        data_ultima_alteracao TIMESTAMP
);

insert into usuarios(nome, email, senha, endereco, data_ultima_alteracao) values
    ('Pedro', 'pedro@gmail.com', '1234', 'avenida , 10 - osasco - sp', '2025-05-22');
