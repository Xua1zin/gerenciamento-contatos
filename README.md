# Gerenciamento de Contatos API

## Descrição
Esta API é responsável pelo gerenciamento de contatos, permitindo realizar operações CRUD (Criar, Ler, Atualizar e Deletar). A aplicação foi desenvolvida utilizando Spring Boot e PostgreSQL como banco de dados relacional.

## Funcionalidades
- Criar um novo contato.
- Atualizar informações de um contato existente.
- Deletar um contato.
- Buscar um contato pelo ID.
- Listar todos os contatos cadastrados.

## Tecnologias Utilizadas
- Java 17
- Spring Boot
- PostgreSQL
- Hibernate

## Endpoints
### Criar um novo contato
**POST** `/contatos`
- Body (JSON):
  ```json
  {
      "nome": "Nome do Contato",
      "celular": "(99) 99999-9999",
      "email": "email@exemplo.com"
  }
  ```
- Resposta: 200 OK em caso de sucesso.

### Atualizar um contato existente
**PUT** `/contatos/{id}`
- Parâmetros:
   - `id` (Long): ID do contato a ser atualizado.
- Body (JSON):
  ```json
  {
      "nome": "Novo Nome",
      "celular": "(88) 88888-8888",
      "email": "novoemail@exemplo.com"
  }
  ```
- Resposta: 200 OK em caso de sucesso.

### Deletar um contato
**DELETE** `/contatos/{id}`
- Parâmetros:
   - `id` (Long): ID do contato a ser deletado.
- Resposta: 200 OK em caso de sucesso.

### Buscar um contato pelo ID
**GET** `/contatos/{id}`
- Parâmetros:
   - `id` (Long): ID do contato a ser buscado.
- Resposta: 200 OK com os dados do contato em caso de sucesso.

### Listar todos os contatos
**GET** `/contatos`
- Resposta: 200 OK com a lista de todos os contatos.

## Configuração
### Banco de Dados
A aplicação utiliza o PostgreSQL. Certifique-se de configurar as variáveis de ambiente antes de executar a aplicação:
- `DB_URL`: URL de conexão com o banco de dados.
- `DB_USERNAME`: Usuário do banco de dados.
- `DB_PASSWORD`: Senha do banco de dados.

### Configuração de Perfil
Por padrão, a aplicação utiliza o perfil de teste (`test`). Para alterar, edite o valor da propriedade `spring.profiles.active` no arquivo `application.properties`.

## Como Executar
1. Clone o repositório:
   ```
   git clone <URL_DO_REPOSITORIO>
   ```

2. Configure as variáveis de ambiente para o banco de dados:
   ```
   export DB_URL=jdbc:postgresql://localhost:5432/nome_do_banco
   export DB_USERNAME=usuario
   export DB_PASSWORD=senha
   ```

3. Execute a aplicação com o Maven:
   ```
   mvn spring-boot:run
   ```

4. Acesse a API na URL padrão: `http://localhost:8080`.

## Observações
- O schema do banco de dados será gerado automaticamente ao iniciar a aplicação (conforme configurado em `spring.jpa.hibernate.ddl-auto`).
- Para visualizar as queries executadas no banco de dados, habilite `spring.jpa.show-sql=true` no arquivo `application.properties`.

## Autor
API desenvolvida por João.
