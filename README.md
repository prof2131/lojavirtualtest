# Projeto Acadêmico de Testes Automatizados em Java

Este é um projeto **acadêmico** desenvolvido com o objetivo de ensinar boas práticas em **testes automatizados com Java**. Utilizando uma estrutura de **loja virtual simulada**, os alunos poderão aplicar testes em diferentes contextos, como autenticação, manipulação de dados, compras e muito mais.

## Objetivo

Capacitar alunos no desenvolvimento de testes utilizando o ecossistema Java, abordando:

- Testes de unidade
- Testes de API REST
- Testes de contrato
- Geração de relatórios de execução

---

## Tecnologias e Ferramentas Utilizadas

- **Java 17**
- **Maven**
- **JUnit 5** – testes unitários
- **Rest Assured** – testes de API REST
- **Allure Reports** – geração de relatórios
- **Testes de Contrato** – [especifique aqui a ferramenta utilizada, ex: Pact, Spring Cloud Contract]

---

## Estrutura da API Simulada (Loja Virtual)

O projeto está baseado em uma loja virtual, com as seguintes funcionalidades simuladas e respectivas rotas:

### 🔐 Login

- **Rota:** `/login`
- **Descrição:** Realiza autenticação de usuários.

### 👤 Usuários

- **Rota:** `/usuarios`
- **Descrição:** Cadastro, edição e listagem de usuários.

### 🛒 Produtos

- **Rota:** `/produtos`
- **Descrição:** Cadastro, consulta, edição e remoção de produtos.

### 📦 Carrinhos

- **Rota:** `/carrinhos`
- **Descrição:** Gerenciamento do carrinho de compras: adicionar itens, fechar pedido etc.

---

## Estrutura de Diretórios
```shell
 - src/
 - ├─ main/
 -   └─ java/ # Código da aplicação (simulada)
 - └─ test/
 -    └─ java/ # Pacotes de testes
 -      ├─ login/ # Testes relacionados ao login
 -      ├─ usuarios/ # Testes para a funcionalidade de usuários
 -      ├─ produtos/ # Testes da API de produtos
 -      └─ carrinhos/ # Testes da funcionalidade de carrinhos
```
