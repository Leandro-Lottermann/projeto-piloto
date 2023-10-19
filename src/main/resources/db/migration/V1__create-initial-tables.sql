BEGIN TRANSACTION

	CREATE TABLE Processos(
		num_processo VARCHAR(255) PRIMARY KEY NOT NULL,
		status_processo VARCHAR(255)

	)

	CREATE TABLE Partes(
		id_parte BIGINT PRIMARY KEY NOT NULL IDENTITY(1,1),
		num_processo VARCHAR(255) FOREIGN KEY REFERENCES Processos(num_processo),
		nome VARCHAR(255),
		documento VARCHAR(255),
		email VARCHAR(255),
	)

	CREATE TABLE Enderecos_Partes(
		id_endereco BIGINT PRIMARY KEY NOT NULL IDENTITY(1,1),
		id_parte BIGINT FOREIGN KEY REFERENCES Partes(id_parte),
		cep VARCHAR,
		logradouro VARCHAR,
		complemento VARCHAR,
		bairro VARCHAR,
		cidade VARCHAR,
		uf VARCHAR,
		numero VARCHAR,
	)

	CREATE TABLE Notificacoes(
		id_notificacao BIGINT PRIMARY KEY NOT NULL IDENTITY(1,1),
		num_processo VARCHAR(255) FOREIGN KEY REFERENCES Processos(num_processo),
		id_parte BIGINT FOREIGN KEY REFERENCES Partes(id_parte),
		texto_notificacao VARCHAR(255),
		data_envio DATETIME,
		status_notificacao VARCHAR(255),
		forma_envio VARCHAR(255),
		status_envio VARCHAR(255)
	)

	CREATE TABLE Enderecos_Notificacoes(
		id_endereco BIGINT PRIMARY KEY NOT NULL IDENTITY(1,1),
		id_notificacao BIGINT FOREIGN KEY REFERENCES Notificacoes(id_notificacao),
		cep VARCHAR,
		logradouro VARCHAR,
		complemento VARCHAR,
		bairro VARCHAR,
		cidade VARCHAR,
		uf VARCHAR,
		numero VARCHAR,
	)
IF @@ERROR=0
COMMIT
ELSE
ROLLBACK