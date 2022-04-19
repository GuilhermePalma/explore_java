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

### CQRS

- Command Query Resposability Segragation (CQRS): Segregação da Responsabilidade de Consulta de Comando
    - Foca em Separar os Comandos e as Consultas, dando mais escalabilidade e desempenho ao Sistema
    - ``Comando``: Uma Solicitação/Requisição que altera o Estado de uma Entidade no Banco de Dados
        - Ex: Criar, Alterar, Comprar, Finalizar um Procedimento
    - ``Query``: Seria uma consulta de Dados, não alterando nada no Banco de Dados.
    - Fazendo um Compartativo, no cotidiano são feitos mais comandos do que queries
        - Ex: Consulta varios Produtos para que no final compre apenas um
- Ao implementar um CQRS, por padrão o Projeto já se torna um Microserviço


- ``Commands``: Indica a intenção de Mudar um Dado no Sistema (Inserir, Excluir, Alterar)
- ``Events``: Indica que algo está acontecendo e Pode ser Interessante aos Demais Componentes
- ``Queries``: Indica a Requisição de uma Informação

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

### Referencias

- [Explanation CQRS and Event Sourcing](https://www.youtube.com/watch?v=BEYF1x48npw)
