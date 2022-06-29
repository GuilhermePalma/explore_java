# Concurrency API

Se trata de uma API desenvolvida no Java 5 que visa facilitar operações multithreads e evitar problmas comuns presentes
nas opreaçãoes usando a classe Thread.

### Thread Safe

Se trata de classes seguras para serem utilizadas em cenarios Multithreads, sem que ocorra o bloqueio de recurso ou a
impossibilidade da manipulação dos recursos

### Volatile

Assim como o `syncronized`, serve como palavra-chave para definir que sempre ao utilizar aquela variavel, será obtido o
valor mais recente (da memória RAM) da variavel. Em um cenario comum, as variaveis são armazenadas num cache local da
aplicação 