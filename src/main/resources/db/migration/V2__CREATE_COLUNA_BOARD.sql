CREATE TABLE COLUNABORDS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    ordem int NOT NULL,
    tipo VARCHAR(7) NOT NULL,
    board_id BIGINT NOT NULL,
    CONSTRAINT boards_coluna_fk FOREIGN KEY (board_id) REFERENCES BOARDS(id) ON DELETE CASCADE,
    CONSTRAINT id_tipo_uk UNIQUE KEY unique_board_id_tipo (board_id, ordem)
); 