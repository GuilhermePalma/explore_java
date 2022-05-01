### Importando Projetos IntelliJ

- Abra o Diretorio na IntelliJ
- No Menu Superior clique em: `File` --> `New` --> `Module from Existing Sources`
- Selecione o arquivo `pom.xml` e clique em `Ok`

### Spring Bot

- Alternativa para Utilizar em Serviços Empresariais (Autenticação, Persistencia, etc)
- Permite que crie aplicações Stand-Alone: Aplicação é Inicializada pelo Metodo ``main``
    - Não demanda de Configurações de Servidores
    - Permite que a aplicação seja executada apenas pelo JDK
    - Facilita a Criação de ``Containers`` por centralizar a parte executavel
- Web Container
    - Tomcat, Jetty Undertow: Onde as aplicações Java são executadas
    - Não é necessario gerar arquivos ``WAR``, o Spring Bot já faz o deploy no servidor
- Framework criado para facilitar a configuração de Spring
- Utiliza Starters (Grandes Bibliotecas de Configurações) → Configura a Aplicação por si proprio
    - O incio de uma aplicação é mais rapido por já estar a maioria das coisas configurados
    - Ganho de produtividade
- O Spring Bor apenas facilita a configuração de uma aplicação Spring, mas ele não gera codigo
- Pode ser usado para desenvolver sistemas web, microservices
- Utiliza o [Spring Initializr](https://start.spring.io) para configurar um Projeto Configurado

### Dependencias Basicas

- Spring Boot DevTools: Conjuntos de Ferramentas para o Desenvolvimento do Projeto, como o LiveReload (Carregar
  alterações de forma rapida e simples)
- Spring Web: Conjuntos de Ferramentas utilizadas para o Desenvolvimento Web de Web Servicesc
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
        - ``Build and Run`` -> Selecionar a Versão Java do Projeto e Apontar para a Pasta Main (Pasta Principal do
          projeto)
        - ``Apply`` -> ``OK``

## Deploy no Heroku

- Fazer o Commit e enviar para o GitHub (``git push origin main``)
- Executar ``git subtree push --prefix bookstoremanager origin deploy``: Obtem os arquivos da Tree ``bookstoremanager``
  do ultimo commit e envia para um Branch chamado ``deploy`` para o Heroku obter apenas o Projeto Java

### Observações

- Separar as responsabilidas em ``Controller`` e ``Service``, permite com que o ``Controller`` apenas faça o tratamento
  de requisições e respostas, enquanto o ``Service`` faz a manipulação dos dados, operações no banco de dados e cuida
  das regras de negócio
    - Essa separação de responsabilidade, facilita a manutenção do codigo

### Web Services

- SOAP
    - WSDL: Descreve como a disponibilizaçãodo Web Service (Quais as Funções, Parametros e Retornos)
    - Segue o Modelo RPC (Remote Procedure Call)
    - Modelo mais burocratico para utilizar
- REST
    - Normalmente os dados são tratados no Formato JSON
    - Usa HTTP Puro (Usa URLs para Requisições)
    - Ao seguir a conveção e restrições estruturais, o serviço se torna REST Full
    - Conveções RESTful
        - Normalmente possui uma URL Base (Estatica) + Continuação do Recurso que será usado
            - Ex: ``urlbase.com/clientes``, ``urlbase.com/clientes/36``
        - Metodo HTTP (POST, GET, DELETE, etc)
        - Possui uma Descrição desse Endpoint (URL Especifica + Metodo HTTP + Envio de Parametros)

#### Arquiteturas de Serviços

- SOA
    - Varios Serviços instalados um ESB (Enterprise Service Bus)
        - É possivel orquestrar/organizar uma Logica para utilziar os Serviços Instalados
    - Mais facil de ter uma comunicação entre os Serviços

<br/>

- Microsserviços:
    - Quebrar a aplicação em partes menores para que cada Web Service faça uma parte e tenha as suas responsabilidades
    - Mais simples de Escalar Serviços

### Computação em Nuvem

- Inspirado nos Serviços Utiliarios Cotidianos (Ex: Agua, Luz, etc), em que se paga o que se utiliza de Infraestrutura
    - Antes era utilizado Estruturas Dimensionadas e Limitadas, em que era implantado um serviço de grande cobertura,
      mas que na maioria do tempo ficava ocioso
    - Inicialmente era comprado uma Estrutura para Hospedagem do Serviço, depois fui utilizado o esquema de "aluguel" de
      máquinas, depois evoluiu para utilização de Máquinas Virtuais
- Pagamento pelo Uso (Seja por Tempo ou Espaço)
    - Permite o Escalonamento Automatico: Permite Expandir ou Diminuir a Infraestrutura
        - Permite criar Condições Lineares (Para crescimento ou diminuição) para esse Processo
