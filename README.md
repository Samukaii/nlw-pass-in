# Pass.in
![Spring boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

***Esta aplicação foi feita durante o evento NLW da Rocketseat***
***link do repositório original: https://github.com/rocketseat-education/nlw-unite-java***

O pass.in é uma aplicação de **gestão de participantes em eventos presenciais**.

A ferramenta permite que o organizador cadastre um evento e abra uma página pública de inscrição.

Os participantes inscritos podem emitir uma credencial para check-in no dia do evento.

## Requisitos

### Requisitos funcionais

- [x] O organizador deve poder cadastrar um novo evento;
- [x] O organizador deve poder visualizar dados de um evento;
- [x] O organizador deve poser visualizar a lista de participantes;
- [x] O participante deve poder se inscrever em um evento;
- [x] O participante deve poder visualizar seu crachá de inscrição;
- [x] O participante deve poder realizar check-in no evento;

### Regras de negócio

- [x] O participante só pode se inscrever em um evento uma única vez;
- [x] O participante só pode se inscrever em eventos com vagas disponíveis;
- [x] O participante só pode realizar check-in em um evento uma única vez;

## Documentação da API (Swagger)

Para documentação da API, acesse o link: https://nlw-unite-nodejs.onrender.com/docs

## Banco de dados

Nessa aplicação vamos utilizar banco de dados relacional (SQL). Para ambiente de desenvolvimento seguiremos com o Hyper Sql pela facilidade do ambiente.

### Diagrama ERD

<img src="./.github/erd.svg" width="600" alt="Diagrama ERD do banco de dados" />

### Estrutura do banco (SQL)

```sql
-- CreateTable
create TABLE events
(
    id                VARCHAR(255) NOT NULL PRIMARY KEY,
    title             VARCHAR(255) NOT NULL,
    details           VARCHAR(255) NOT NULL,
    slug              VARCHAR(255) NOT NULL,
    maximum_attendees INTEGER      NOT NULL
);

-- CreateTable
create TABLE attendees
(
    id         VARCHAR(255) NOT NULL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    event_id   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT attendees_event_id_fkey FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE RESTRICT ON UPDATE CASCADE
);


-- CreateTable
create TABLE check_ins
(
    id         INTEGER NOT NULL PRIMARY KEY IDENTITY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    attendee_id VARCHAR(255) NOT NULL,
    CONSTRAINT check_ins_attendee_id_fkey FOREIGN KEY (attendee_id) REFERENCES attendees
);



-- CreateIndex
CREATE UNIQUE INDEX events_slug_key ON events(slug);

-- CreateIndex
CREATE UNIQUE INDEX attendees_event_id_email_key ON attendees(event_id, email);

-- CreateIndex
CREATE UNIQUE INDEX check_ins_attendee_id_key ON check_ins(attendee_id);
```