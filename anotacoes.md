
### Spring Bot

- Permite que crie aplicações stand-alone
  - Permite que a aplicação seja executada apenas pelo JDK
  - Facilita a criação de ``Containers`` por centralizar a parte executavel
- Web Container
  - Tomcat, Jetty Undertow: Onde as aplicações Java são executadas 
  - Não é necessario gerar arquivos ``WAR``, o Spring Bot já faz o deploy dentro do servidor
- Framework criado para facilitar a configuração de Spring
- Utiliza Starters (Grandes Bibliotecas de Configurações) → Configura a Aplicação por si proprio
  - O incio de uma aplicação é mais rapido por já estar a maioria das coisas configurados
  - Ganho de produtividade
- O Spring Bor apenas facilita a configuração de uma aplicação Spring, mas ele não gera codigo
- Pode ser usado para desenvolver sistemas web, microservices
- Utiliza o [Spring Initializr](https://start.spring.io) para configurar um Projeto Configurado

### Dependencias Basicas

- Spring Boot DevTools: Conjuntos de Ferramentas para o Desenvolvimento do Projeto, como o LiveReload (Carregar alterações de forma rapida e simples)
- Spring Web: Conjuntos de Ferramentas utilizadas para o Desenvolvimento Web
- Spring Boot Actuator: Permite monitar a aplicação Java
  - Algumas Metricas Observadas: Health, Metrics, Beans, Propriedades, Loggers
- Spring Data JPA: Faz o gerenciamento do banco de dados
- H2 Database: Cria um banco de dados em memória

### Estrutura

- ``src/main/java/nameproject/nameproject.java``: Arquivo e Pasta Principal do Projeto
- ``src/test/java/nameproject/nameproject.java``: Arquivo e Pasta Principal de Testes Unitarios

### Inicializando Projeto

- Configurando o JDK: ``ctrl+shift+alt+s`` 
  - ``SDKs`` -> Escolher a versão usada no Projeto
  - ``Project`` -> ``SDK`` -> Selecionar a Versão do SDK do Projeto
  - ``Apply`` -> ``OK``
- Configurações de Execução:
  - Parte Superior Central -> ``Add Configuration`` -> ``Edit Configuration`` ou ``Add Configuration``
  - ``Add New Configuration`` 
    - ``Name`` -> Nome do Projeto
    - ``Build and Run`` -> Selecionar a Versão Java do Projeto e Apontar para a Pasta Main (Pasta Principal do projeto)
    - ``Apply`` -> ``OK``

## Deploy no Heroku

- Fazer o Commit e enviar para o GitHub (``git push origin main``)
- Executar ``git subtree push --prefix bookstoremanager origin deploy``: Obtem os arquivos da Tree ``bookstoremanager`` do ultimo commit e envia para um Branch chamado ``deploy`` para o Heroku obter apenas o Projeto Java

### Observações

- Separar as responsabilidas em ``Controller`` e ``Service``, permite com que o ``Controller`` apenas faça o tratamento de requisições e respostas, enquanto o ``Service`` faz a manipulação dos dados, operações no banco de dados e cuida das regras de negocio
  - Essa separação de responsabilidade, facilita a manutenção do codigo

## Padrões de Persitencia

#### Active Record

Os proprios objetos são reponsaveis para interagir com a camada de persistencia (Banco de Dados)
- Se trata de um Objeto ativo que possui seus proprios metodos para se relacionar à uma Tabela do Banco de Dados
  - Os atributos do Objetos serão mapeados para cada atributo da Tabela
  - Ex: Classe Cliente possui um Atributo Endereço que se trata de uma Tabela no Banco de Dados
- Normalmente os metodos são acessados/usados por meio de Herança

#### Data Mapper
- Usa metadados (anotations, xml, json) para fazer o mapeamento do atributo de um Objeto com uma coluna da tabela do Banco de Dados
  - Realção entre:
    - Objeto e Tabela
    - Atributo do Objeto e Coluna da Tabela
- Pode incluir o relacionamento entre tabelas
- Usado pelo JPA: A partir dos metadados, ele faz as manipualções com o banco de dados

#### ORM (Object Relational Mapper)

Mapeamento Objeto Relacional

- Usando Anotations do JPA
  - As Tabelas podem ser criadas pelo proprio JPA, caso seja pedido
  - ``@Table(*nameOfTable*)``: Relaciona o Objeto com uma Tabela
  - ``@Column(*nameOfColumn*)``: Relaciona o Objeto a uma Coluna de uma Tabela
    - ``nullable=boolean`` : Permite se o Item pode ou não ser Nulo 
  - Anotações como @NotNull não são usadas para validar os dados e sim para a criação/gerenciamento de colunas na tabela
  - ``@Transient``: Quando a variavel/atributo não será inserida no Banco de Dados
- Usando o Arquivo ``persistence.xml`` (JPA)
  - Nele é possivel determiar o comportamento do JPA e Hibernate no Projeto
  - Configurações da Conexão com o Banco de Dados, Geração de Tabelas, Exibição de Queries SQLs












