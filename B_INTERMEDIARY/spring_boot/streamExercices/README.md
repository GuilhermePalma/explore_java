<h1 align="center">Stream Exercices</h1>
<h3 align="center">Um Projeto em Spring Boot para praticar a utilização e aplicações dos metodos da Stream API</h3>

---

[🫖 Voltar ao Conteudo Spring Boot](../README.md)

### Overview

Esse Projeto em Spring Boot, visa criar uma API utilizando Java e a estrutura comum de REST APIs e realizar
operações com dados salvos no banco de dados (`H2 Database`) usando os metodos da Stream API.

A Stream API se trata de uma biblioteca implementada no JDK 8 que traz metodos e padrões da programação funcional e
paralela, fazendo com que seja possivel realizar operações nas `collections` com uma maior performance, flexibilidade e
menos codigo escrito e imperativo

Esse repositorio utilizou os dados e exercicios criados no seguinte
Repositorio: [gavinklfong: Stream API Exercises](https://github.com/gavinklfong/stream-api-exercises). Para entender
melhor a estrutura das classes e dados, acesse o repositorio para visualizar o mapeamento do banco de dados e proposta
desse projeto

### Stream

A `Stream API` foi um conjunto de funcionalidades implementadas no Java JDK 8, que permite trabalhar no Java com
paradigmas da Programação Funcional. Além disso, traz operações que possam atuar de forma paralela, agregando uma maior
performance, menos codigo e mais facilidade para manipulação de grandes quantidades de dados.

Quando se fala de **Operações Paralelas**, significa que os itens serão processadas em um fluxo paralelo. Por exemplo:
Foi escrito um codigo em que o fluxo possui um `peek` seguido de um `map`. Isso significa que o primeiro item irá
realizar todo o fluxo (passar pelo `peek` e depois pelo `map`) para depois o item seguinte realizar as operações.

Dessa forma, para utilizar a `Stream API` é necessario que a `collection` que será utilizada implemente esse recurso.
Normalmente, as classes (ex: `List`, `Set`) possuirão dentro de si um metodo que retornará uma instancia da `Stream API`
baseada na classe e os seus valores.

Por fim, um ponto fundamental da `Stream API` (e uma caracteristica da **Programação Funcional**) é a imutabilidade dos
dados originais, isto é, será retornado uma nova `collection` ao inves de sobrescever os dados na `collection` original,
conservando os valores originais e retornando os novos valores transformados.

Principais funcionalidades:

- `Map`: Retorna a mesma quantidade de dados, mas com um valor transformado
- `Filter`: Retorna os dados correspondentes ao **filtro** aplicado
- `ForEach`: Executa uma operação (ou varias) para cada Item e não retorna nada
- `Peek`: Executa uma operação (ou varias) para cada Item retornando a coleção original
- `Count`: Retorna o número de Registros de uma `Collection`
- `Group`: Permite o agrupamento de dados de uma `Collection` baseado em uma condição

Para ver exemplos de operações utilizando o Stream, acessa a
classe [StreamExamples](src/main/java/com/guilhermepalma/streamexercices/StreamsExamples.java) e veja algumas operações
da Stream API com a collection `List`

### Exercicios

#### Produtos (Product)

- Exercicio 1: Obtenha uma Lista de `Product` com a Categoria `Books`  e Preço > 100
- Exercicio 3: Obtenha uma lista de `Product` com a Categoria `Toys` e aplique 10% de Desconto
- Exercicio 5: Obtenha o `Product` mais barato da categoria `Book`
- Exercicio 10: Obtenha os valores estaticos (ex: soma, media, maximo, minimo, total de elementos) para todos os
  produtos da
  categoria `Books`

#### Pedidos (Order)

- Exercicio 2: Obtenha uma Lista de `Order` em que os Produtos tenham a Categoria `Baby`
- Exercicio 4: Obtenha uma lista de `Product` encomendados pelo `Tier=2` entre `01-Feb-2021` e `01-Apr-2021`- Exercicio
  6: Obtenha as 3 `Order` mais Recentes
- Exercicio 7: Obtenha uma lista de `Order` do dia `15-Mar-2021`, Imprima os Valores no Console e devolva a Lista
  de `Product`
- Exercicio 8: Calcule o Valor Total de Todos os Pedidos em `Feb 2021`
- Exercicio 9: Calcule o valor medio pago nas `Order` de `15-Mar-2021`
- Exercicio 11: Obtenha um mapeamento de dados (`Map`) com o ID da `Order` e a quantidade (`count`) de `Product`
- Exercicio 12: Produza um mapeamento de dados (`Map`) com os registros das `Order` agrupados pelos `Customer`
- Exercicio 13: Produza um mapeamento de dados (`Map`) com o ID da `Order` e o valor total dos `Product`
- Exercicio 14: Obtenha um mapeamento de dados (`Map`) com uma Lista de nomes de `Product` por Categoria
- Exercicio 15: Obtenha o `Product` mais caro de cada Categoria
