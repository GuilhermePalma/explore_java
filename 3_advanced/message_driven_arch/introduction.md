# Conceitos de Mensageria

### Introdução

- Microserviços: Estruturar aplicações como coleção de aplicaçõpes
    - Cada serviço tem um proposito proprio
    - Diminui o acomplamento: Podem comunicar-se entre si, mas são desenvolvidos, testados e escalados individualmentes

### Mensageria

- Conceito que define que sistemas possam se comunicar por trocas de mensagens (os eventos)
- As mensagens, tambem vistas como eventos, são gerenciadas por um Message Broker
- O Message Broker ou Event Backbone, ou Event Bus (em protugues: Barramento de Mensagens), ou tambem servidor/modulo de
  mensagens enfilera as mensagens, demandando que em algum momento elas sejam consumidas para sair dessa Fila.
    - Existem Message Broker que armazena as mensagens para serem consumidas em outro momento.
    - Esse armazenamento pode ocorrer em Banco de Dados
    - Faz com que o fluxo seja assincrono, ou seja, que a operação não será processada necessariamente durante a
      execução do metodo
- Uma forma de evitar conflitos nos dados
    - Evita com que ocorra problemas em situações em que um mesmo dado pode ser alterado ao mesmo tempo
    - A estrutura de eventos utilizando a FILA faz com que esse dado seja alterado a partir da alteração mais antiga
      para a mais recente
    - Exemplo Prático: Escolha de Poltrona num Cinema, Onibus, Avião

### Arquitetura de Eventos

- A arquitetura terá como centro o Message Broker.
    - Nele será Publicado, Consumido e Disponibilizado os eventos
    - Os microserviçoes se conectam nessa Message Broker, registrando e consumindo os eventos
    - Menor acoplamento: Por conta da centralização do Fluxo no Message Broker, os microserviços não se conhecem e nem
      se comunicam diretamente entre si
        - Os serviçoes são independentes e desacoplado, em que os eventos serão consumidos por um serviço específico
- Pilares da Arquitetura
    - Ter os dados Transitando entre os Sistemas
    - Prioridade em Responder e Consumir os eventos
    - Realidade da Aplicação: Por meio dos LOGs gerados, é possivel com que seja possivel armazenar os eventos
        - Em casos de perda da Realidade da Aplicação, é possivel reprocessar os eventos e reobter o estado
    - As APIs são vistas como redes que respondem às solicitações quando necessario
- Beneficios
    - As Comunicações entre serviços não são perdidadas
    - É possivel armazenar eventos para processa-los depois
    - A publicação de eventos é permitida mesmo que os serviços estejam desativados
    - Tarefas com maior demanda, podem ser separadas em serviços
    - Maior garantia de transmissão.
        - Caso o message broker não consiga entregar um evento, há mecanismos de retentativa de entregas
        - Caso não consigam processar os eventos, adicionam numa lista de mensagens perdidas, não perdendo o registro
- Desvantagem
    - Maior complexidade e dificuldade em DEBUGs
    - Consistencia eventual: Não é possivel alterar certos dados até os eventos serem propagados
    - Maior curva de aprendizado

### Padrões de Mensagerias

#### FIFO: First in, First Out

- Nesse padrão, é utilizada a Fila, ou seja, o Primeiro Evento à ser registrado, será o Primeiro Evento à ser processado
- Pensar nessse padrão como se numa ponta existe um INPUT para os Eventos, no centro a Estrutura que mantem a FILA
  desses Eventos e na outra ponta o resultado do PROCESSAMENTO/Consumo do Evento
- Caso não exista um Consumidor das Mensagens ou por algum motivo ele não esteja as processando, as mensagens não serão
  consumidas e só irão acumular

#### Point-To-Point

- Nesse padrão, tambem é implementado a Fila, mas o Evento é destinado à um Serviço Especifico
    - Exemplo Pratico: E-mails: É destinado à outro(s) endereço(s) especificos. Um e-mail só é enviado após o
      anterior já ter sido enviado.
- Fire-and-Forget: Quando não é necessario receber a resposta do Destinatario
    - Nessa fluxo, a observação do Evento só fica até o momento em que o Message Broker recebe a resposta
- Request/Reply: O Remetente espera a resposta do Destinatario
    - Nesse fluxo, é esperado que o evento retorne ao Remetente com a resposta
    - É necessario ter dois centralizadores de Eventos, um para as Requisições e outro para as Respostas

#### Publish/Subscriber (Pub-Sub)

