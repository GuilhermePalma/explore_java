# Persistencia de Dados - JPA

[🫖 Voltar ao Conteudo Java](../README.md)

Projeto ``exmapleJPA``: Projeto Pratico das Diferentes abordagens utilizando o Java Persistence API (JPA) para Mapear,
Manipular e Gerenciar Tabelas, Registros e Colunas no Banco de Dados MySQL

## Estrutura do Projeto

- [Configurações do Projeto usando o JPA (persistence.xml)](src/main/resources/META-INF/persistence.xml)
    - Nesse arquivo contem configurações da conexão com o Banco de Dados MySQL, como Usuario, Senha, Tipo do Banco de
      Dados, Driver usado para Conexão. Tambem possui configurações do JPA, como a exibição de Queries e Log.
- [Classe DAO (Data Acess Object)](src/main/java/com/guilhermepalma/exampleJPA/model/DAO/DAO.java)
    - Classe que utiliza de Metodos e outras classes do JPA - como o EntityManager - para manipular os registros no
      Banco de Dados
- [Anotações Usando JPA](src/main/java/com/guilhermepalma/exampleJPA/model)
    - Nas Classes [Product](src/main/java/com/guilhermepalma/exampleJPA/model/Product.java)
      e [User](src/main/java/com/guilhermepalma/exampleJPA/model/User.java) possuem as anotações Basicas para o
      Mapeamento das Classes com o JPA
    - [UserTest](src/test/java/model/UserTest.java): Classe de Teste que implementa manualmente a manipulação de
      registros no Banco de Dados usando Bibliotecas e Classes do JPA
    - [ProductTest](src/test/java/model/ProductTest.java): Classe de Teste que utiliza a Classe Generica
      [DAO](src/main/java/com/guilhermepalma/exampleJPA/model/DAO/DAO.java) para acessar os metodos configurados para a
      manipulação de registros no Banco de Dados
- [Relacionamento OneToOne (1-1)](src/main/java/com/guilhermepalma/exampleJPA/model/relations/oneToOne)
    - Nas Classes [Client](src/main/java/com/guilhermepalma/exampleJPA/model/relations/oneToOne/Client.java)
      e [Position](src/main/java/com/guilhermepalma/exampleJPA/model/relations/oneToOne/Position.java) contem anotações
      estrategias do JPA para realizar o Mapeamento de duas Classes que possuem um Relacionamento
    - [ClientTest](src/test/java/model/relations/oneToOne/ClientTest.java): Classe de Teste que utiliza a Classe
      Generica [DAO](src/main/java/com/guilhermepalma/exampleJPA/model/DAO/DAO.java) para acessar os metodos
      configurados para a manipulação de registros com Relacionamento no Banco de Dados
- [Relacionamento OneToMany e ManyToOne (1-N)](src/main/java/com/guilhermepalma/exampleJPA/model/relations/oneToMany)
    - [Order](src/main/java/com/guilhermepalma/exampleJPA/model/relations/oneToMany/Order.java): Classe que possui uma
      relação Bidirecional com a Classe
      [ItemOrder](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToOne/ItemOrder.java) em uma Relação
      Many to One. Nessa relação o ItemOrder pode ser relacionado varias vezes a Diferentes Order, mas a Order é unica,
      não podendo ser repetida
    - [ItemOrder](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToOne/ItemOrder.java): Classe que
      possui uma relação Bidirecional com a Classe
      [Order](src/main/java/com/guilhermepalma/exampleJPA/model/relations/oneToMany/Order.java). ESsa relação representa
      um dos varios items que a Order pode ter. Armazena os Dados (Dados do Produto, Preço que foi Vendido, Quantidade,
      etc) dos Produtos que serão comprados
- [Relacionamento ManyToMany (N-N)](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToMany)
    - As Classes [Uncle](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToMany/Uncle.java)
      e [Nephew](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToMany/Nephew.java) representam um
      Relacionamento Bidirecional Muitos para Muitos, em que o mapeamento desse relacionamento pelo JPA é feito na
      classe **Uncle**
        - Os registros dos **Uncles** podem ser associados a um unico ou varios **Nephew**, assim como os **Nephews**
          podem ser associados a um unico ou varios **Uncle**
    - [UncleNephewTest](src/test/java/model/relations/manyToMany/UncleNephewTest.java): Executa algumas operações DAO
      com a Gerencia do Relacionamento Uncle-Nephew feito manualmente
    - As Classes [Movie](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToMany/Movie.java)
      e [Actor](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToMany/Actor.java) possuem um
      Relacionamento Bidirecional Muitos para Muitos que seguem a mesma Logica que as
      Classes [Uncle](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToMany/Uncle.java)
      e [Nephew](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToMany/Nephew.java), mas adicionando
      novos mapeamentos das Tabelas e Relacionamentos no Banco de Dados

