# Modularidade (JDK 9)

Esse conceito introduz o conceito de Modulos que permite uma melhor gerência do encapsulamento das Classes (``default``
, ``private``, ``protected`` e ``public``). Nesse gerenciamento, é possivel definir quais Pacotes (``Packages``) serão
compartilhados com outras aplicações. Esse conceito foi introduzido no JDK 9 e evita erros de **Dependencias Ciclicas**
(A interdependencia de Modulos de diferentes Projetos).

## Aplicando a Modularidade (module-info)

Por padrão as classes ficarão definidas com internas do Proprio Projeto, não permitindo o acesso externo. Ao criar o
arquivo ``module-info.java`` é possivel controlar quais pacotes serão importados e exportados de um determinado Projeto.

Nesse arquivo, não é possivel inserir uma lógica que irá criar uma dependencia ciclica, isto é, não será possivel que os
projetos dependam um do outro.

- ``exports name.of.package;``: Exporta o ``Package`` com o Nome Informado (Substituir no ``name.of.package``)
- ``requires nameAplication;``: Representa que a Aplicação depende de um ``Package/Aplicação`` Informado (Substituir
  no ``nameAplication``)
