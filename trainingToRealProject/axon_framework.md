# Axon Framework, CQRS e Event Sourcing

- Objetivos 19/04:
    - Introdução ao Axon Framework
        - Instalação
        - Funcionamento
        - Comandos Gerais
    - Introdução ao CQRS
        - Definição
        - Beneficios
        - Implementação
    - Introdução ao Event Sourcing

---

### Disposções Gerais

Esse Projeto contem as anotações pessoais realizadas durante o estudo de **CQRS**, **Axon Framework** e **Event
Sourcing**. Esse Projeto não foi escrito por um especialista ou conhecedor do conteudo, mas sim por um estudante. Por
conta disso, inicialmente consulte a documentação das Ferramentas e Recursos para ter uma base e depois busque esse
conteudo

Caso encontre alguma inconformidade ou erro, inicie uma Issues para que que seja corrigido e disponibilizado a versão
correta.

- Legenda
    - Textos ~~Tachados~~: Representam informações da interpretação propria do escritor que não se tem certeza
    - Textos ```code```: Representa Trechos descrevendo ou contendo codigo

### Event Sourcing

- Garante com que todas as alterações sejam armazenadas numa sequência de eventos, mantendo um Historico de Eventos
- Armazenar o Estado de um Aplicativo por meio do Historico de Eventos Passados
    - O Estado Atual será reconstruindo no Historico Completo de Eventos
    - Os Eventos são a unica fonte verdadeira do que aconteceu na Aplicação
    - O Estado Atual tambem é chamado de `Materialized State`
    - Os eventos não podem ser modificados, já que eles representam algo que aconteceu
    - Ex: O Carrinho de Compras de um Comercio é acomapanhado desde a seleçãoed um um ou varios produtos até a compra
      final, mapeando os possiveis itens uqe foram tirados do carrinho
- Permite executar Projeções do Estado atual da Aplicação, consultar eventos para gerar uma Projeção Passada ou uma
  Auditoria
- Para reconstruir o Estado Materializado é necessario realizar repretições dos eventos
    - Os eventos serão entregues aos manipuladores de eventos que realizam o procesamento
    - Ao recriar os eventos é necessario verificar se certos eventos ao serem executados novamente irão gerar novos.
      Nesse caso é necessario desabilitar o replay desses eventos para evitar problemas
        - Ex: Recriar o processo de Compra pode ocorrer uma nova cobrança no evento de pagamento, por isso é necessario
          desabilitar
- `Snapshot`: Carregar todo o historico de eventos de um `Agregado` pode gerar um processo muito longo, com isso pode
  ser tirado Snapshot que irá representar o Estado Atual do Agregado
    - A criação do Snapshot é um processo assuncrone
    - No `Axon Framework` é necessario configurar quando esses Snapshot serão gerados
- `Upcaster` (Transmissão): São usados para implementar atualizações em um `esquema/model`
    - Responsavel por pegar os eventos usando o esquema antigo e então usar o novo modelo para atualizar as informações
    - Podem ser aplicados mais de um `Upcaster` na repetição de Evenetos
    - Pode ser usado para combinar varios eventos em um unico evento e vice-versa
    - Para manter uma segurança entre os Atributos Antigos e Novos:
        - Não remover campos e sim marca-los como `deprected`
        - Fornecer valor padrão a novos campos
        - Não alterar o Tipo do evento e sim apresentar um Novo Evento
- Segurança
    - Os eventos são imutaveis e precisam sempre estar disponiveis
    - Regulamentações que peçam para que os dados sejam excluidos vão contra o `Event SOurcing`
    - `Cryptographic Erasure`: Usa algoritimos de criptografia para Criptografar dados
        - As chaves são armazenadas junto do armazenamento de eventos
        - As informações são descriptografadas quando é realizado uma repetição de eventos
        - Quando as chaves são excluidas, os dados criptografados por essa chave se tornam inacessivel
- Considerações Finais
    - Faz com que a aplicação se torne um pouco mais complexa, tanto na implantação como na manutenção
    - Permite com que o software seja adaptavel
    - Importante definir quais eventos serão armazenados
    - É possivel reproduzir a sequência de eventos e encontrar onde ocorreu uma falha de algum processo por meio do
      historico de eventos

### DDD (Domain-Driven Design)

