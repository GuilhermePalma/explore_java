#  MongoDB

[🍵 Voltar ao Conteudo NoSQL](README.md)

Esquema que Representa o **MongoDB**

```
MongoDB
|__ Database (Um MongoDB pode comportar varios Databases)
    |__ Collections (Um Databse pode ter diveras Collections)
        |__ Documents (JSON) (As Collections armazenam os JSONs)
```

- Banco de Dados NoSQL baseado em Documento, nesse caso o JSON
- Os `Documents` seriam as Linhas de um Banco de Dados Relacional
- Banco de Dados sem `Scheme`
    - É possivel Inserir dados sem que uma `Collection` exista
    - Um Documento pode ter varios e diferentes relacionamentos
    - Os dados não precisam ter os mesmos campos. É possivel que alguns dados inseridos em uma `collection` tenha
      mais/menos atributos que outros na mesma `collection`
- Quando começa a separar varios `documents` para realizar relacionamentos é importante verificar se não faria mais
  sentido usar um banco relacional
- Não Trabalha em SQL e sim numa API Propria
    - Consultas com relacionamentos são mais complicadas de serem feitas
- Se trata de um Banco de Dados `case sensitive`, isso significa haver diferença entre letras maiusculas e minusculas
- Quando um registro é inserido no Banco de Dados (`database`) é gerado um ID baseado em HASH
- Ex de Interação entre NoSQL e Banco de Dados Relacional: Um `document` (JSON) é utilizado para armazenar possiveis
  informações de um processo e depois quando se confirmar o processo, é enviado para o Banco de Dados Relacional
  persistir as Informações

#### Comandos Basicos

- `help`: Mostra a Interface de ajuda para os comandos
- `show`: Comando que Lista algo
    - `show dbs`: Exibe os Banco de Dados (`Database`)]
    - `show collections`: Exibe as `Collections` do Banco de Dados selecionado após o `use`
- `use databasename`
    - É possivel colocar o nome de um Banco de Dados que ainda não existe. Inicialmente ele não será criado, mas após
      criar uma `collection` cria-se o `database`
- `db.createCollection('nameCollection')`: Após executar o comando `use`, é possivel criar novas `collections`
  dentro do `database`
    - Caso crie uma `collection` com letra minusculas e outra com o mesmo nome mas usando letras maisuculas, haverá
      duas `collections` por causa do `case sensitive`
- `db.nameCollection.drop()`: Exclui a `collection` informada (localizada no `.nameCollection`)
    - Ao apagar todas as `collections` de um `database`, o `database` tambem será excluido. (Caso ainda esteja dentro da
      propriedade `use`, ele pode ser criado novamente ao criar uma `collection`)
- `db.nameCollection.insert()`: Insere um Objeto (JSON) na `Collection`
    - Caso a `Collection` ainda não exista, ao inserir um novo registro ela é criada
    - É possivel inserir dados nas linhas de baixo até se fechar o comando e objeto/lista
- `db.nameCollection.save()`: Responsavel por Inserir **OU** Atualiza um Registro
- Generalização de Consultas no MongoDB:
    - `db.nameCollection.find({filtrosAplicados}, {parametrosExibidos})`: Obtem todos os Itens com os Filtros Aplicados
    - `db.nameCollection.findOne({filtrosAplicados}, {parametrosExibidos})`: Obtem apenas o Primeiro Item com os Filtros
      Aplicados
    - Os Filtros Aplicados (Substituir no `filtrosAplicados`) são:
        - `{$or: [{keyOne: valueOne}, {keyTwo: valueTwo}]}`: Parametro Condicional **OU**, ou seja, os itens exibidos
          terão que ter **UM DOS DOIS** valores
        - `{$and: [{keyOne: valueOne}, {keyTwo: valueTwo}]}`: Parametro Condicional **E**, ou seja, os itens exibidos
          terão que ter **OS DOIS** valores
        - `key: {$exists: true}`: Busca os Itens que **POSSUEM** a Chave informada
        - `key: {$exists: false}`: Busca os Itens que **não POSSUEM** a Chave informada
    - Os Parametros (Pares Chave-Valor) que serão Exibidos (`parametrosExibidos`) são:
        - Passar os Parametros que **SERÃO** exibidos no formato: ``{key: 1}``
        - Passar os Parametros que **NÃO SERÃO** exibidos no formato: ``{key: 0}``
