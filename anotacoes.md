
### Spring Bot

- Framework criado para facilitar a configuração de Spring
- Cria um Projeto já pronto para ser usado
  - Por meio de Starters (Grandes Bibliotecas de Configurações) -> Autoconfiguration
- Pode ser usado para desenvolver sistemas web, microservices
- Utiliza o Spring Initializr (https://start.spring.io) para configurar um Projeto Configurado

### Dependencias Basicas

- Spring Boot DevTools: Conjuntos de Ferramentas para o Desenvolvimento do Projeto, como o LiveReload (Carregar alterações de forma rapida e simples)
- Spring Web: Conjuntos de Ferramentas utilizadas para o Desenvolvimento Web
- Spring Boot Actuator: Permite monitar a aplicação Java
- Spring Data JPA: Faz o gerenciamento do banco de dados
- H2 Database: Cria um banco de dados em memoria

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