- `Domain`:
    - Definições de `Dominio`:
        - Conceito dentrode um "Espaço Problema"
        - Area de assunto que um Usuario usa um Programa
        - Esgera de Conhecimento, Influencia e Atividade
    - Podem ter diferentes areas de conhecimentos
- `Subdomains`
    - Podem ser mmais complexos em que cada Subdomminio representa uma parte diferente do neogcio
    - Para identificar o Subdominio é necessario entender o nogocio, a sua estrutura organizacional e areas de
      especialização
- Os `Domain` e `Subdomain` são a parte mais importante do negócio, responsavel por agregar valor (e dinheiro)
- `Domain Model`: Um sistema de abstrações que descreve os apectos selecionados de um dominio, em que é utilizado para
  resolver problemas relacionados a ele
    - `Software` --> Representa uma Parte do --> `Domain Model` (Comunicação das Pessoas e Empresas) --> Representa o
      que pensamos e falamos Sobre --> `Domain`
    - Responsavel ppor capturar o que é importante e util para a resolução de um problema
        - > Importante lembrar que o Software surge para solucionar problemas
- `Axon Framework`
    - Fornece um Modelo de Programação para criar blocos de um `Domain Model`:
        - `Aggregate`: Entidade ou Grupo de Entidades que mantidas num estado consistente. Se torna um bloco de
          construção principal para implementar o `Domain Model` baseado no `Axon`
            - Aceitam comandos de negócios que produz um evento relacionado
            - Em aplicacações sequindo o [CQRS](#cqrs) o `Aggregate` é presente no `Command Model`, pois é onde a
              Mudança é Iniciada. Mas tambem é possivel contruir `Qury/Projection Model`, sendo elas mais diretase as
              invariaveos de estado menos rigidas
        - `Aggregate Root`: Entidade dentro do `Aggregate` responsavel por manter um Estado Consistente
        - `Entity`
        - `Value Object`: É um tipo imutavel que se diferencia apenas pelos estados das propriedades, que devem sempre
          contar a história do problema que está sendo resolvendo
        - `Domain Event`
        - Uma lógica pode demandar um Agregado com mais do que apenas uma Raiz. Com isso a complexidade deve ser
          atribuido a varias `Entities (Aggregate Members)`
    - Faz Explicitamente a Separação entre a Lógica de negócios e a Configuração da Infraestrutura em que a Lógica será
      executada
        - `AxonServer`: Componente de Infraestrutura que cuida do Armazenamento/Roteadmento dos Eventos e tambem dos
          Tipos de Mensagens (`Command` e `Quey`) de forma Otimizada e Confiavel
        - Permite uma maior flexibiliadade e configurações (Ex: `RDBMS`, `NoSQL`, `AMQP`, etc)
        - Permite que o programador se concentre em obter a implementação correta da lógica de dominio
- `Bounded Context` (Contexto Limitidao)
    - A Linguagem Ambigua como o `Domain Model` (Ou `Subdomain Model`) precisa ser desenvolvida dentro de
      um `Bounded Context` explicitamente delimitado. Para isso existe algumas regras para `Models` e `Context`:
        - Definir o `context` que o `model` se aplica
        - Definir explicitamente limites (da organização da equipe), uso em partes do aplicativo e manifestações fisicas
          (bases de codigo e esquemas de banco de dados)
        - Manter os `models` consistente e dentro dos limites
        - Manter um modelo de `subdomain` por um `bounded context`
    - O `Axon Framework` aceita `bounded Context` de maneiras diferentes
        - Perspectiva do `Framework`: Separa a Logica de Negocio das Configurações. A logica se concentra no seu proprio
          contexto e utiliza das configurações do Sistema
        - Perspectiva do `AxonServer`: Suporta contextos limitados, permiitndo com que diferentes aplicativos se
          conectem a diferentes contextos dentro do `AxonServer`
    - Mapemaneto do `Context`: Um `bounded context` não vive por conta propria, ele precisa dos diferentes contextos
      para ser sincronizados
        - `Partnership`: Dois Contextos/Equipes se Combinam para uma Interação
        - `Customer-supplier`: Dois contextos no relacionamento Upstream/Downstream. Upstream pode ter sucesso
          independente do contexto Downstream
        - `Conformist`: Dois contextos no relacionamento Upstream/Downstream. ~~Upstream não tem sucesso se o contexto
          Downstream tambem não tiver~~
        - `Shared Kernel`: Compartilha uma parte do `model`
        - `Separate Ways`
        - `Anti-Corruption Layer`: o Contexto Downstream cria uma camada para evitar que o upstream "vaze" do seu
          proprio modelo
    - Alguns eventos só são relevantes no contexto da sua publicação
- Quanto mais amplo o escopo de um evento, mais componentes acabam se acoplando à origem

### CQRS

- Command Query Resposability Segragation (CQRS): Segregação da Responsabilidade de Consulta de Comando
    - Foca em Separar as Alterações (Comandos) e as Consultas. Essa segregação é feito em Objeto diferentes, em
      processos separados e até mesmo em Hardware separados. Esse esquema prove uma maior escalabilidade e desempenho ao
      Sistema
    - Conforme a Interação do Usuario com a ``UI`` pode fazer com que se execute opreaçãoes de Leituras ou Escrita de
      Dados
    - Por mais que os ``Models`` de **Consulta** e **Leitura** sejam separados, eles possuem uma relação durente alguns
      processos
        - Ex: Ao alterar um Registro, é feito o processo pelo ``Command Model``, mas depois o ``Query Model`` é chamado
          para trazer os dados alterados para a ``UI`` permitindo com que o usuario visualize
    - Incialmente é Feita uma intereção com a ``UI``, que resulta numa requisição de Serviço (``Service Interface``)
      que interaje entre um dos dois modelos, o ``Query Model`` e o ``Command Model``. Ambos os modelos estarão
      compartilhando uma mesma Base de Codigo (``Database``) ou tambem uma Bases de Dados separadas, que se comunicam
      pelo [Event Sourcing](#event-sourcing)
    - ``Command Model``: Uma Solicitação/Requisição que possui dados para realizar uma alteração no Estado de uma
      Entidade no Banco de Dados
        - Indica a intenção de Mudar um Dado na Base de Dados (``Database``), como Inserir, Excluir, Alterar
        - Ex: Criar, Alterar, Comprar, Finalizar um Procedimento
    - ``Query Model``: Indica a requisição de uma consulta de Dados/Informações, não alterando nada na Base de
      Dados (``Database``)
        - Os ``Service Interface`` alteram dados da apresentação por meio do ``Query Model``
    - Fazendo um Compartativo, no cotidiano são feitos mais comandos do que queries
        - Ex: Consulta varios Produtos para que no final compre apenas um
- Ao implementar um CQRS, por padrão o Projeto já se torna um Microserviço
- Utilizado durante a Implementação de Projetos usando os conceitos ``DDD`` (Domain-Drive Design)

- ``Events``: Indica que algo está acontecendo e Pode ser Interessante aos Demais Componentes

#### Segragação entre Command e Query

- ``Command Gateway``: Obtem o Command Handler
    - ``Command Handler``: Especifica o que irá acontecer após emitir o ``Command``
        - Ex: Insere um novo dado no Banco de Dados
- ``Query Gateway``: Utilizado quando se deseja consultar algo
    - ``Query Gateway`` --> ``Query Handler`` --> ``Repository``
    - A Solicitação chega pelo ``Query Gateway``, é direcionado ao ``Query Handler`` que consulta o ``Repository``
      buscando os Dados
    - Ao encontrar os Dados, retorna ao Query Handler que retorna um ``Materialized View`` ao Usuario
        - ``Materialized View`` contem os Dados obtidos do Banco de Dados

#### Segregação do Database

- ``Write Database``: Utilizado ao realizar alteração (Ex: inserção, exclusão, atualização) no Banco de Dados
    - APÓS a alteração ser concluida, é gerado um evento, que o ``Event Handler`` captura a alteração realizada e
      atualiza/sincroniza o ``Read Database``
    - Após a Sincronização, é gerado eventos que informam que houve alterações e solicitando que os modelos e Banco de
      Dados sejam atualizados
- ``Read Database``: Banco de Dados utilizado para Leitura
- Ambos os Bancos precisam sempre estarem sincronizados para não ocorrer divergencias

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

### Referencias

- [Explanation CQRS and Event Sourcing - ENG](https://www.youtube.com/watch?v=BEYF1x48npw)
- [Padrão CQRS - PT BR](https://www.infoq.com/br/news/2011/11/cqrs/)
- [Aplicando Axon Framework em Projetos Java _PT BT](https://coderef.com.br/cqrs-e-event-sourcing-com-axon-framework-e-spring-boot-7bd83093782d)