### Padrões de Persitencia

#### Active Record

Os proprios objetos são reponsaveis para interagir com a camada de persistencia (Banco de Dados)

- Se trata de um Objeto ativo que possui os seus proprios metodos para se relacionar a uma Tabela do Banco de Dados
    - Os atributos do objeto serão mapeados para cada atributo da Tabela
    - Ex: Classe Cliente possui um Atributo Endereço que se trata de uma Tabela no Banco de Dados
- Normalmente os metodos são acessados/usados através de Herança

#### Data Mapper

- Usa metadados (anotations, xml, json) para fazer o mapeamento do atributo de um Objeto com uma coluna da tabela do
  Banco de Dados
    - Realção entre:
        - Objeto e Tabela
        - Atributo do Objeto e Coluna da Tabela
- Pode incluir o relacionamento entre tabelas
- Usado pelo JPA: A partir dos metadados, ele faz as manipualções com o banco de dados

### Object Relational Mapper (ORM)

Mapeamento Objeto Relacional

- Usando Anotations do JPA
    - As Tabelas podem ser criadas pelo proprio JPA, caso seja pedido. Se não, seguira o modelo da tabela já criada no
      Banco de Dados
    - ``@Table``: Relaciona o Objeto com uma Tabela
        - ``name=nameOfTable``: Nome especifico para a tabela
        - ``scheme=nameOfDatabase``: Relaciona a Tabela à outro Banco de Dados
    - ``@Column``: Relaciona o Objeto a uma Coluna de uma Tabela
        - ``name=string``: Nome da Coluna que será gerado
        - ``nullable=boolean`` : Permite se o Item pode ou não ser Nulo
        - ``length=int``: Tamanho Maximo da Coluna da Tabela
        - ``precision=int``: Define o Tamanho Maximo do número
        - ``scale=int``: Numeros Permitidos após a virgula (casas decimais)
    - ``@Transient``: Quando a variavel/atributo não será inserida no Banco de Dados
    - ``@Entity``: Define que está relacionada a uma Tabela no Banco de Dados
    - ``@Temporal``: Define que o dado se trata de uma Data/Horario
        - ``TemporalType.DATE``: Define uma Data
        - ``TemporalType.TIME``: Define uma Hora
        - ``TemporalType.TIMESTAMP``: Define uma Data e Hora
    - ``@Embeddable``: Define colunas Comuns em Outras Tabelas, mas sem representar um relacionamento, e sim a inserção
      direta dos atributos como atributos da outra Tabelas
    - Anotações como ``@NotNull`` não são usadas para validar os dados e sim para a criação/gerenciamento de colunas na
      tabela
- Usando o Arquivo ``persistence.xml`` (JPA)
    - Nele é possivel determiar o comportamento do JPA e Hibernate no Projeto
    - Configurações da Conexão com o Banco de Dados, Geração de Tabelas, Exibição de Queries SQLs
- Estados JPA
    - Estado Gerenciavel:
        - O Objeto está sendo observado
        - Qualquer alterção feita no Objeto o JPA sincroniza com o Banco de Dados
    - Estado não Gerenciavel
        - O Objeto só será atualizado quando um metodo for chamado explicitamente (Ex: ``merge``)
        - Mais recomendado de se utilizar, já que controla quando a alteração será realiza no Banco de Dados
        - Para tornar um Objeto não Gerenciavel, usar ``entityManager.detach(object)``

### Relacionamentos

- Padrão **Entidade Relacional**
    - Um para Muitos (1-N): Uma coluna é ``Primary Key`` (Unica) em uma tabela e tambem é usada como ``Foreign Key`` em
      outra tabela, uma ou varias vezes
    - Um para Um (1-1): Uma coluna é ``Primary Key`` (Unica) em uma tabela e tambem é usada como ``Foreign Key`` em
      outra tabela, uma unica vez
    - Muitas para Muitas (N-N): Uma ``Primary Key`` de uma Tabela é gerada a partir de duas colunas com
      uma ``Foreign Key``
    - O Relacionamento Sempre será unidirecional, ou seja, apenas a Tabela A tem uma chave com a Tabela B, sem que a
      Tabela B tenha alguma chave que refencie a Tabela A
