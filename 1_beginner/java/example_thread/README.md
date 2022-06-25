# Thredas

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
