
CREATE DATABASE IF NOT EXISTS controle_peso;


USE controle_peso;

CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    altura DECIMAL(3,2) NOT NULL,
    CONSTRAINT chk_altura CHECK (altura > 0)
);

CREATE TABLE IF NOT EXISTS peso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_peso CHECK (peso > 0),
    CONSTRAINT fk_peso_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario(id)
);
