# observerMediator
Observer: 

O padrão observer cria uma relação de um para muitos, no qual o um é representado pela classe a ser monitorada (que deve herdar da classe observable) e o muito é representado pelos observers que são adicionados a classe a ser monitorada. Com isso, a cada mudança de estado ou ação tomada na classe a ser monitorada, todos os seus observers são notificados e a partir daí tomam alguma ação necessária. 

Mediator: 

O padrão mediator funciona para evitar facilitar a manipulação de entidades e evitar caos na comunicação entre elas. Para isso é criado a interface mediator e todas as classes devem implementar essa interface. Com isso toda comunicação entre as classes deve ser feita por meio das funções da interface, com isso facilita e implementação e evita repetição de código.