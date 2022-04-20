### MVC

Se Trata de um Rigido Padrão de Arquitetura de Software em que as Responsabilidades de cada camada ficam segragadas.

- Model: Regras de Controle, Validação, Entidade
- View: Renderiza a resposta da Requisição WEB
    - Utilização Javascript, CSS, HTML
- Controller: Controla/Coordena o Fluxo da Aplicação

- Browser (Realiza a Requisição) --> Web Service (O Servidor recebe a Requisição) --> Aplicação (Recebe e Trata os
  Dados) --> Controller (Verifica o que foi Solicitado)
    - Do Controller pode ser Redirecionado à View: Exibe o Conteudo ao Usuario sem Consultar o Banco de Dados
    - Do Controller pode ser Redirecionado ao Model: Obtem Dados do Banco de Dados, redireciona o Resultado ao
      Controller que verifica se a Logica foi Concluida e no fim redireciona à View para Exibir ao Usuario o que foi
      solicitado

- Browser realiza uma Requisição à Aplicação
    - Aplicação: A Aplicação Spring Bot recebe a Requisição, passa pelo Servidor (Ex: Tomcat), redireciona ao
      FrontController, obtem a URL e redireciona ao Controller correto
    - Controller: Redireciona o Fluxo
    - Model: Realiza Validações, realiza interações com o Banco de Dados

### Criação do Projeto MVC

- `src.main.java.guilhermepalma.controllers`: Adiciona os Controllers RESTfull
- `src.main.java.guilhermepalma.models.entity`: Adiciona as Classes Mapeadas com o JPA para Mapeamento com o Banco de
  Dados
- `src.main.java.guilhermepalma.models.repositories`: Armazena as Interfaces Javas de Repositorio
    - Abstrair e Criar Metodos de Altor Nivel que dará acesso aos Dados
    - ``extends CrudRepository<T, V>``
        - `T`: Classe que representa o Repositorio que está sendo iniciado
        - `V`: Classe que representa o Tipo do ID da Classe