- Pensar nessse padrão como se numa ponta existissem os Publicadores das Mensgens (Publisher), no centro um Message
  Broker subdividido em Topicos/Tipos e na outra ponta os Consumidores (Subscriber) que recebem e consomem os
  Topicos/Tipos específicos
    - Para um unico evento, pode ser acionado diversos Subscriber
    - Cada Subscriber/Microserviço acionado, terá uma função diferente (Ex: Auditoria, Notificação, Validação, Envio, 2
      Factor Auth)
    - Todos os Subscribers de um Topico/Tipo receberão o mesmo evento enviado pelos Publisher
    - Os Publishers não estão atrelados à nehum Topico/Tipo do Message Broker
    - Os Subscribers estão atrelados à um Topico/Tipo do Message Broker
    - Nessa Padrão pode ser arquitetado em diferentes formatos
        - Apenas o primeiro Provider pode consumir o evento
        - Varios Providers pode consumir esse evento
        - Os Providers consumirão varia vezes aquele evento
    - Exemplo Pratico: Grupos de Chats. Para os usuarios que pertencem ao Grupo A (seria o Topico/Tipo do Message
      Broker) devems ser notificados (Subscriber) quando uma nova mensagem é enviada (Publisher)

### Durabilidade dos Eventos

- Os eventos que são aramazenados no Message Broker. Com isso, existe um Limite (Espaço, Numero de Itens, Dia após o
  recebimento, etc) de eventos armazenados e a validade que eles terão para ser consumidos antes de serem destruidos.
    - Tanto o Limite, como a Validade, podem ser configurados no Message Broker
- Normalmente existe mais de uma instância do Message Broker, para caso em que um Message Broker deixe de funcionar
- Sempre que um Evento é REGISTRADO ou CONSUMIDO no Message Broker, ele retorna uma confirmação (ACK)
    - O Fluxo pode variar conforme a arquitetura
    - Caso exista mais de uma instância do Message Broker, pode ser que a confirmação só seja enviado no caso de todas
      as intancias terem recebido a mensagem. Entrentanto, pode ser que conforme a configuração, a confirmação seja
      enviada caso uma unica instancia receba aquele evento.

### Streaming de Eventos

- A partir de dados capturados em tempo real, é criado um Fluxo de Eventos
    - Ex: Sensores que observam a umidade do local a cada 15 minutos. Um Message Broker recebe e processa esses dados
    - Ex2: Finalizar uma transação em Tempo real na Bolsa de Valores
    - Ex3: Monitoramento da Atividade na Interneth pelo Google

### Sistemas de Mensagerias

- RabbitMQ
    - Message Broker Open Source
    - Utiliza o Protocolo AMQP (Advanced Message Queuing Protocol): Um evento é enviado e dentro os vários consumidores,
      apenas um processa aquele evento
    - Por meio de Plugins, é possivel trabalhar com Streaming de Eventos (Streaming Text Oriented MEssaging Protocol)

- Amazon SNS (Simple Notification Service)
    - Pode ocorrer entre varios tipos de dispositivos: Entre dois microserviçoes e entre microserviço e usuario
    - Baseado no conceito de PUSH: Enviar uma mensagem para um destinatario
    - A2A: Application to Application: Repassa mensagens entre aplicações por meio do AWS SQS, AWS Lambda (serveless),
      Endpoints HTTPs (CAllback HTTPs) ou outros metodos
    - A2P: Application to Person: Envio de SMS, Notificação, E-mails para o Usuario

- Amazon SQS (Simple Queue Service)
    - Semelhante ao RabbitMQ, mas gerenciado e hospedado pela AWS
    - Baseado no conceito de PULL: Recebe uma mensagem para armazenar
    - Sistema de Fila: Recebe e Armazena Mensagens para que outros serviços utilizem
        - Os itens armazenados tem que ter uma validade para serem retirados do armazenamento
        - Os itens são aramzenados em Topicos/Tipos para depois poderem ser consumidos por outros sistemas

- Kafka
    - Plataforma Distribuida desenvolvida em Java. Open Source desenvolvida pela Linkedin e mantida pelo Confluence
    - Permite o Armazenamento Permanente: Guarda os eventos em disco, até atingir um limite
    - Alta Disponibilidade, Escalabilidade e Fault-Tolerant: Caso um servidor deixe de funcionar, as demais instancias
      continuam a funcionar e consumindo os eventos
    - Processamento de Streaming Embutido
    - Scheme Registry: Como os dados trafegam em bytes pelo Kafka, existe um sistema para verificar se a mensagem
      enviada pelo Publiser é a esperada pelos Providers
        - Ex: Provider espera receber um JSON com determinado Campo. Então o Publisher deve seguir esse padrão
    - Possui interfaces que permitem integrar à diferente soluções
        - Diversas linguagens podem gerar eventos no Kafka
        - Existem Aplicações que recuperam informações e geram evento Kafka para recuperar o Estado de uma Aplicação
    - Os Providers podem ser agrupados, permitindo com que defina isoladamente os parametros deles (Instancias,
      Capacidades, etc)

### To-do List

- [ ] Revisar Erros Ortograficos
- [ ] Revisar Termos Tecnicos e Coloca-los como Codigo
- [ ] Conforme o Contexto, alterar a palavra Mensagem por Evento
- [ ] Trocar Topico/Tipo para Grupo
- [ ] Revisar termos duplicados (ex: termo1/termo2)
- [ ] Criar Resumo