- Padrão Orientação Objeto
    - Relação Unidirecional Um para Um (1-1): Uma Classe possui um atributo que referencia outra classse
    - Relação Bidirecional Um para Um (1-1): Ambas as Classes possuem um atributo que referencie outra
    - Um para vários (1-N): Uma classe possui uma Lista de outra classe como atributo
    - Varios para vários (N-N): Ambas as classes possuem uma Lista da outra classe como atributo
    - Pode ocorrer 2 tipos de relacionamentos:
        - Unidirecional: Apenas uma das Classes possui o(s) atributo(s)
            - Normalmente a partir do que o Software demanda, é escolhido qual classe possui mais destaque para possuir
              a outra Classe como atributo
        - Bidirecional: Ambas as Classes possui o(s) atributo(s)
            - Sempre que utilizar, é necessario verificar se os dados se manterão consistente.
- Anotações JPA
    - ``@OneToOne``
        - ``cascade={CascadeType.____, CascadeType.___, ...}``: Define quais operações será feita em Conjunto das Duas
          Classes
            - Usa-se as ``{}`` para informar mais de um Tipo de Cascade
    - ``@ManyTo Many``:
    - ``@OneToMany``: Uma ``Collections/List`` será associado
    - ``@ManyToOne``
    - ``@OneToMany`` e ``@ManyToOne`` indicam uma Relação Bidirecional
        - ``@OneToMany`` representa a "Mãe" do Elemento
        - ``@ManyToOne`` representa o "Filho" do Elemento
    - ``mappedBy=nameOfOtherAtribute`` (Usado em um dos Relacionamentos Acima): Usado em Relacionamentos Bidirecionais,
      informando o Nome do Atributo da outra classe em que possui o mapemanto principal do relacionamento
    - ``@JoinColumn``: Define a coluna de Relacionamento de Duas Tabelas
        - ``name=nameOfColumn``: Nome que será aplicado à Coluna no Banco de Dados
        - ``unique=boolean``: Define se o Campo será do Tipo ``Unique`` (Unica)
        - ``referencedColumnName=nameOfVariable``: Coluna que será usada como Referencia (Ex: ``Primary Key/ID``)
        - ``name=nameInDatabase`` = Nome que será aplicado à coluna da Tabela
    - ``@JoinTable``
        - ``joinColumns=@JoinColumn``: Representa a Coluna da Classe responsavel pelo Mapeamento (Normalmente usado em
          Relações ``@ManyToMany``)
        - ``inverseJoinColumns=@JoinColumn``: Representa a coluna da Classe que possui a segunda parte do Mapeamento,
          normalmente utiliza em Relações ``@ManyToMany``, no lado que recebe o atributo ``mappedBy``
    - Quando está trabalhando com a Inserção Manual dos registros nas Tabelas relacionadas, sempre é necessario inserir
      primeiro o registro que a outra tabela depende

- Estrategia de Obtenção de Registros
    - Em Relações ``@OneToOne`` e ``@ManyToOne``, o ``fetch`` padrão é o ``EAGER`` (Obtenção Acelerado)
    - Em Relações ``@ManyToMany`` e ``@OneToMany``, o ``fetch`` padrão é o ``LAZY`` (Obtenção Tardia)
    - Ex1: A classe [Order](src/main/java/com/guilhermepalma/exampleJPA/model/relations/oneToMany/Order.java) possue uma
      Relação ``@OneToMany`` com a Classe
      [ItemOrder](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToOne/ItemOrder.java) em que os **
      ItemOrder** serão obtidos somente quando tentar acessa-lo, fazendo uma nova consulta no Banco de Dados.
        - A Propriedade usada nessa relacionamento é: ``@OneToMany(fetch=FetchType.LAZY)``
    - Ex2: A classe [ItemOrder](src/main/java/com/guilhermepalma/exampleJPA/model/relations/manyToOne/ItemOrder.java)
      possui uma Relação ``@ManyToOne`` com a Classe
      [Product](src/main/java/com/guilhermepalma/exampleJPA/model/Product.java), levando com que os
      **Product** sejam obtidos junto ao **ItemOrder**, em uma unica consulta no Banco de Dados (Utiliza do ``JOIN``)
        - A Propriedade usada nessa relacionamento é: ``@ManyToOne(fetch=FetchType.EAGER)``

### Named Queries

As Named Queries são consultas em SQL (no caso do ``Named Native Query``) ou em JPQL (para ``Named Query``) para obter
dados do Banco de Dados

