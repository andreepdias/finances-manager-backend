# FinancesManager 

Aplicação de gerenciamento e controle de finanças pessoais desenvolvida para praticar conhecimentos de Angular e Spring Boot. O frontend pode ser encontrado no repositório https://github.com/andreepdias/finances-manager-frontend

O sistema pode ser acessado em https://andreepdias.github.io/finances-manager-frontend. A primeira requisição pode demorar devido ao fato da API ser executada em um plano gratuito do Heroku, que necessita iniciar a máquina virtual.

## Tecnologias utilizadas

A aplicação foi desenvolvida utilizando Spring Boot 2 e banco de dados relacionado SQL (MySQL). 

* Especificação JPA com Hibernate foi utilizada para a persistência dos dados; 
* Spring Security com OAuth 2 e token JWT para autenticação e autorização;
* Dependências como ModelMapper e Lombok para o desenvolvimento do projeto;
* Padrão de camadas (controller -> serivice -> repository).

## Modelagem das Entidades

#### User
Usuário do sistema.

#### Wallet
Abstração de uma entidade que gerencia capital (pode representar uma conta no banco, dinheiro, etc.).

#### Transaction
Uma transação que altera o capital de uma carteira.

#### Category
Possível categoria para uma transação, que pode assumir tipo de despesa ou renda.

### Diagrama de Relacionamentos de Entidades

![](https://i.imgur.com/N4VXlSu.png)
