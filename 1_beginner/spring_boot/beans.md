# Beans

Os `Beans` são anotações (`Stereotypes`) que permite que o `Spring` consiga gerenciar (Intanciar e Destruir) as classes
por conta propria, tirando essa responsabilidade do programador e deixando-a com o `Spring Container`.

### Stereotypes

Tambem conhecidas como **Anotações**, os `Stereotypes` se tratam de marcações feitas nas classes e/ou interfaces Java
para que o `Spring` reconheça como um `Bean` e faça o gerenciamento. Uma vez adicionada um `Stereotypes` o `Spring`
consiguirá acessar a classe/interface por **Pontos de Injeção de Dependencia**. Esses `Stereotypes` são:

- `@Component`: Se trata de uma anotação **Generica**, podendo ser usada em qualquer classe/interface
- `@Service`: Diz que a classe/interface é reponsavel pelas **Regras de Negócio**
- `@Repository`: Informa que a classe/interface será responsavel pela **Persistencia e Transações no Banco de Dados**
- `@Controller`: A classe/interface marcada irá receber requisições (ex: RestEndpoints)

Após a adição dos `Stereotypes`, quando se deseja acessar essas classes/interfaces por meio de uma outra, é necessario
marcar a vairavel da classe com o `Stereotypes` com `@Autowired`. Dessa forma, será informado ao `Spring` que a classe
será instanciada e gerenciada por ele.

### Utilização do @Bean ?

O `Stereotypes` `@Bean` é utilizado quando se deseja criar em classes externas um `Bean`, fazendo com que a **Injeção de
Dependencia** citada acima seja possivel.

Para isso é necessario criar uma classe anotada com `@Configuration`, informando ao `Spring` que aquela classe contem
dados de configurações de outras classes/interfaces.

Após isso, é criado um metodo anotado com `@Bean` que irá retornar uma instância/Tipo da Classe que se deseja fazer a
Injeção de Dependencia.
