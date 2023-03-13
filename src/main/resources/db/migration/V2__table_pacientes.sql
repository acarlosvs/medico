-- Paciente
CREATE TABLE pacientes (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    cpf varchar(255) NOT NULL,
    usuario_id bigint(20),
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;