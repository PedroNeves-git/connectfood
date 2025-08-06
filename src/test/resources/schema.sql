DROP TABLE IF EXISTS itens_cardapio;
DROP TABLE IF EXISTS restaurantes;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS tipos_usuarios;

------ TIPOS_USUARIOS --------
CREATE TABLE tipos_usuarios (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                nome_tipo VARCHAR(100) NOT NULL UNIQUE
);

------ USUARIOS --------
CREATE TABLE usuarios(
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255),
                         email VARCHAR(255),
                         senha VARCHAR(255),
                         endereco VARCHAR(255),
                         data_ultima_alteracao TIMESTAMP,
                         login VARCHAR(255),
                         tipo_usuario_id BIGINT,
                         FOREIGN KEY (tipo_usuario_id) REFERENCES tipos_usuarios(id) ON DELETE CASCADE
);

------ RESTAURANTES --------
CREATE TABLE restaurantes (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              nome VARCHAR(255) NOT NULL,
                              endereco VARCHAR(255),
                              tipo_cozinha VARCHAR(100),
                              horario_funcionamento VARCHAR(100),
                              dono_id BIGINT,
                              FOREIGN KEY (dono_id) REFERENCES usuarios(id) ON DELETE CASCADE
);


------ ITENS_CARDAPIO --------
CREATE TABLE itens_cardapio (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                nome VARCHAR(255),
                                descricao TEXT,
                                preco DECIMAL(10,2),
                                somente_local BOOLEAN,
                                caminho_foto VARCHAR(255),
                                restaurante_id BIGINT,
                                FOREIGN KEY (restaurante_id) REFERENCES restaurantes(id) ON DELETE CASCADE
);
