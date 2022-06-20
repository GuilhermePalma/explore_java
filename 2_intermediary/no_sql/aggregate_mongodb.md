# Mongo Aggregation

As **Operações de Agregação** (`Mongo Aggregation`) são funções de agregação que permitem processar dados, calcular,
modificar, remover e/ou inserir valores, agrupar conforme as suas caracteristicas os dados em uma consulta de
uma `collections` do Banco de Dados

Dessa forma, exitem três maneiras que pode ser realizad as operações de agregação:

- [Metodos de Agregação de Finalidade Unica](#metodos-de-agregao-de-finalidade-unica)
- [Funções de Map-Reduce](#funes-de-map-reduce)
- [Pipeline de Agregação](#pipeline-de-agregao)

### Metodos de Agregação de Finalidade Unica

São operações de Finalidade Unica, como contar os documentos (`estimatedDocumentCount()` e `countDocuments()`) e buscar
documentos unicos (`distinct()`: Obtem os Valores existentes de um Campo/`Key`). Dessa forma, são operações que agregam
os documentos em uma unica coleção, tendo pouca flexibilidade

### Funções de Map-Reduce

Quando utilizada essas funções, os dados passam por duas fases obrigatoriamente

- `Appeasement`: Cada documento de entrada é processado e emite um ou mais objetos de saìda
    - Os documentos de entrada ser submetidos a uma condição
- `Reduce`: Combina a saída da operação de `Map`
    - Os documentos de saida podem ser classificados e/ou limitados

Além dessa duas fases, pode haver uma terceira para finalização, em que é realizado as modificações finais no resultado

Além disso, esse processo utiliza de funções Javascript personalizadas para executar o `map-reduce` e a finalização. Por
utilizar do Javascript, permite uma grande flexiblidadem, mas se torna menos eficiente e mais complexo que
o [pipeline de agreggação](#pipeline-de-agregao)

### Pipeline de Agregação

Se trata de uma forma de **agrupar** os dados em um **pipeline de processamento**. Na prática, o `pipeline` atua como
uma sequência de estágios que realiza transformações nos dados.

Dessa forma, dentro do `pipeline`, a cada `step` que o dado passa eles são transformados (ex: campos são criados ou
removidos, os documentos são agrupados, etc). Esse é um ponto importante ao criar o `pipeline`: sempre montar um fluxo
considerando os dados após passar pela `step` anterior - e tambem o resultado transformação sofrida nela.

Alguns `steps` disponivies para o pipeline de agregação são:

- `$addFields`/ `$set`: Semelhante ao `$project`, adiciona novos campos nos documentos de saída.
- `$count`: Conta a quantidade de documentos naquela `step`.
- `$facet`: Quando se deseja criar novos **pipelines de agregação** dentro `step` atual. Os documentos de entrada serão
  iguais para os novos `pipelines`, mas cada `pipeline` terão suas proprias `steps` e diferentes resultados de
  documentos de saída
- `$project`: Remodela os documentos de entrada, adiciondo ou removendo campos existentes
- `$unset`: Remove campos dos documentos
- `$group`: Conforme os campos informados, agrupa os documentos. Tambem é possivel acumular os dados daquele
  agrupamento (gerando `arrays`) e realizar operações com os valores dos campos (ex: media, soma)
- `$limit`: Delimita quantos documentos serão passados para a proxima `step`
- `$skip`: Ignora/Pula uma quantidade determinada de elementos passados para a proxima `step`
- `$sort`: Reordena os documentos conforme uma `key` especifica. Nesse ``step``, os valores dos documentos são matidos,
  somente alterando a ordem
- `$sortByCount`: Conforme os campos informados, agrupa os documentos recebidos e calcula a quantidade (`count`) de
  documentos em cada grupo
- `$lookup`: Executa uma associação externa com outra `collection` do Banco de Dados, obtendo os valores caso existam
- `$match`: Filtra os documentos conforme os parametros definidos, passando apenas os documentos correspondentes para a
  proxima `step`
- `$merge`: Introduzido na versão 4.2, incorpora (salvar, mesclar, substituir, atualizar) os documentos resultantes do
  pipeline em uma ``collection``. Para isso, é necessario que seja o último `step` do pipeline
- `$out`: Salva os documentos resultantes do pipeline em uma outra `collection`. Tambem deve ser o ultimo `step`
  do `pipeline`
- `$replaceWith`: Substitui os documentos de entrada por um documento específico
- `$sample`: Seleciona uma quantidade de documentos especificos de forma aleatoria
- `$unwind`: Segrega um dos campos do documento (normalmente um `array` com elementos), mantendo os demais campos
