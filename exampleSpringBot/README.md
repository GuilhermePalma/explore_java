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
