<h1 align="center">Stream Exercices</h1>
<h3 align="center">Um Projeto em Spring Boot para praticar a utilização e aplicações dos metodos da Stream API</h3>

---

### Overview

Esse Projeto em Spring Boot, tem como objetivo criar uma API utilizando Java e a estrutura comum de REST APIs e realizar
operações com dados salvos no banco de dados (`H2 Database`) usando os metodos da Stream API.

A Stream API se trata de uma biblioteca implementada no JDK 8 que traz metodos e padrões da programação funcional e
paralela, fazendo com que seja possivel realizar operações nas `collections` com uma maior performance, flexibilidade e
menos codigo escrito e imperativo

Esse repositorio utilizou os dados e exercicios criados no seguinte
Repositorio: [gavinklfong: Stream API Exercises](https://github.com/gavinklfong/stream-api-exercises). Para entender
melhor a estrutura das classes e dados, acesse o repositorio para visualizar o mapeamento do banco de dados e proposta
desse projeto

### Exercicios

#### Produtos

- Exercicio 1: Obtenha uma Lista de `Product` com a Categoria `Books`  e Preço > 100
- Exercicio 3: Obtenha uma lista de `Product` com a Categoria `Toys` e aplique 10% de Desconto
- Exercicio 5: Obtenha o `Product` mais barato da categoria `Book`
- Exercicio 10: Obtenha os valores estaticos (ex: soma, media, maximo, minimo, total de elementos) para todos os
  produtos da
  categoria `Books`

#### Clientes (Customer)

#### Pedidos (Order)

- Exercicio 2: Obtenha uma Lista de `Order` em que os Produtos tenham a Categoria `Baby`
- Exercicio 4: Obtenha uma lista de `Product` encomendados pelo `Tier=2` entre `01-Feb-2021` e `01-Apr-2021`- Exercicio 6: Obtenha as 3 `Order` mais Recentes
- Exercicio 7: Obtenha uma lista de `Order` do dia `15-Mar-2021`, Imprima os Valores no Console e devolva a Lista
  de `Product`
- Exercicio 8: Calcule o Valor Total de Todos os Pedidos em `Feb 2021`
- Exercicio 9: Calcule o valor medio pago nas `Order` de `14-Mar-2021`
- Exercicio 11: Obtenha um mapeamento de dados (`Map`) com o ID da `Order` e a quantidade (`count`) de `Product`
- Exercicio 12: Produza um mapeamento de dados (`Map`) com os registros das `Order` agrupados pelos `Customer`
- Exercicio 13: Produza um mapeamento de dados (`Map`) com o ID da `Order` e o valor total dos `Product`
- Exercicio 14: Obtenha um mapeamento de dados (`Map`) com uma Lista de nomes de `Product` por Categoria
- Exercicio 15: Obtenha o `Product` mais caro de cada Categoria
