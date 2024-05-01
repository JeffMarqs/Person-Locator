## Person Locator

## Bem-vindo à documentação da API Person Locator. Esta API oferece recursos para cadastrar e consultar pessoas e seus endereços.

## Base URL
`localhost:8080/person-locator/`

## Endpoints
1. Busca Pessoa
 `GET /{id}`

Descrição: Retorna os detalhes de uma pessoa específica com o ID fornecido.

   **Exemplo de Request:**
   ```bash
   curl --location 'http://localhost:8080/person-locator/{id}'
```
Exemplo de Response:

 ```bash
{
    "id": 1,
    "fullName": "João Silva",
    "birthDate": "15/05/1990",
    "addresses": [
        {
            "id": 1,
            "street": "Rua das Flores",
            "zipCode": "12345-678",
            "number": "123",
            "city": "São Paulo",
            "state": "SP",
            "type": "MAIN"
        },
        {
            "id": 2,
            "street": "Avenida das Árvores",
            "zipCode": "54321-876",
            "number": "456",
            "city": "Rio de Janeiro",
            "state": "RJ",
            "type": "SECONDARY"
        }
    ]
}
```
Erros Exemplos:

```bash
{
    "status": 404,
    "message": "ID not found for Person: 5",
    "code": "404 NOT_FOUND",
    "error": "Id Not Found"
}
```
2. Busca lista de pessoas
 `GET /{id}`

   Descrição: Retorna os detalhes de uma lista de pessoas.
   
   **Exemplo de Request:**
   ```bash
   curl --location 'http://localhost:8080/person-locator'

Exemplo de Response:

 ```bash
[
    {
        "id": 1,
        "fullName": "João Silva",
        "birthDate": "15/05/1990",
        "addresses": [
            {
                "id": 1,
                "street": "Rua das Flores",
                "zipCode": "12345-678",
                "number": "123",
                "city": "São Paulo",
                "state": "SP",
                "type": "MAIN"
            },
            {
                "id": 2,
                "street": "Avenida das Árvores",
                "zipCode": "54321-876",
                "number": "456",
                "city": "Rio de Janeiro",
                "state": "RJ",
                "type": "SECONDARY"
            }
        ]
    },
    {
        "id": 2,
        "fullName": "Maria Santos",
        "birthDate": "20/10/1985",
        "addresses": [
            {
                "id": 3,
                "street": "Praça da Paz",
                "zipCode": "98765-432",
                "number": "789",
                "city": "Curitiba",
                "state": "PR",
                "type": "MAIN"
            }
        ]
    },
    {
        "id": 3,
        "fullName": "Pedro Almeida",
        "birthDate": "12/07/1988",
        "addresses": [
            {
                "id": 4,
                "street": "Rua do Comércio",
                "zipCode": "13579-246",
                "number": "321",
                "city": "Belo Horizonte",
                "state": "MG",
                "type": "MAIN"
            }
        ]
    }
]
```
3. Cadastra Pessoa
 `POST /create`

   Descrição: Este endpoint permite cadastrar uma nova pessoa. Os detalhes da pessoa devem ser enviados no corpo da requisição.
   
   **Exemplo de Request:**
   ```bash
   curl --location 'http://localhost:8080/person-locator/create' \
    --header 'Content-Type: application/json' \
    --data '{
    "fullName": "Ester Marques",
    "birthDate": "05/05/2002"
    }'

Exemplo de Response:

 ```bash
201 Created
```
Erros Exemplos:

```bash
{{
    "status": 400,
    "message": "To complete your registration, please enter your surname.",
    "code": "400 BAD_REQUEST",
    "error": "Incomplete Name Exception"
}
```
4. Atualiza Pessoa
 `PUT /{id}`

   Descrição: Este endpoint permite atualizar uma pessoa já existente no banco de dados. Os detalhes da pessoa devem ser enviados no corpo da requisição junto com o ID da     pessoa a ser atualizada, que deve ser fornecido no path da URL.

    **Exemplo de Request:**
   ```bash
   curl --location --request PUT 'http://localhost:8080/person-locator/1' \
    --header 'Content-Type: application/json' \
    --data '{
    "fullName": "João Marques",
    "birthDate": "15/05/1998"
    }'

Exemplo de Response:

 ```bash
200 Ok
```
Erros Exemplos:

```bash
{
    "status": 404,
    "message": "ID not found for Person: 5",
    "code": "404 NOT_FOUND",
    "error": "Id Not Found"
}
```

5. Busca Endereço
 `GET /address/{id}`

   Descrição: Retorna os detalhes de um endereço específico com o ID fornecido.

    **Exemplo de Request:**
   ```bash
   curl --location 'http://localhost:8080/person-locator/address/1'

