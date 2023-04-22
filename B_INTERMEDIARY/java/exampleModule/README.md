# Modularidade (JDK 9)

[🫖 Voltar ao Conteudo Java](../README.md)

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
  - ``to name.of.second.package``: Adicionando o ``to`` no ``exports`` permite com que aponte para qual Aplicação será
    concedido o Acesso à aquele ``Package``. Adicionando virgulas, pode adicionar mais de um Módulo.
- ``requires nameAplication;``: Representa que a Aplicação depende de um ``Package/Aplicação`` Informado (Substituir
  no ``nameAplication``)
- ``transitive``: Evita com que aconteça erros ao passar Dependencias de outros Modulos para outra Aplicação
- ``open``: Usado na Frente de ``module`` para realizar o ``java.reflect`` na classe, manipulando e alterando os
  dados ``private`` e da Classe.
- ``opens name.of.package``: Usado para abrir  ``packages`` especificios, permitindo a manipulação das Classes dentro
  deles. Tambem é possivel aplicar a estratégia do ``to``, em que na Propria Aplicação referencia em que Aplicação
  o ``Package`` será usado

### Observações Finais

> Pendencias: ``Interface``, ``Implementação``, ``Provides With`` e ``Uses``