- Comandos `find`
    - `db.nameCollection.find()`: Exibe todos os Dados Inseridos na `collection`
        - `db.nameCollection.find({key: value})`: Busca todos os Itens com a Chave-Valor Informado
- Comandos `findOne`
    - `db.nameCollection.findOne()`: Obtem o Primeiro Registro (Já vem Formatado)
    - `db.nameCollection.findOne({key: value})`: Busca o Primeiro Item com a Chave-Valor Informado
- `SELECT` com parametros/filtros: Seria consultas com Operadores Condicionais (tipo um `IF`)
    - `db.nameCollection.find({$or: [{keyOne: valueOne}, {keyTwo: valueTwo}]})`: O Operador `$or` seria o **OU**,
      buscando itens que possuam **UM DOS VALORES** do chave-valor informado
    - `db.nameCollection.find({$and: [{keyOne: valueOne}, {keyTwo: valueTwo}]})`: O Operador `$and` seria o **E**,
      buscando itens que possuam **AMBOS** os valores do chave-valor informado
    - `db.nameCollection.find(key: {$exists: true})`: Busca os Itens que possuem a Chave informada
    - `db.nameCollection.find(key: {$exists: false})`: Busca os Itens que não possuem a Chave informada
    - Filtros dos Itens que serão Exibidos
    - `db.nameCollection.find{key: value}, {paramShow: 1, paramNotShow: 0})`: Realiza a busca definindo quais itens
      serão exibidos ou não
        - São Passados na segunda parte do `.find()`
        - Passar o valor `1` irá exibir os itens. Já se passar valor `0`, não será exibido o item
- Parametros que podem ser utilizados nos Metodos `.find()`/`.findOne()`
    - `.pretty()`: Exibe os Itens com a Formatação `pretty/beautiful` de um `JSON`
    - `.skip(valueOfSkip)`: Pula o número de registro informado (substituir o valor em `valueOfSkip` por um número)
    - `.limit(valeuOfLimit)`: Delimita a quantidade de resultados exibidos (substituir o valor em `valueOfLimit` por um
      número)
    - `.count()`: Retorna a quantidade de Dados da Consulta (Podem contar os Itens Totais ou dos Resultados de uma
      Pesquisa com Filtros)
- `db.nameCollection.remove({_id: hashID})`: Remove o Item/Documento de uma Collection com o `_id` informado
    - `db.nameCollection.remove({keu : {$param :value}})`: Exclui todos os Valores com o `value` informado

### Consultas Agregadas

- Pipes (Tubulações) and Filters (Filtros)
    - Os Filtors são responsavel por Transformar os Dados
    - Os Dados Transformados são passados e transformados por cada Filtro
- `db.nameCollection.aggregate()`
    - `db.nameCollection.aggregate({$match: {key: value}})`: Busca os Itens/Documentos com os `key` e `value` informados
        - Seria um Filtro +- como `Where`
    - `db.nameCollection.aggregate({$project: {key: 0}})`: Define quais Itens serão exibidos nos Resultados
        - Valor `0` não exibe os itens, já o `1` exibe
    - `db.nameCollection.aggregate({$addFields: {key: value}})`: Adiciona Campos no Resultado da Consulta
    - `db.nameCollection.aggregate({$sort: {key: value}})`: Ordena os Resultados em Ordem Crescente ou Decrescente
        - Caso o `value` seja `1`, a ordenção será por ordem Crescente. Mas se o `value` for `-1`, a ordenação será por
          ordem Decrescente
    - `db.nameCollection.aggregate({$unwind: {key: value}})`: "Desenrola"/Separa os Resultados em Itens/Documentos
- Quando se Manipula Dados dentro de Dados (Ex: `{key: {keyTwo: valueTwo}}`), é necessario colocar os campos entre aspas
    - Ex: `db.nameCollection.find({"key.keyTwo"})`
    - Quando quer se utilizar no lado do valor, é necessario adicionar o `$` antes
