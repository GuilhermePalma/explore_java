# Conceitos de Mensageria

### Resumo

A mensageria é uma forma de organizar sistemas de forma distribuida e independetes, mas permitindo a comunicação entre
si. O modulos responsavel por essa comunicção é o ``Message Broker``: Ele recebe e distribui os eventos para os serviços
responsaveis.

Por meio do ``Message Broker`` é possivel registar, disponibilizar e consumir eventos. Como se trata de uma estrtutura
centralizadora é possivel ter diferentes sistemas conectados nele, independete da Tecnologia, Linguagem, Dominio ou
as Lógicas Internas de Negócio, deixando esses pontos específicos à nivel do proprio serviço.

Por meio das configurações do ``Message Broker`` é possivel separa-lo em instâncias, assegurando que caso uma instância
deixe de funcionar corretamente outra irá substituir e garantir o funcionamento da aplicação. Outro ponto configuravel é
o armazenamento dos eventos, permitindo o rastreio e o reprocessamento de eventos com falhas.

A arquitetura discutida seguirá o padrão FIFO (First-In, First-Out), ou seja, cria uma fila de eventos que serão
consumidos conforme a sequência que foram criados. Para os serviços identificar, distinguir e consumir os eventos é
utilizado um identificador chamado `Topic`, comumente é usado um texto como identificar.

Para implementar, é possivel usar dois Padrões: `Point-to-Point` e `Publisher-Subscriber`, variando apenas como os
eventos serão criados e gerenciados. No `Point-to-Point` existe duas formas de considerar um evento como
consumido: `Fire-and-Forget` (Envia o evento e espera a confiramaçao do seu registro no ``Message Broker``)
e `Request/Apply` (Espera receber uma resultado do consumo do evento). Já no padrão `Publisher-Subscriber` fica divido
os **Produtores** (`Publiser`) e os **Consumidores** (`Subscriber`) dos eventos; além disso, os eventos podem ser
consumidos de duas formas: apenas uma unica vez ou consumido por todos os `Subscriber` que monitoram o `Topic` daquele
evento.

| Vantagens                                                                                           | Desvantagens                                           |
|-----------------------------------------------------------------------------------------------------|--------------------------------------------------------|
| Menor Acoplamento: Os servições não conhecem os demais serviços (Linguagens e Regras)               | Aumento da Complexidade da Arquitetura                 |
| Escalabilidade: Serviços independentes, permite implementar/remover um serviço sem afetar os demais | Aumento na Complexidade de DEBUGs                      |
| Por meio dos LOGs e Eventos armazenados no `Message Broker`, é possivel reprocessar os eventos      | Curva maior de Aprendizagem                            |
|                                                                                                     | Adição de mais itens na Arquitetura (`Message Broker`) |

### Introdução

- Microserviços: Uma forma de estruturar o `software` como coleções de aplicações divididas por **Dominio de Negócio**
    - Cada serviço deve ter um Proposito Proprio
    - Diminuir o Acomplamento: Os microserviços podem se comunicar entre si, mas são desenvolvidos, testados e escalados
      individualmentes, sem ocorrer a dependencia entre si.

### Mensageria

- É um padrão de arquitetura que define que sistemas possam se comunicar por **Trocas de Mensagens** (Nesse contexto,
  será chamado de `Eventos`)
- Os eventos são gerenciadas por um `Message Broker`
- O `Message Broker` ou `Event Backbone` ou `Event Bus` ou `Servidor/Modulo de Mensagens` se trata da estrutura
  responsavel por receber, gerir, distribuir e enfileirar os `Eventos`. Um evento só saí dessa fila de eventos quando é
  consumido por algum serviço.
    - **Fluxo Assincrono**: Os `Eventos` são aramazenados em FILA, ou seja, não necessariamente serão consumidos no
      momento em que foram criados, mas sim quando chegar a sua vez na Fila.
- Um evento é registrado no `Message Broker` atraves de um serviço. Dependendo do fluxo definido da arquitetura, o mesmo
  serviço **ou** outro serviço será responsavel por consumir os Eventos da Fila
- `Event Storage`: Se trata de armazenar os `Eventos` para ter um historico das alterações ou tambem consumi-los num
  momento futuro.
    - É configuravel dentro do `Message Broker`
    - Formas de Armazenamento: Bancos de Dados, no Proprio `Message Broker`
- Evita `Deadlock de Eventos`: Evita que os eventos alterem um mesmo recurso. Um exemplo seria a compra de um
  mesmo ingresso de show por diferentes usuarios
    - Com a mensageria, evita ocorrer `Deadlock de Eventos`: Problemas em que um mesmo dado é alterado ao mesmo tempo
    - A estrutura utilizada é a FIFO (First-In, First-Out), ou seja, FILA. Dessa forma, os dados serão alterados a
      partir do `Evento` mais antigo para o mais recente
    - Esta arquitetura traz ao sistema uma maior resilencia nos dados, evitando conflitos de alterações em um mesmo
      valor
    - Exemplo Prático: Escolha de Poltrona num Cinema/Onibus/Avião, Escolha de um ingresso no Show

