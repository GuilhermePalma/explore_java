# Conteudo de Bancos NoSQL

### Conteudos

- [NoSQL - Banco de Dados: MongoDB](no_sql_mongodb.md)

### Bancos No SQL

- NoSQL: Not Only SQL
- Subdivididos em 4:
    - Chave-Valor (Ex: Redis (Muito usado para Cache, Sessões))
        - Possui uma Chave que é atribuida um Valor (`key: value`)
    - Documento (Ex: MongoDB)
        - Baseado em algum tipo de documento, como JSON
    - Grafo (Ex: Neo4j (Organizar Dados como Grafo))
        - Grafos são os nós e possuem vertices/conexões com outros nós
        - Ex: Redes Sociais
    - Column (Ex: Cloud BigTable e Cassandra)
        - Espaço de Chaves, semelhantes ao Banco Relacional
        - Familias de Colunas podem possuir familias Diferentes

|               Escalonamento Vertical                |              Escalonamento Horizontal               |
|:---------------------------------------------------:|:---------------------------------------------------:|
|   Possibilidade de aumentar elementos do Hardware   |   Permite Separa os Dados em Maquinas Diferentes    |
| A Maquina só pode ser aumentada até um certo limite |           Multiplas Maquinas Funcionando            |
|            Ex: Aumentar Memoria, HD, SSD            | Mais utilizado quando irá sofrer muitas requisições |

---

[🍵 Voltar ao Conteudo Iniciante](./README.md)