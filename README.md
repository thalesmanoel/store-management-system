# Sistema de Gerenciamento de Loja

## Sobre o projeto
Uma aplicação web full-stack que permite aos usuários visualizar, criar, editar, atualizar e excluir, clientes, vendedores e produtos, além de poder realizar compras e visualizar o histórico de vendas. Desenvolvido com Angular no frontend e Java com Spring Boot no backend.

## Funcionalidades:
### Operações CRUD:
- **Gerenciamento de clientes**
- **Gerenciamento de vendedores**
- **Gerenciamento de produtos.** *Obs: Um produto associado a uma venda não pode ser excluído. Isso é uma medida de integridade de dados para garantir que os registros de vendas mantenham consistência e histórico completo.*

### Operações de vendas:
- **Registro de compras e vendas:** ao entrar na área de realizar uma compra, o usuário pode escolher um produto cadastrado na plataforma para comprar e a quantidade de cada produto que deseja. A plataforma irá calcular o subtotal de cada produto, e o total geral de sua compra.
- **Atualização de estoque:** Ao efetuar uma compra, a quantidade comprada de cada produto é automaticamente descontada do estoque. O sistema mantém o controle do estoque em tempo real com base nas vendas registradas.

## Tecnologias utilizadas:
### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL

### Frontend
- Angular 17
- TypeScript
- HTML
- SCSS

### DevOps
- Docker

## Como executar o projeto:
### Pré-requisitos:
- Docker instalado

### Passos para a execução:
1.**Clone o repositório com esse comando:**
`` git clone https://github.com/thalesmanoel/store-management-system``






