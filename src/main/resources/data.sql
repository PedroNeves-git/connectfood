------ TIPOS_USUARIOS --------
INSERT INTO TIPOS_USUARIOS(nome_tipo) VALUES
                                          ('Dono de Restaurante'),
                                          ('Cliente');

------ USUARIOS --------
INSERT INTO USUARIOS(nome, email, senha, endereco, data_ultima_alteracao, login, tipo_usuario_id) VALUES
    ('Pedro', 'pedro@gmail.com', '1234', 'avenida , 10 - osasco - sp', '2025-05-22 00:00:00', 'pedro123', 1);

------ RESTAURANTES --------
INSERT INTO RESTAURANTES(nome, endereco, tipo_cozinha, horario_funcionamento, dono_id) VALUES
    ('Restaurante do Pedro', 'Rua das Flores, 123', 'Brasileira', '10:00 - 22:00', 1);

------ ITENS_CARDAPIO --------
INSERT INTO ITENS_CARDAPIO(nome, descricao, preco, somente_local, caminho_foto, restaurante_id) VALUES
    ('Feijoada Completa', 'Feijoada com arroz, farofa e couve', 35.90, true, '/imagens/feijoada.jpg', 1);
