# Example Axon Framework

### Anotações de Conceitos

- `Command`: Validam as Regras de Negócio, mas não alteram o Valor do Aggregate
    - Realizam a Logica de negócio antes liberar um Evento de Ocorrer
- `Event`: Alteram o Valor do `Aggregate`
    - Inserem, Exluem, Modificam dados Salvos no `Aggregate`
- `projections`
    - Permite definir como serão aramzenados os Dados

### Anotações Gerais

- Axon Server é iniciado em `http://localhost:8024`
- Para acessar os Dados Persistidos no Axon Framework, Ir em `Search` e no Campo `Query` inserir a Query de Consulta
    - Pode utilizar de um `AggregateIdentifier` para Buscar determinados Registros (
      ex: `aggregateIdentifier = "ef24a10b-ab6b-4882-9cf4-2e05ac46f080"`)

### Anotações da Codificação

- `@EventSourcingHandler`: Responsavel por "Escutar" (`Listener`) o Evento de Criação de uma Sala
    - Sempre que ocorrer uma Alteração, ele estará ciente
- `cosumes`: Propriedade utilizada nos `Mapping` dos Metodos HTTP que definem quais `media types` são **suportados**
  (ex: xml, json)
- `produces`: Propriedade utilizada nos `Mapping` dos Metodos HTTP que definem quais `media types` são **retornados**
  (ex:  xml, json)

#### Passo a Passo do Desenvolvimento

- Criar os `Packages`: `coreapi`, `commandModel`,`entity`, `restapi`, `query`
- Dentro de `coreapi`: Crie as Classes `Command` e `Event` necessarias
    - Normalemnte, sempre que se cria um `command`, irá criar um `event` junto
        - Anote essas Classes com o `@TargetAggregateIdentifier`: Define qual campo é o Identificador do Aggregate
    - Pode-se usar do **Kotlin** para a Geração das Classes de forma mais Rapida e com menos Codigo
    - Ex: `CreateRoomCommand` e `RoomCreatedEvent`
- Dentro de `commandModel`: Crie uma Classe que irá ser repsonsavel pelas Solicitações
    - Crie um Construtor Vazio
    - Anote a Classe com `@Aggregate`
    - Cria os Atributos da Classe
    - Marque o Atributo Identificador (ID) com o `@AggregateIdentifier`
    - Para os `command`:
        - Crie metodos que recebam um `command` como Parametro.
        - Anote os Metodos com `@CommandHandler`
        - Crie a Logica de Negócio e Validações
        - Use do `AggregateLifecycle.apply(command)` para executar a solicitação do Comando
    - Para os `event`:
        - Crie metodos que recebam um `event` como Parametro.
        - Anote os Metodos com `@EventSourcingHandler`
        - Aplique as modificações que os Objetos farão no `Aggregate`
- Dentro de `entity`: Faça o Mapeamento da Classe que será usada no Banco de Dados
    - Coloque os atributos que os itens que serão validados (ex: `@NotNull`)
- Dentro de `restapi`: Crie as Classes que Receberão as Requisições (Os **controllers**)
    - Crie a Classe `CommandController`: Responsavel pela Manipulação de Dados
        - Crie uma variavel `final` da Classe `CommandGateway` (AxonFramewok)
        - Crie um Construtor que receba a Variavel acima
        - Desenvolva metodos que recebam requisições REST
        - Retornos Assincronos
            - Marque o Retorno dos Metodos como `Future<String>`
            - Retorne o metodo `commandGateway.send(command)` do `CommandGateway` criado acima
- Dentro de `query`: Crie os `Packages` necessarios para Identificar à qual Parte a `Query` irá se referir
    - Cria uma Classe Responsavel pela Consulta
    - Marque-a com `@Entity` para realizar o Mapeamento no Banco de Dados
    - Crie os Atributos/Variaveis da Classe
    - Anote a Variavel Identificadora (ID) como `@Id`: A variavel se torna `primary-key` no Banco de Dados
    - Cria um Construtor Vazio (Requerido)
    - Crie Construtores/Getters/Setters necessarios
    - Crie uma `Interface` que herda Metodos de algum repositorio (Ex: `JpaRepository<Class, ID>`)
    - Volte no `package` `coreapi` e insira no arquivo as classes responsavel pela `Query`
    - Crie uma Classe com o final `Projection`
        - Anote a classe com `@Component`
        - Crie uma variavel `final` da Classe `QueryUpdateEmitter` e do Repositorio (Criado acima)
        - Crie um construtor que recebe essas duas variaveis `final`
        - Crie metodos que recebem as classes criada para `query` (Criado acima)
            - Esses Metodos são responsaveis por estarem "Escutando" chamadas da Classe `Query`
            - Marque os Metodos com `@QueryHandler`
            - Esses Metodos normalmente irão realizar consultas no Banco de Dados e Retornar Dados
        - Crie metodos que recebam `event`
            - Normalmente esses Metodos são responsaveis por presistirem eventos (`event`) gerados pelos `command`
            - Nesse momento será feito chamadas do `repository` para persistir os dados
            - Tambem é chamado o `updateEmitter.emit(class, eventSelect, instanceOfClasse)`
- Dentro de `restapi`: Crie as Classes que Receberão as Consultas (as **queries**)
    - Crie a Classe `CommandController`: Responsavel pela Manipulação de Dados
        - Crie uma variavel `final` da Classe `QueryGateway` (AxonFramewok)
        - Crie um Construtor que receba a Variavel acima
        - Desenvolva metodos que recebam requisições REST
        - Retornos Assincronos
            - Marque o Retorno dos Metodos como `Future<String>`
            - Retorne o
              metodo `queryGateway.query(classQuery, new MultipleInstancesResponseType<>(classReturned.class))`
              do `QueryGateway` criado acima