### Arquitetura de Eventos

- A arquitetura terá como centro o `Message Broker`
    - Nele será Publicado, Consumido e Gerenciado os `Eventos`
    - Os microserviçoes se conectam no `Message Broker`, registrando e os metodos que irão consumir os eventos
    - Menor acoplamento: Por conta da centralização do Fluxo no `Message Broker`, os microserviços não se conhecem e nem
      se comunicam diretamente entre si. Eles devem ser Independentes e Desacoplado
- Pilares da Arquitetura
    - Ter os dados Transitando entre os Sistemas por meio de `Eventos`
    - Prioridade em Responder e Consumir os Eventos
    - Realidade da Aplicação: Os LOGs gerados permitem que armazene os eventos e monitore o seu estado
        - Em casos de perda da Realidade da Aplicação, isto é, algum problema que não é possivel garantir que os dados
          estão corretos, é possivel reprocessar os eventos e obter novamente o estado corrigido
    - As APIs são vistas como redes que respondem às solicitações quando necessario
- Beneficios
    - As Comunicações entre serviços não são perdidadas, visto que são armazenadas
    - É possivel armazenar eventos para reprocessar, se necesário
    - A Publicação de `Eventos` é permitida, mesmo se os serviços estejam desativados
    - Tarefas com maior demanda podem ser separadas em Serviços
    - Maior garantia de transmissão.
        - Se o `Message Broker` não consiga entregar um evento, há mecanismos de retentativa de entregas
        - Caso não consigam processar os eventos, adicionam numa lista de mensagens perdidas, não perdendo o registro
- Desvantagem
    - Aumento na Complexidade e Dificuldade em DEBUGs
    - Consistencia Eventual: Não é possivel alterar certos dados até os eventos serem propagados
    - Maior Curva de Aprendizado

### Padrões de Mensagerias

#### Fila: FIFO (First in, First Out)

- Se trata de um padrão de Arquiatetura
- É utilizada a Fila, ou seja, o Primeiro Evento à ser registrado (`First In`), será o Primeiro Evento à ser
  Processado (`First Out`)
- Os eventos são **distribuidos** entre os consumidores (Providers)
- Caso não exista um Consumidor das Mensagens ou ele esteja indisponivel, as mensagens serão registradas, mas não serão
  consumidas, gerando um acúmulo para ser processado
- Pensar nessse padrão como se numa ponta existe um INPUT para os Eventos, no centro a Estrutura que mantem a FILA
  desses Eventos e na outra ponta o resultado do PROCESSAMENTO/Consumo do Evento

#### Point-To-Point

- Nesse padrão, tambem é implementado a Fila, mas o Evento é destinado a um Serviço Especifico
    - Exemplo Prático: E-mails: É destinado à outro(s) endereço(s) especificos. Um e-mail só é enviado após o
      anterior já ter sido enviado.
- Modos para definir se um `Evento` foi Consumido
    - `Fire-and-Forget`: Quando **não** é necessario receber a Resposta do Destinatario
        - Nessa fluxo, a observação do Evento só fica até o momento em que o `Message Broker` diz que registrou
          o `Evento`
    - Request/Reply: O Remetente **Espera** a Resposta do Destinatario
        - Nesse fluxo, é esperado que o evento retorne ao Remetente alguma resposta após processar o evento
        - É necessario ter dois centralizadores de `Eventos`, um para as Requisições e outro para as Respostas

#### Publish/Subscriber (Pub-Sub)

- `Topic`: Para que os sistemas identifiquem os `Eventos` criados e que serão processados é necessário ter um
  identificador chamado de `Topic`.
    - É responsavel por definir uma identificação do Grupo que o evento pertence. Uma vez pertencendo a um grupo, os
      serviços que monitoram aquele `Topic` conseguem consumi-lo
- Nesse Padrão existe a divisão entre os Produtores de Mensagens (`Publisher`) e os Consumidores de
  Mensagens (`Consumer`). Para que um `Consumer` consuma os `Eventos` dos `Publisher`, é necessário ter uma
  correspondencia no `Topic` dos Eventos
    - Os `Publishers` não são atrelados aos `Topics` do `Message Broker`, mas sim criam `Eventos` dentro de um `Topic`
    - Os Subscribers estão atrelados e monitoram `Topics` do `Message Broker`.
- Pensar como se numa ponta existissem os Publicadores dos Eventos (`Publisher`), no centro um `Message Broker`
  armazenando os `Eventos` em `Topic` e na outra ponta os Consumidores de Eventos (`Subscriber`), responsaveis por
  consumir os `Eventos` nos topicos correspondetes
- Consumo de Eventos
    - Todos os `Subscriber` de um `Topic` receberão o mesmo evento enviado pelos `Publisher`
    - O `Evento` pode ser consumido por diversos `Subscriber` registrados naquele `Topic`
        - `Topic` Igual + Microserviços Diferentes: Cada `Subscriber` acionado dentro de cada Microserviço terá uma
          função diferente (Ex: Auditoria, Notificação, Validação, Envio, 2 Factor Auth)
    - O `Evento` será consumido apenas uma unica vez por um dos `Subscriber` registrados naquele `Topic`
