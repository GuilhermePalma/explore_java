
### Axon Framework

- Framework Java que permite implementar o padrão de arquitetura CQRS
    - Realiza a Implementação do CQRS através de Blocos de Construções: Agregados, Repositorios e Barramentos de Eventos
    - Fornece anotações para criar ``Objetos Agregados`` (Conjuntos de eventos que formam o Estado atual do Objeto)
    - Fornece anotações para criar ``Ouvintes de Eventos``
- Não tem como objetivo ocultar a arquitetura CQRS do Desenvolvedor, mas sim ajudar a garantir a entrega de eventos aos
  ouvintes de e fazer os processos na ordem correta
- Fornece uma Infraestrutura de Testes que segue as ideias ``BDD (Behaviour-Driver Developemnt)`` que foca os testes nos
  eventos publicados em reação ao envio de comandos
- Dependencias no **Maven**:
    - ``Axon AMQP``:
    - ``Axon Spring Bot Startet``: ``Libary`` do ``Axon`` que utiliza o sistema de Auto Configuration do Spring Bot para
      configurar os componentes basicos da infraestrutura, como o ``Barramento de Comando``, ``Barramento de Eventos`` e
      outros componentes necessarios para execução e armazenamento de ``Agregados``
- Os comandos possuem a intenção de realizar uma alteração e carregam com si os dados necessarios para realizar a mesma.
  Eles são registrados num barramento, podendo ser executado de forma sincrona ou assincrona
- Anotações do Axon Framework:
    - ``@TargetAggregateIdentifier``: Marca o **Identificador/ID** do Agregado que o comando busca alterar
        - ~~Utilizado na Classe do Comando~~
    - `@AggregateIdentifier`: **Identificador/ID** do Agregado
        - Axon usa esse metodo para buscar os eventos relacionados ao agregado e obter seu estado atual
    - `@Aggregate`: Anotação para Representar que a Classe é um Agregado
        - Agregado: Arvore Isolada de Entidades que consegue manipular comandos.
        - Quando um comando tem como destino um agregado, o Axon pega a instancia do Agregado e chama o metodo
          manipulador do comando relacionado
    - `@CommandHandler`: Cadastra um Metodo ou Construtor como Manipulador do Comando no Barramento de Comando Axon
        - Deve receber o mesmo parametro que será manipulado pelo Metodo/Cosntrutor
        - Usa-se no Construtor quando após o comando ser manipuado, resulte na criação de um Agregado
        - Voltado ao `Comand`
    - `@EventHandler`: Especifica que o Metodo é um Manipulador de Eventos. Deve receber como parametro o evento que
      será executado
    - `@EventSourcingHandler`: Marca um **Metodo** em um **Agregado** como Manipulador para Eventos gerados pelo
      Agregado
        - Usado para Ler todos os Eventos e Montar o estado atual de um Agreado
        - Voltado ao `Event`
- Classes e Comandos
    - `AggregateLifecycle.apply(event)`: Envia os eventos para o **Barramento de Eventos**
    - `CommandGateway.send(command)`: Usa do `Command Bus Axon` para enviar os `Commands` para os `Handlers`
      dos `Agregados`. Esse é um metodo **Assincrono**, isso significa que a que ira retornar um `CompletableFuture` (Um
      Evento Futuruo)
    - `CommandGateway.sendAndWait(command)`: Usa do `Command Bus Axon` para enviar os `Commands` para os `Handlers` dos
      Agregados. Esse é um metodo que retorna uma resposta **Sincrona** (Em Tempo de Execução)
    - `EventStore.readEvents(aggregateId)`: Retorna uma Visão Geral dos Evendos que foram gerados para um Agregado
- Observações Gerais
    - Para Aplicações que Gerenciam os **Comandos**:
        - Responsavel pelas alterações das Informações
        - As Classes necessarias: `Command`, `Event` e `Aggregate` (+ classes Padrões, como `Models`, `Controllers`)
    - Para Aplicações que Gerenciam as **Queries**
        - Responsabvel por consumir o Barramenton de Eventos e Criar uma Projeção em um Banco Relacional
        - As classes necessarias: `Repository`, `EventProcessor` (+ classes Padrões, como `Models`, `Controllers`)
    - Após um comando ser processadom ele pode resultar em vários eventos ou em um unico apenas
    - Seguir convenções de nomenclatura para Classes, Exemplos de Comandos(``NameOfClassCommand``, ``NameOfClassEvent``,
      ``NameOfClassAggregate``) e Exemplo de Queries( `NameOfClasseRepository` ), Exemplo de Model(``NameOfClasse``),
      Exemplo de DTO(`NameOfClasseDTO`), Exemplo de Controller(`NameOfClasseController`) . Normalmente, as classes são
      geradas com o mesmo nome, alterando apenas a Terminação
