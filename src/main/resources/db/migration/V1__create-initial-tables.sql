BEGIN TRANSACTION

	CREATE TABLE Processos(
		num_processo VARCHAR(255) PRIMARY KEY NOT NULL,
		status_processo VARCHAR(255)

	)

    CREATE TABLE Enderecos_Partes(
		id_endereco BIGINT PRIMARY KEY NOT NULL IDENTITY(1,1),
		cep VARCHAR(255),
		logradouro VARCHAR(255),
		complemento VARCHAR(255),
		bairro VARCHAR(255),
		cidade VARCHAR(255),
		uf VARCHAR(255),
		numero VARCHAR(255),
	)

	CREATE TABLE Partes(
		id_parte BIGINT PRIMARY KEY NOT NULL IDENTITY(1,1),
		id_endereco BIGINT FOREIGN KEY REFERENCES Enderecos_Partes(id_endereco),
		num_processo VARCHAR(255) FOREIGN KEY REFERENCES Processos(num_processo),
		nome VARCHAR(255),
		documento VARCHAR(255),
		email VARCHAR(255),

	)

	CREATE TABLE Enderecos_Notificacoes(
    		id_endereco BIGINT PRIMARY KEY NOT NULL IDENTITY(1,1),
    		cep VARCHAR(255) NOT NULL,
    		logradouro VARCHAR(255),
    		complemento VARCHAR(255),
    		bairro VARCHAR(255),
    		cidade VARCHAR(255),
    		uf VARCHAR(255),
    		numero VARCHAR(255) NOT NULL,
    	)

	CREATE TABLE Notificacoes(
		id_notificacao BIGINT PRIMARY KEY NOT NULL IDENTITY(1,1),
		id_parte BIGINT FOREIGN KEY REFERENCES Partes(id_parte),
		id_endereco BIGINT FOREIGN KEY REFERENCES Enderecos_Notificacoes(id_endereco),
		texto_notificacao VARCHAR(255),
		data_envio DATETIME,
		status_notificacao VARCHAR(255),
		forma_envio VARCHAR(255)
	)


IF @@ERROR=0
COMMIT
ELSE
ROLLBACK