- Exemplo Prático: Grupos de Chats: Para os usuarios que pertencem ao Grupo A (seria o `Topic` do `Message Broker`)
  devem ser notificados (`Subscriber`) quando uma nova mensagem é enviada (`Publisher`)

### Durabilidade dos Eventos

- Os eventos podem ser aramazenados no `Message Broker` ou Banco de Dados. Com isso, existe um Limite (Espaço, Numero de
  Itens, Dia após o recebimento, etc) de eventos armazenados e a validade que eles terão para ser consumidos antes de
  serem destruidos.
    - Tanto o Limite, como a Validade, podem ser configurados no `Message Broker`
- Sempre que um Evento é REGISTRADO ou CONSUMIDO no `Message Broker`, ele retorna uma confirmação (`ACK`)
    - O Fluxo pode variar conforme a arquitetura
    - Caso exista mais de uma instância do `Message Broker`, pode ser que a confirmação só seja enviado no caso de todas
      as intancias terem recebido a mensagem. Entrentanto, pode ser que conforme a configuração, a confirmação seja
      enviada caso uma unica instancia receba aquele evento.

### Streaming de Eventos

- A partir de dados capturados em tempo real, é criado um Fluxo de Eventos
    - Ex: Sensores que observam a umidade do local a cada 15 minutos. Um `Message Broker` recebe e processa esses dados
    - Ex2: Finalizar uma transação em Tempo real na Bolsa de Valores
    - Ex3: Monitoramento da Atividade na Interneth pelo Google

### Sistemas de Mensagerias

- RabbitMQ: Projeto `Open Source` que utiliza o Protocolo AMQP, fazendo com que apenas um `Subscriber` consuma o evento
    - `Message Broker`
    - Utiliza o Protocolo AMQP (Advanced Message Queuing Protocol): Um evento é enviado e dentro os vários consumidores,
      apenas um processa aquele evento
    - Por meio de Plugins, é possivel trabalhar com Streaming de Eventos (Streaming Text Oriented MEssaging Protocol)

- Amazon SNS (Simple Notification Service): Sistema baseado em `PUSH` (Envio) de eventos entre aplicações. Funcionam no
  formato `A2A` (`Application to Appplication`) ou `A2P` (`Application To Person`)
    - Pode ocorrer entre varios tipos de dispositivos: Entre dois microserviçoes e entre microserviço e usuario
    - Baseado no conceito de `PUSH`: Enviar uma mensagem para um destinatario
    - `A2A`: Application to Application: Repassa mensagens entre aplicações por meio do AWS SQS, AWS Lambda (serveless),
      Endpoints HTTPs (CAllback HTTPs) ou outros metodos
    - `A2P`: Application to Person: Envio de SMS, Notificação, E-mails para o Usuario

- Amazon SQS (`Simple Queue Service`): Realiza o `PULL` (Recebimento) de eventos. Os eventos são organizados em Fila,
  dividos em `Topics` e possuem um prazo de validade.
    - Semelhante ao RabbitMQ, mas gerenciado e hospedado pela AWS
    - Baseado no conceito de `PULL`: Recebe uma mensagem para armazenar
    - Sistema de Fila: Recebe e Armazena Mensagens para que outros serviços utilizem
        - Os itens armazenados tem que ter uma validade para serem retirados do armazenamento
        - Os itens são aramzenados em Topicos/Tipos para depois poderem ser consumidos por outros sistemas

- Kafka: Plataforma `Open Source` com alta utilização no mercado. Implementa o padrão `Sub/Pub`, entregando alta
  disponibilidade (varias instancias), escalabilidade e permitindo o aramazenametno dos Eventos.
    - Plataforma Distribuida desenvolvida em Java. `Open Source` desenvolvida pela Linkedin e mantida pelo Confluence
    - Permite o Armazenamento Permanente: Guarda os eventos em disco, até atingir um limite
    - Alta Disponibilidade, Escalabilidade e Fault-Tolerant: Caso um servidor deixe de funcionar, as demais instancias
      continuam a funcionar e consumindo os eventos
    - Processamento de Streaming Embutido
    - `Scheme Registry`: Como os dados trafegam em bytes pelo Kafka, existe um sistema para verificar se a mensagem
      enviada pelo `Publiser` é a esperada pelos `Subscribers`
        - Ex: Subscriber espera receber um JSON com determinado Campo. Então o `Publisher` deve seguir esse padrão
    - Possui interfaces que permitem integrar à diferente soluções
        - Diversas linguagens podem gerar eventos no Kafka
        - Existem Aplicações que recuperam informações e geram evento Kafka para recuperar o Estado de uma Aplicação
    - Os Subscribers podem ser agrupados, permitindo com que defina isoladamente os parametros deles (Instancias,
      Capacidades, etc)
