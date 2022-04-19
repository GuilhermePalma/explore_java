# Example Spring Bot

O Projeto ``exampleSpringBot`` tem como **Objetivo** explorar a utilização do Spring Bot para programar em Java um Web
Service

### Estrutura

Quando se utiliza do ``Maven`` para Gerenciar um Web Service, é importante seguir um padrão de projeto, mantendo os
arquivos dentro de determinados diretorios

Os Arquivos sempre estarão dentro da pasta ``src``, se subdividindo em:

- ``main``
    - ``java``: Onde ficarão as Classes Javas
    - ``resources``: Onde ficarão os Recursos (Paginas HTML, Arquivos CSS, JS, etc)
- ``test``: Local de Diretorios/Classes de Testes

### Maven

- Arquivo ``pom.xml``
    - Informações do Projeto (Ex: Nome, Versão, etc)
        - ``groupId``: Identificação/Dominio do Grupo Fez o Projeto
        - ``artifactId``: Nome do Projeto
        - ``version``: Versão em que o Projeto está
        - ``name``: Nome do Projeto
        - ``description``: Descrição do Projeto
    - Decrição das Dependencias (Ex: Driver de Banco de Dados, JPA): Ficam dentro da Tag ``dependecies``
        - ``dependecy``: Adiciona uma Dependencia no Projeto
    - Plugins: Adicionam um Recruso em alguma fase do Ciclo de Vida do projeto - Ficam dentro do ``build --> plugins``
- Plugins
    - Entram em alguma fase do Ciclo de Vida do Maven
    - Normalmente são usados para automatizar algo, como Testes Unitarios
- Ciclo de Vida: Segue a Seguinte Ordem: Compilação (Construção do Projeto) --> Testes (Testes Unitarios e Utilitarios)
  --> Instalação (Gera um Arquivo que pode Utilizado)
- Gerenciador de Dependencias
    - Consulta um Repositorio Online com as Dependencias
    - Normalmente são arquivos ``.jar``
    - Responsavel por Ler o arquivo ``pom.xml``, acessar o Repositorio Online e Baixar as Dependencias

### Criando Web Service

A partir do **Spring Bot** é possivel poupar muito Trabalho de Configuração e Logica de Codigo para a criação de uma
Aplicação, permitindo que através de anotações crie-se endpoints para requisições.

- Erros Comuns
    - Só é possivel ter uma Mesma URL para Metodos Diferentes caso o Metodo HTTP seja diferente (Ex: Em um metodo é
      utilizado o ``GET`` e em outro é usado o ``POST``). Caso contrario, a aplicação irá exibir um erro
    - Requisição utilizando um Metodo Invalido (Ex: Requisição ``GET`` para um Metodo ``POST``)

#### Anotações

- ``@RestController``: Define que a Classe se Trata de um Controller REST API
- ``@RequestMapping``: Faz o Mapeamento de uma Requisição HTTP (Utilizando a URL)
    - ``method=RequestMethod.___``: Metodo HTTP utilizado na Requisição. Por padrõa o Metodo HTTP é o ``GET``
    - ``path=urlForRequest``: Define a partir de qual Fragmento de URL será chamado o Metodo
- ``@GetMapping``: Faz o Mapeamento da URL utilizando o Metodo ``GET``
