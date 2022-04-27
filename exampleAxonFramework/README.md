# Example Axon Framework

### Anotações de Conceitos

- `Command`: Validam as Regras de Negócio, mas não alteram o Valor do Aggregate
    - Realizam a Logica de negócio antes liberar um Evento de Ocorrer
- `Event`: Alteram o Valor do `Aggregate`
    - Inserem, Exluem, Modificam dados Salvos no `Aggregate`

### Anotações da Codificação

- `@EventSourcingHandler`: Responsavel por "Escutar" (`Listener`) o Evento de Criação de uma Sala
    - Sempre que ocorrer uma Alteração, ele estará ciente

#### Passo a Passo do Desenvolvimento

- Criar os `Packages`: `coreapi`, `commandModel`,`entity`, `restapi`
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
    - Coloque os atributos que os itens possuirão no Banco de Dados (ex: `@NotNull`)
- Dentro de `restapi`: Crie as Classes que Receberão as Requisições (Os **controllers**)
    - Crie uma variavel `final` da Classe `CommandGateway` (AxonFramewok)
    - Crie um Construtor que receba a Variavel acima
    - Desenvolva metodos que recebam requisições REST
    - Retornos Assincronos
        - Marque o Retorno dos Metodos como `Future<String>`
        - Retorne o metodo `commandGateway.send(command)` do `CommandGateway` criado acima
    - Retornos Sincronos: **DESENVOLVERRRRRRRRRRRR**