Exemplo de Response:

 ```bash
{
    "id": 1,
    "personId": 1,
    "street": "Rua das Flores",
    "zipCode": "12345-678",
    "number": "123",
    "city": "São Paulo",
    "state": "SP",
    "type": "MAIN"
}
```
Erros Exemplos:

```bash
{
    "status": 404,
    "message": "ID not found for Address: 6",
    "code": "404 NOT_FOUND",
    "error": "Id Not Found"
}
```

6. Busca lista de endereços
 `GET /address`

   Descrição: Retorna os detalhes de uma lista de endereços.

    **Exemplo de Request:**
   ```bash
   curl --location 'http://localhost:8080/person-locator/address'

Exemplo de Response:

 ```bash
[
    {
        "id": 1,
        "personId": 1,
        "street": "Rua das Flores",
        "zipCode": "12345-678",
        "number": "123",
        "city": "São Paulo",
        "state": "SP",
        "type": "MAIN"
    },
    {
        "id": 2,
        "personId": 1,
        "street": "Avenida das Árvores",
        "zipCode": "54321-876",
        "number": "456",
        "city": "Rio de Janeiro",
        "state": "RJ",
        "type": "SECONDARY"
    },
    {
        "id": 3,
        "personId": 2,
        "street": "Praça da Paz",
        "zipCode": "98765-432",
        "number": "789",
        "city": "Curitiba",
        "state": "PR",
        "type": "MAIN"
    },
    {
        "id": 4,
        "personId": 3,
        "street": "Rua do Comércio",
        "zipCode": "13579-246",
        "number": "321",
        "city": "Belo Horizonte",
        "state": "MG",
        "type": "MAIN"
    }
]
```
7. Cadastra Endereço
 `POST /address/create`

   Descrição: Este endpoint permite cadastrar uma novo endereço. Os detalhes do endereço devem ser enviados no corpo da requisição.

    **Exemplo de Request:**
   ```bash
   curl --location 'http://localhost:8080/person-locator/address/create' \
   --header 'Content-Type: application/json' \
   --data '{
    "street": "Tv Regia",
    "zipCode": "13579-246",
    "number": "19",
    "city": "São Paulo",
    "state": "SP",
    "type": "Secondary",
    "personId": "0"
   }'

Exemplo de Response:

 ```bash
201 Created
```
Erros Exemplos:

```bash
{
    "status": 404,
    "message": "ID not found for Person: 0",
    "code": "404 NOT_FOUND",
    "error": "Id Not Found"
}
```

8. Atualiza Endereço
 `PUT /address/{id}`

   Descrição: Este endpoint permite atualizar um endereço já existente no banco de dados. Os detalhes do edenreço devem ser enviados no corpo da requisição junto com o ID do endereço a ser atualizada, que deve ser fornecido no path da URL.

    **Exemplo de Request:**
   ```bash
   curl --location --request PUT 'http://localhost:8080/person-locator/address/4' \
   --header 'Content-Type: application/json' \
   --data '{
    "street": "Av Parada Pinto",
    "zipCode": "13579-246",
    "number": "19",
    "city": "São Paulo",
    "state": "SP",
    "type": "Secondary",
    "personId": "2"
   }'

Exemplo de Response:

 ```bash
200 Ok
```
Erros Exemplos:

```bash
{
    "status": 404,
    "message": "ID not found for Person: 0",
    "code": "404 NOT_FOUND",
    "error": "Id Not Found"
}
```
   
## Considerações Finais

Este projeto utiliza o banco de dados H2 em memória para armazenar os dados das pessoas e seus endereços. O H2 é uma excelente escolha para desenvolvimento e testes, pois oferece facilidade de configuração e não requer um servidor de banco de dados separado.

Além disso, o gerenciamento das migrações do banco de dados é feito através do Flyway. O Flyway é uma ferramenta de controle de versão para bancos de dados que permite a aplicação automatizada de scripts de migração SQL conforme o esquema do banco de dados evolui ao longo do tempo.

Para mais informações sobre o H2, consulte [H2 Database](https://www.h2database.com/html/main.html).

Para mais informações sobre o Flyway, consulte [Flyway by Redgate](https://flywaydb.org/).

Para qualquer dúvida ou sugestão relacionada a este projeto, entre em contato em jeffer.son_dasilva@live.com.