- ``Named Query``: Utiliza do JPQL, ou seja, utiliza do Mapeamento do JPA para fazer a criação de uma Query SQL e
  realizar a consulta no Banco
    - Como Usar ? (Veja um [Exemplo Aqui: Named Query](src/main/resources/META-INF/persistence.xml))
        - Criar um arquivo ``.xml`` com a Tag ``entity-mappings``
        - Criar uma Tag ``named-query`` e dentro da Tag ``query`` escrever a Query em JPQL
        - Referenciar esse arquivo por meio da Tag ``mapping-file`` dentro da ``persistence-unit``
        - Inicializar uma ``TypedQuery`` com uma ``EntityManager.createNamedQuery(name, params)``
        - Inserir os argumentos na ``TypedQuery`` (``query.setParameter(key, value)``)
        - Obter o Resultado da ``TypedQuery`` com  ``.getResultList()``
- ``Named Native Query``: Utiliza do SQL Puro, em que é necessario saber o nome exato das tabelas no Banco de Dados.
    - Como Usar ? (Veja um [Exemplo Aqui: Named Native Query](src/main/resources/META-INF/persistence.xml))
        - Criar um arquivo ``.xml`` com a Tag ``entity-mappings``
        - Criar a Tag ``sql-result-set-mapping`` e Configurar a Classe que será Mapeada quando obter o resultado da
          Consulta SQL
            - É necessario ter um dos Construtores com a variavel que aramazenará o resultado
        - Criar uma Tag ``named-native-query``, referenciar o Mapeamento com ``result-set-mapping`` e dentro da Tag
          ``query`` escrever a Query em SQL
        - Referenciar esse arquivo por meio da Tag ``mapping-file`` dentro da ``persistence-unit``
        - Inicializar uma ``TypedQuery`` com uma ``EntityManager.createNamedQuery(name, params)``
        - Inserir os argumentos na ``TypedQuery`` (``query.setParameter(key, value)``)
        - Obter o Resultado da ``TypedQuery`` com  ``.getResultList()``

### Herança no Mapeamento do JPA

- Estrategias para Mapear Herança usando o JPA (Implementar na Classe Pai ``@Inheritance``):
    - ``strategy = InheritanceType.TABLE_PER_CLASS``
        - Cria Tabelas para cada Classes Concreta, mantendo de Fora as Classes Abstratas (Contem os Dados Generalizados)
        - Nesse Caso ocorreria Campos Duplicados nas Tabelas (Ex: varios campos ``name`` em diferentes Tabelas)
        - Uma maior separação e especificação dos Campos nas Tabelas
        - Exemplo no
          Commit: [8f74546](https://github.com/GuilhermePalma/explore_java/commit/8f74546934edd35d8b1543843f72e4087f4cb988)
    - Não marcar a Classe que Herda com o ``@Inheritance`` ou marcar com
      ``@Inheritance(strategy = InheritanceType.SINGLE_TABLE)``
        - Os atributos das classes Pais e Filhas são agrupados em uma unica Tabela, criando diversos campos opcionais
          que ficarão marcados como ``null``
        - É necessario aplicar o Processo do [@DiscriminatorColumn](#configurando-o-DiscriminatorColumn) para
          Identificar as Instancias das Diferentes Classes
        - Exemplo no
          Commit: [10b60c6](https://github.com/GuilhermePalma/explore_java/commit/10b60c6c00c64cf1c108d1db7989176871ff8411)
    - ``strategy = InheritanceType.JOINED``
        - Nessa Estrategia, será criada uma Tabela Geral que contenha os Campos Comuns
        - Tambem é criado tabelas secundarias, que contem as informações implementadas nas classes que Herdaram da
          Classe Pai. A relação será feita a partir do relacionamento da ``Primary Key`` da Tabela Geral, com
          uma ``Foreing Key`` da Tabela Secundaria com as demais informações
        - É necessario aplicar o Processo do [@DiscriminatorColumn](#configurando-o-DiscriminatorColumn) para Fazer a
          Separação das Tabelas conforme as Diferentes Classes
        - Exemplo no
          Commit: [82c6951](https://github.com/GuilhermePalma/explore_java/commit/82c69515925f1157a24ac9de7c29f29f0cf27298)

> Ao Testar cada Estrategia de Herança, pode ocorrer erros ao criar a Tabela. Para isso, exclua as Tabelas da Herança criadas nas versões anteriores

#### Configurando o DiscriminatorColumn

- Inserir o ``@DiscriminatorColumn`` na Classe Pai definindo os seguintes Atributos
    - ``name=nameOfColumn``: Nome da Coluna
    - ``length=int``: Tamanho da Coluna
    - ``discriminatorType=DiscriminatorType.___``: Tipo de Dado que será aplicado na Coluna
- Definir a Propriedade ``@DiscriminatorValue(value)``, com o ``value`` seguindo os atributos definido no
  ``@DiscriminatorColumn``
