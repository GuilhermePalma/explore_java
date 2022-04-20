### API REST

- `API` (Aplicação que Permite a Interoperabilidade entre Sistemas) `REST` (Arquitetura de Aplicações que segue Boas
  Práticas)
- Um Modelo de Arquitetura utilizado na evolução da arquitetura do Protocolo HTTP
- Definição de Caracteristicas Fundamentais para a contruções de boas Aplicações seguindo **Boas Práticas**
- Recursos: Permitem mapear qualquer coisa **real** para um elemento de acesso Web
- `RESTful`: Quando se a API Implementa o Padrão REST
    - Deve seguir o `Modelo de Maturidade de Richardson`: Modelo que define 4 Pilares que a API deve seguir:
        - Nivel 0: Quando a API utiliza do Protocolo HTTP como Comunicação e não como Semantica
            - Não Utilizar Verbos na URI, e sim Substantivos
        - Nivel 1: Quando a API utiliza das URIs de Forma Eficiente, Utilizando um Mapeamento Eficiente, bem definido e
          unico para cada recurso
        - Nivel 2: Quando a API utiliza da Semantica Correta dos Metodos HTTP nas **Requisições** e **Retornos**
            - Ex: Cada função do codigo utiliza o Metodo HTTP Especifico, seguindo corretamento o Objetivo do Metodo
              HTTP
            - Ex2: A API Possui Retornos Corretos, Exbindo os Codigos Corretos de `Erro`, `Sucesso` e outros
        - Nivel 3: Implementação de HETEOAS na API (Guia dentro da Propria API para os possiveis caminhos do Recurso na
          API e a Relação dele nos Recursos)
            - Ex: Retorna como obter o recurso na API, podendo ser um Link Obtendo o Proprio Recurso na API
- Existem Divisões na Requisição:
    - `Server`: URI de Base (Ex: `www.google.com`)
    - `Resource`: Parte/Recurso que está sendo acessado (Ex: `/api`, `/search`, etc)
    - `Parametters`: Parametros/Filtros Informados para Realizar a Requisição (Ex: `id`, `name`, etc)
        - É possivel passar Parametros, via `Request Body` ou na Propria Formação da `URI`
            - Podem ser Passado no `Body` de uma Requisição (Ex: Requisições `POST`)
            - Podem ser Passado na Propria `URI` (Ex: `www.something.com/api?paramKey=paramValue`
              , `www.something.com/api/paramValue` e outros)
- Analisando Resposta de uma Requisição
    - `X-Ratelimit-limit: valueLimit`: Valor Maximo de Requisições que ainda podem ser Feitas
    - `X-Ratelimit-remainig: valueRemaining`: Valor Restante de Requisições que ainda podem ser Feitas
    - `content-type: typeOfReturn`: Tipo do Dado Retornado na Requisição (Ex: `application/json; charset=utf-8`)
- Autenticação
    - `OAuth` e `OAuth2`:
        - É feita uma Reuqisição com o `ID` (ou `Email`) e o `Password` do Usuario para Obter um `Token`
        - A Autenticação pode ser feita tambem pela Propria Requisição pelo Campo de `Authentication` no `Header`
- Pode retornar e Receber Dados via `JSON` (JavaScript Object Notation): Um Objeto contendo uma Dupla de `"key": value`

#### Principais Metodos

Se trata de Metodos que utilizam o Protocolo `HTTP` e `HTTPS` para realizar Requisições Web

- `GET`: Metodo Usado para Obter Recursos
- `POST`: Metodo Usado para Criar um Recurso
    - Uma boa prática é colocar os Dados da Requisição no `Body` da Requisição
- `PUT`: Metodo Usado para Atualizar um Recurso
- `DELETE`: Metodo Usado para Excluir um Recurso

- HTTP + REST: Fornece um Conjunto de respostas aos clientes (Browser, APIs, outros) de como agir a partir de uma
  resposta
    - Respostas de Sucesso, Erro, Inconformidade, etc

#### Codigos de Estado REST

- 1XX: Estado de Informações
    - Ex: 100 - Continue (Indica que tudo ocorreu corretamente e que a solicitação deve continuar)
- 2XX: Estado de Sucesso
    - Ex: 200 - OK (Indica que a Requisição foi Bem Sucedida)
- 3XX: Estado de Redirecionamento
    - Ex: 301 - Moved Permanently (Indica que a URI da Solicitação foi Alterada)
- 4XX: Esatdo de Erro no Cliente
    - Ex: 400 - Bad Request (Requisição Invalida; O Servidor não entendeu a requisição; Sintaxe Invalida)
- 5XX: Estado de Erro no Servidor
    - Ex: 500 - Internal Server Error (Erro Interno do Servidor; Surgiu um Erro no Servidor e ele não foi possivel
      Lidar)

### Arquitetura Monolitica

- Todas as Funções do negócio estão em um unico processo
- Vatangens
    - Mais facil entendimento, menor curva de aprendizado
- Desvantagens
    - Baixa escalabilidade (Mais complicado de aumentar partes do sistema)
    - Alta dependencia de Compenentes de Codigo (Alterações de uma Funcionalidade pode afetar outra)

|         Vantagens          |                                    Desvantagens                                     |
|:--------------------------:|:-----------------------------------------------------------------------------------:|
|  Mais Simples de Entender  |   Baixa Escalabilidade: Mais complicado de aumentar e dar manutenção ao sistema)    |
| Menor Curva de Aprendizado |  Alta Dependencia dos Componentes: Altearar uma Funcionalidade pode afetar outras   |

### Arquitetura de Microserviços

- Cada Serviço é desenvolvido num conjunto de regras de negócios especificos
- Implementação de forma Independente

|                        Vantagens                        |                              Desvantagens                              |
|:-------------------------------------------------------:|:----------------------------------------------------------------------:|
|   Manutenção e Evolução do Software são mais Estaveis   | Possui uma maior complexidade para criar/manter um sistema distribuido |
|   Flexibilidade para Utilizar Diferentes Tecnologias    | Uso de Softwares de Monitoramento para acompanhar o Estado do Software |
| Mais simples de Fazer o `Deploy` do Sistema em Produção |           Sistema mais complexo de Entender para Iniciantes            |
|                                                         |                                                                        |

### Cloud Computing

- Inspirado nos Serviços Utiliarios Cotidianos (Ex: Agua, Luz, etc), em que se paga o que se utiliza de Infraestrutura
    - Antes era utilizado Estruturas Dimensionadas e Limitadas, em que era implantado um serviço de grande cobertura,
      mas que na maioria do tempo ficava ocioso
    - Inicialmente era comprado uma Estrutura para Hospedagem do Serviço, depois fui utilizado o esquema de "aluguel" de
      máquinas, depois evoluiu para utilização de Máquinas Virtuais
- Pagamento pelo Uso (Seja por Tempo ou Espaço)
    - Permite o Escalonamento Automatico: Permite Expandir ou Diminuir a Infraestrutura
        - Permite criar Condições Lineares (Para crescimento ou diminuição) para esse Processo
