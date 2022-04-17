# Modularidade (JDK 9)

Esse conceito introduz o conceito de Modulos que permite uma melhor gerencia do encapsulamento das Classes (``default``
, ``private``, ``protected`` e ``public``)

Permite com que uma aplicação tenha uma dependencia com um pacote dentro de outra aplicação

## Sistema de Modulos

Por padrão as classes ficarão definidos com internos do proprio projeto, não permitindo o acesso externo

Evita a Dependencia Ciclica de Modulos (Modulos de Diferentes Projetos que um depende do outro)

O arquivo ``module-info.java`` controla os modulos do Projeto. Ele pode definir:

- A Visibilidade dos Pactoes para que Projetos externos acessem

