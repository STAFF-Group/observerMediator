# observerMediator
## Observer: 

O padrão observer cria uma relação de um para muitos, no qual o um é representado pela classe a ser monitorada (que deve herdar da classe observable) e o muito é representado pelos observers que são adicionados a classe a ser monitorada. Com isso, a cada mudança de estado ou ação tomada na classe a ser monitorada, todos os seus observers são notificados e a partir daí tomam alguma ação necessária. 

Para melhor explicar o padrão de projeto, implementamos um exemplo utilizando JAVA, no qual é retradado o envio de uma notificação por parte de
um aplicativo, e o recebimento das notificações por parte de diversos sistemas operacionais.

Foram implementadas 2 classes base, sendo elas Observer (interface) e Observable (abstract class), e suas respectivas extensões 
SistemaOperacional (Observer) e Aplicativo (Observable).

A lógica por trás da comunicação é que os Observables possuem internamente uma lista contendo todos os seus observadores, e a partir dessa lista
podemos notificar facilmente os observadores após alguma alteração ou atualização acontecer dentro do objeto. Os observers, por sua vez, implementam um
método update, que é chamado sempre que os observados enviam uma nova atualização.

No nosso código, quando um aplicativo envia uma notificação, o método responsável notifica todos os observadores ali presentes
Os observadores do aplicativo recebem a mensagem, e mostram a notificação

Explicação dos testes
São criados 3 aplicativos (observables), instagram, whatsapp e americanas
São criados 2 sistemas operacionais (observers), android e ios

os observers são adicionados ao aplicativo
instagram e whatsapp possuem tanto android quanto ios como observers
americanas possui apenas android

as notificações são enviadas por seus respectivos aplicativos
e recebidas pelos observadores que estão contidos neles

## Mediator: 

O padrão mediator funciona para evitar facilitar a manipulação de entidades e evitar caos na comunicação entre elas. Para isso é criado a interface mediator e todas as classes devem implementar essa interface. Com isso toda comunicação entre as classes deve ser feita por meio das funções da interface, com isso facilita e implementação e evita repetição de código.