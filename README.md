# FinancesManager 

Aplicação de gerenciamento de treinos para academia e planejador de refeições com contagem de macro nutrientes (calorias, carboidratos, proteínas e gorduras).

Esta aplicação foi desenvolvida para praticar conhecimentos de Angular e Spring Boot. O frontend pode ser encontrado em: https://github.com/andreepdias/fitness-manager-frontend

O sistema está implantado no GitHub Pages e pode ser acessado por: https://andreepdias.github.io/fitness-manager-frontend. OBS.: O backend está hospeado em plano GRATUITO no Heroku, portanto a primeira requisição pode demorar (até que a máquina virtural do Heroku seja iniciada).

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
