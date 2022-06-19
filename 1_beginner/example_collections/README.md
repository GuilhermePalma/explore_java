# Collections em Java

A utilzação das Classes da API ``java.util.Collections`` é um dos pontos essenciais da lingaugem Java, já que a
utilização de listas para armazenar em memoria mais de um dado é muito presente em quaquer aplicação, sendo por exemplo
uma lista de usuarios, dados obtidos do banco de dados e outras varias aplicações.

Dessa forma, nesse documento será abordado o funcionamento, implementação, principais metodos das ``Collections``,
a Estrutura `Map`, as Interfaces `Comparable` e `Comparator` do Java.

### Arrays

Se tratam de uma estrutura de dado que permite armazenar mais de um valor na variavel. Na criação desse tipo de dados,
já é delimitado quantos itens serão armazenados dentro de si, dando uma exceção caso tente inserir itens à mais da
quantidade de itens informados

Pensando de forma analoga, seria o equivalente à uma linha de uma Planilha do Excel, em a linha é a variavel e as
celulas dessa linha seriam os valores. Por exemplo:

| String[5] | `"Valor 01"` | `"Valor 02"` | `"Valor 03"` | `"Valor 04"` | `"Valor 05"` |
|-----------|--------------|--------------|--------------|--------------|--------------|

As estruturas seguintes (`List`, `Set` e `Map`) utilizam esse mesmo conceito de uma variavel que pode receber diversos
valores.

### Collections

Implementada baseado na coleção de ``Arrays``, com o objetivo de solucionar e implementar funcionalidades mais
abrangentes

- Permite ter objetos Retirados e Inseridos de Forma **Dinamica**
- Abordagem mais declarativa: Permite com que a classe manipule as proprias operações e que o usuario de menos "ordens"

### List

Se trata de uma Lista em que os dados serão salvos e respeitarão a ordem de inserção. Possui metodos de inserção,
remoção,
busca por elemento, obtenção do index, tamanho e varias outras operações

#### ArrayList

Se trata da Implementação de ``List`` mais utilizada. Por meio dessa interface, permite com que adicione e remova itens
sem se preocupar com as operações que envolvem esse processo.

### Queue

Permite a implementação de ``fila``, em que os elementos são tratados conforme sua ordem. Dessa forma, ele garante que
os elementos obedeçam a ordem da inserção, sendo que o primeiro elemento que entra, deve ser o primeiro a sair.

Com isso, permite a inserção e remoção de elementos, mas não a modificação e reordenção de elementos já presentes
na `Queue`, preservando a ordem dos elementos presentes.

Alguns exemplos de aplicações são:

- Controle de Estoque
- Controle de Envio E-mails
- Listas de Compras

### Set

Essa collection não garantirá a ordem de inserção, mas sim uma melhor performance na leitura, não mantendo a ordenação
dos itens.

Não irá permitir a inserção de itens repitidos, sempre que inserir, será verificado se o valor já existe no `Set`, tendo
uma perda de perfonance nessa ação

Não possui um index, não permitindo com que busque um item especifico na coleção

Quando se considera a ordenação, mais a performance será afetada

Pode ser instanciada por meio do `HashSet`, `TreeSet` e `LinkedHashSet`

|               |                         Quando Usar                          |                    Ordenação                     |                                 Performance                                 |
|:--------------|:------------------------------------------------------------:|:------------------------------------------------:|:---------------------------------------------------------------------------:|
| HashSet       |                    Não importa ordenação                     |                    Não possui                    |       Não permite repetição de valores, mais performatico na leitura        |
| LinkedHashSet |                 Importa a ordem de Inserção                  |                Ordem de Inserção                 |                          Implementação mais lenta                           |
| TreeSet       | Alterar a ordem pelos `Comparators` ou Uso de `Binary Trees` | Matem a Ordem de Inserção e pode ser Reordenação | Performatico na leitura, mais lento na reordenação e do que o LinkedHashSet |

A cada modificação do TreeSet, ele é reordenado

### Map

Não é uma extensão de `java.util.Collection`

Implementações: `HashMap` (Mais Utilizado), `LinkedHashMap` (Garante a Ordenação dos Campos), `TreeMap` (Para
manipulação de `Binary Tree`), `HashTable` (Versão mais antiga do `HashMap`, usada para sincronização de `Threads`,
acessando ele em diferentes `Threads`)

Estrutura: É formado por dados no formato `key-value`, em que é inserido um `value` com uma chave unica (`key`). Por
exemplo: `color: blue`, em que `color` é a `key` e `blue` é o `value`, onde varias chaves podem ter o valor de `blue`,
mas só pode ter uma unica `key` chamada `color`

### Comparators e Comparable

Se trata de interfaces que permitem a padronização a ordenação de objetos (Classes e Objetos mais Complexos (Criados
pelo Usuario))

- `Comparator`: É implementado em uma Classe Separada, recebendo a outra classe que deve ser Ordenada
    - Sobrescreve o metodo `compare` para realizar a comparação entre dois objetos recebidos
- `Comparable`: É implementada na propria classe
    - Sobrescreve o metodo `compareTo` para realizar a comparação entre o objeto atual e o objeto recebido
