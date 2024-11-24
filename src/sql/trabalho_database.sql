CREATE DATABASE projeto_tcc
GO
USE projeto_tcc
GO

CREATE TABLE Pessoa (

id			INT					NOT NULL		IDENTITY(1, 1),
senha		VARCHAR(50)			NOT NULL,
nome		VARCHAR(100)		NOT NULL		UNIQUE,
email		VARCHAR(100)		NOT NULL		UNIQUE,

PRIMARY KEY(id)

)
GO

CREATE TABLE Orientador (

pessoaId		INT				NOT NULL,
matricula		CHAR(13)		NOT NULL		CHECK(LEN(matricula) = 13) UNIQUE,

PRIMARY KEY(pessoaId),
FOREIGN KEY(pessoaId) REFERENCES Pessoa(id)
)
GO

CREATE TABLE Grupo (

id						INT					NOT NULL		IDENTITY(1, 1),
orientadorPessoaId		INT					NULL,
tema					VARCHAR(100)		NULL

PRIMARY KEY(id)
FOREIGN KEY(orientadorPessoaId) REFERENCES Orientador(pessoaId)

)
GO

CREATE TABLE Aluno (

pessoaId		INT				NOT NULL,
grupoId			INT				NULL,
ra				CHAR(13)		NOT NULL		CHECK(LEN(ra) = 13) UNIQUE,
turno			VARCHAR(50)		NOT NULL,
semestre		INT				NOT NULL		CHECK(LEN(semestre) = 1 AND semestre >= 5),
curso			VARCHAR(50)		NOT NULL

PRIMARY KEY(pessoaId)
FOREIGN KEY(pessoaId) REFERENCES Pessoa(id),
FOREIGN KEY(grupoId) REFERENCES Grupo(id)

)
GO

CREATE TABLE Area_Orientador (

id				INT					NOT NULL		IDENTITY(1, 1),
nome			VARCHAR(100)		NOT NULL,
descricao		VARCHAR(300)		NOT NULL

PRIMARY KEY(id)

)
GO

CREATE TABLE Associativa_OrientadorArea (

orientadorPessoaId		INT		NOT NULL,
area_OrientadorId		INT		NOT NULL

PRIMARY KEY(orientadorPessoaId, area_OrientadorId)
FOREIGN KEY(orientadorPessoaId) REFERENCES Orientador(Pessoaid),
FOREIGN KEY(area_OrientadorId) REFERENCES Area_Orientador(id)

)
GO
