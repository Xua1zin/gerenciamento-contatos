# Gerenciamento de Contatos API

Esta é uma API RESTful desenvolvida para gerenciar contatos, permitindo criar, atualizar, excluir, buscar e listar contatos. A API foi construída utilizando Java, Spring Boot e banco de dados PostgreSQL.

---

## Funcionalidades

- **Criar um novo contato**: Adiciona um novo contato com nome, celular e e-mail.
- **Atualizar contato existente**: Atualiza os dados de um contato já cadastrado.
- **Deletar contato**: Remove um contato do banco de dados pelo ID.
- **Buscar contato por ID**: Retorna os dados de um contato específico.
- **Listar todos os contatos**: Exibe todos os contatos cadastrados no sistema.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Hibernate (JPA)**

---

## Configuração do Ambiente

1. Certifique-se de ter o **Java 17** ou superior instalado.
2. Instale o **PostgreSQL** e configure um banco de dados para uso.
3. Configure as variáveis de ambiente no sistema:
   - `DB_URL`: URL do banco de dados (ex.: `jdbc:postgresql://localhost:5432/seu_banco`).
   - `DB_USERNAME`: Usuário do banco de dados.
   - `DB_PASSWORD`: Senha do banco de dados.

4. Altere o arquivo `application.properties` caso necessário:
   ```properties
   spring.datasource.url=${DB_URL}
   spring.datasource.username=${DB_USERNAME}
   spring.datasource.password=${DB_PASSWORD}
   spring.jpa.hibernate.ddl-auto=update
