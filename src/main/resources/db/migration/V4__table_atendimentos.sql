-- Atendimento
CREATE TABLE atendimentos (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    medico_id bigint NOT NULL,
    paciente_id bigint NOT NULL,
    data_hora timestamp NOT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;