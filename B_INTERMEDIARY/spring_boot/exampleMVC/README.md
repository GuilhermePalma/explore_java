# Estrutura MVC em Spring Boot

[🫖 Voltar ao Conteudo Spring Boot](../README.md)

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
    - `extends PagingAndSortingRepository<T, V>`: Segue o mesmo conceito e possui os mesmos metodos do `CrudRepository`,
      mas adiciona metodos para Paginação e Ordenação de Dados do Banco de Dados
        - Ao Seguir Padrões do `Spring Bot` é possivell anotar metodos em interfaces que o `Spring Bot` junto ao JPA
          realizam uma busca
            - Ex: `public void findByPriceLessThanEqual(Double price)` --> Padrão: `findBy...`

### Inversão de Controle

- Inversion Controll using Dependency Injection: Inversão de Controle usando Injeção de Dependencia
- Ocorre quando uma Classe A depende de uma Classe B e irá utilizar uma instância da Classe B em algum momento
- `@Autowired`: O Framework responsavel pela Aplicação (nesse contexto, o `Spring`) adiciona uma Instancia da Classe
  nela Propria
    - Ex: Uma `ClassA` depende de uma `ClassB`, mas a instância da `ClassB` é Complexa e com vários Parametros. Com
      isso, a `ClassA` cria um Atributo `ClassB classB` marcado com o `@Autowired` que o `Spring Framework` faz uma
      Copia dos Atributos da `ClassB` e atribui isso na variavel `classb`
        - Chama **Inversão de Controle** por conta que a ordem de Instanciação é inversa, ou seja, ao inves da instância
          ser obtida da classe que depende para a outra classe, ela é obtida da outra classe para a classe que depende
- A Inversão de Dependencia só ocorre se o `Spring` for o responsavel por fazer a Instancia da Classe. Caso a Instancia
  seja Chamada explicitamente no codigo pelo programador, o `@Autowired` não irá funcionar e o atributo será `null`.

### Anotações

- `@Valid`: Verifica se a Classe foi Validada pelo Java Validation
- `@NotNull`: Indica que a variavel não pode ser nula
- `@NotBlank`: Indica que a varivel não pode ser em branca (Somente usar em Strings, classes `Double`, `Long`, etc não
  irá funcionar)
- `@Min(value)`: Valor minimo que pode ser aplicado a uma Variavel
- `@Max(value)`: Valor maximo que pode ser aplicado a uma Variavel
