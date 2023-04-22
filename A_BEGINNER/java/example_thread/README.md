<h1 align="center">Threads em Java</h1>

<h3 align="center">Um compilado de aplicação e manipulação da Programação Paralela em Java, usando das Threads e
Runnable</h3>

---

[🍵 Voltar ao Conteudo Java](../README.md)

### Overview

Esse modulo busca explorar o Funcionamento da Classe Threads e das suas opreações junto a interface Runnable. As Threads
se tratam de um processo sendo executado de forma paralela à outro processo em uma mesma aplicação. Isso faz com que
seja possivel executar diferentes ações num tempo menor do que a programação sincrona.

Esse conceito vem com uma ideia de programação mais funcional e paralela, otimizando alguns processos executados em
Java.

### Thredas

Se trata da menor unidade de codigo que um programa pode executar, permitindo com que vários processos dele sejam
executadas de maneira paralela ao mesmo tempo que outro.

Quando a maquina possui apenas um unico processador, ele altera entre as threads executando cada uma pouco a pouco,
dando a impressão de que elas estão sendo executadas ao mesmo tempo. Já quando se possui mais processadores, cada um
fica responsavel pela execução de cada Thread.

### Ciclo de Vida das Threads

- `new Thred()`: Instancia/Criação de uma nova Thread
- `.start()`: Prepara a Thread para ser executada (Estado **Runnable**)
- `.run()`: Executa a Thread (Estado **Run**)
- `.yield()`: Faz a Thred que estava sendo executada voltar ao Estado pronto para Executar (**Running** --> **
  Runnable**)
- `.sleep()`, `.wait()` e `.suspend()`: Coloca a Thread em Espera (`Waiting`)
- `.notify()`, `.notifyAll()`: Notifica a Thread para voltar ao Estado pronta para ser Executada (Estado **Runnable**)
- `.resume()`: Voltar a Execução
- **Dead**: Thread Finalizada

### Sincronização do Codigo

A sincronização do Codigo de Threads, ou Thread-safe visa a utilização de uma mesma instancia de obejto, mas sem que as
modificaões e estados de uma Thread interfira nos valores e estados de outra. Essas modicicações podem ser percebidas,
quando é atribuido um valor a uma das propriedades da classe em uma Thread e depois esse mesmo objeto é acessado em
outra Thread. Ao acessa-lo, o valor permance alterado, tendo que lidar com isso cada vez, o que não é desejado na
lógica.

Além disso, é utilizado a Thread-safe quando se deseja acessar um recurso compartilhado, lidar com um recurso por vez ou
até mesmo, coordenar atividades entre duas ou mais Threads. Para isso, é possivel utilziar a palavra `synchronized` para
definir que um metodo ou bloco de codigo só será acessado e lidado uma vez por Thread.

Para mais exemplos, acesse o seguinte arquivo
[SyncronizedThreads](src/main/java/com/guilhermepalma/syncronized/SyncronizedThreads.java)
que possui exemplos de chamadas do metodo ``printTable`` da Classe
[Table](src/main/java/com/guilhermepalma/syncronized/SyncronizedThreads.java). Esse metodo é marcado pela keyword
`syncronized`, ou seja, uma tabela será inteirametne impressa em uma Thread para que depois a proxima Thread
imprima a sua

### Notify e Await

Os metodos `notify` e `await` faz com que seja possivel acompanhar o estado de um recurso compartilhado entre Threads.

- `.wait()`: Coloca a Thread em espera, bloqueabdo a sua execução até ser _notificada_ para voltar a execução
- `.notify()`: Notifica a Thread que estava em espera para voltar a execução
- `.notifyAll()`: Mesma função do `.notify`, mas notifica todas as Threads

*Obs: Para ter acesso aos metodos acima é necesario usar o `syncronized`

### Deadlock

Se trata do bloqueio de um recurso que outro processo/recurso tambem iria utilizar. Para resolver é recomendado
utilizar os metodos `notify`, `await` e `syncronized` para controlar o campartilhamento dos recursos
