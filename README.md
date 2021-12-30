# observerMediator
## Observer: 

O padrão observer cria uma relação de um para muitos, no qual o um é representado pela classe a ser monitorada (que deve herdar da classe observable) e o muito é representado pelos observers que são adicionados a classe a ser monitorada. Com isso, a cada mudança de estado ou ação tomada na classe a ser monitorada, todos os seus observers são notificados e a partir daí tomam alguma ação necessária. 

Para melhor explicar o padrão de projeto, implementamos um exemplo utilizando JAVA, no qual é retradado o envio de uma notificação por parte de
um aplicativo, e o recebimento das notificações por parte de diversos sistemas operacionais.

Foram implementadas a interface `Observer` e a classe abstrata `Observable`:

Observer (interface):

```java
public interface Observer {
	public void update(String s);
}
``` 
Observable (abstract class):

```java
import java.util.List;
import java.util.ArrayList;

public abstract class Observable {

	private List<Observer> observers = new ArrayList<Observer>();
	private String info;
	
	public void addObserver(Observer o) {
		this.observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}
	
	public void updateInfo(String info) {
		this.info = info;
		this.observers.forEach(observer -> observer.update(info));
	}

}
```

Apos isso foram criadas as classes `SistemaOperacional` e `Aplicativo` que extendem respectivamente de `Observer` e `Observable`:
SistemaOperacional (Observer):

```java
public class SistemaOperacional implements Observer {

	private String nome;
	
	public SistemaOperacional(String nome) {
		this.nome = nome;
	}
	
	private void receberNotificacao(String notificacao) {
		System.out.println("O sistema operacional " + this.nome + " recebeu a seguinte notificação");
		exibirNotificacao(notificacao);
	}
	
	private void exibirNotificacao(String notificacao) {
		System.out.println(notificacao);
	}
	
	@Override
	public void update(String info) {
		this.receberNotificacao(info);
	}
}
```
Aplicativo (Observable):

```java
public class Aplicativo extends Observable {

	private String nome;
	
	public Aplicativo(String nome) {
		this.nome = nome;
	}
		
	public void enviarNotificacao(String s) { 
		
		System.out.println("\nO aplicativo " + nome + " enviou a notificação: " + s);
		this.updateInfo(this.nome + ": " + s);
	}
	
}
```

A lógica por trás da comunicação é que os Observables possuem internamente uma lista contendo todos os seus observadores, e a partir dessa lista
podemos notificar facilmente os observadores após alguma alteração ou atualização acontecer dentro do objeto. Os observers, por sua vez, implementam um
método update, que é chamado sempre que os observados enviam uma nova atualização.

No nosso código, quando um aplicativo envia uma notificação, o método responsável notifica todos os observadores ali presentes
Os observadores do aplicativo recebem a mensagem, e mostram a notificação

### Testes
![alt text](https://user-images.githubusercontent.com/50862193/147714480-cda9bfc8-1430-4241-aff4-5cc1dccb155d.png "Teste Observer")

### Explicação dos testes
São criados 3 aplicativos (observables), instagram, whatsapp e americanas
São criados 2 sistemas operacionais (observers), android e ios

os observers são adicionados ao aplicativo
instagram e whatsapp possuem tanto android quanto ios como observers
americanas possui apenas android

as notificações são enviadas por seus respectivos aplicativos
e recebidas pelos observadores que estão contidos neles

## Mediator
O Mediator é um design pattern que nos ajuda a "desprender" o código. Por exemplo, quando temos um botão que faz com que sejam realizadas mais três outras ações, precisamos instanciar e usar as respectivas classes que fazem essas ações. Isso causa dependências entre classes (uma depende da outra pra ter o funcionamento correto).

A ideia do Mediator é remover essas dependências entre os objetos usando um objeto que fica entre eles. Seguindo a lógica do botão, agora ele só dependeria do Mediator. Reduzindo as dependências, tornamos o código mais fácil de modificar, estender e se reutilizar.

Exemplo criado: um aeroporto, onde temos aviões e uma torre de comando. A torre de comando faz o intermedeia comunicação entre os aviões. Começamos criando então duas interfaces: `Plane` e `Mediator`. Elas definem o componente que será mediado e o mediador.

```go
type Plane interface {
	takeOff()
	land()
}
```
Na interface `Plane`, temos as ações básicas do componente.

```go
type Mediator interface {
	canLand(Plane) bool
	notifyTakeOff()
}
```

Já no `Mediator`, temos esses dois métodos. Aqui é onde fazemos a administração das ações de cada componente. Se um avião quer pousar, ele deve comunicar a torre e deve perguntar se a pista está livre (`canLand(Plane)`). Se um avião vai sair, deve comunicar a torre que está saindo da pista (`notifyTakeOff()`), e etc.

Feito as interfaces, podemos partir pra fazer classes concretas. Assim, temos:
```go
type airportTower struct {
	isRunwayClear bool
	planeQueue []Plane
}

func NewAirportTower() *airportTower {
	return &airportTower{
		isRunwayClear: true,
	}
}

func (a *airportTower) canLand(p Plane) bool {
	if a.isRunwayClear {
		a.isRunwayClear = false
		return true
	}
	a.planeQueue = append(a.planeQueue, p)
	return false
}

func (a *airportTower) notifyTakeOff() {
	if !a.isRunwayClear {
		a.isRunwayClear = true
	}
	if len(a.planeQueue) > 0 {
		firstPlane := a.planeQueue[0]
		a.planeQueue = a.planeQueue[1:]
		firstPlane.land()
	}
}
```
Esta seria a classe responsável por mediar os aviões. Perceba que em sua definição, temos um atributo que guarda os aviões que estão esperando para aterrissar. Para que o Mediator possa administrar todos os elementos da interação, ele precisa acessá-los. Além disso, temos o método de criação do objeto, um método que administra se o pouso é permitido (e se não, guarda o avião numa _queue_) e o método `notifyTakeOff()`, que será explicado mais a frente.

Logo após, temos o `Jet` e o `Boeing` (como suas estruturas são parecidas, usarei somente o código de um deles).

```go
type Boeing struct {
	mediator Mediator
}

func NewBoeing(newMediator Mediator) *Boeing {
	return &Boeing{
		mediator: newMediator,
	}
}

func (b *Boeing) takeOff() {
	fmt.Println("Boeing taking off...")
	b.mediator.notifyTakeOff()
}

func (b *Boeing) land() {
	if b.mediator.canLand(b) {
		fmt.Println("Boeing landing...")
		return
	}
	fmt.Println("Boeing landing delayed: waiting...")
}
```

Perceba que o mesmo princípio de acesso se aplica também ao componente que utiliza o mediador: em sua classe, precisamos ter o atributo do mediador para que ele consiga o utilizar. Analisando melhor os métodos dessa classe, percebemos que toda a ação é mediada pelo `mediator`. 

Com a classe do avião explicada, podemos compreender totalmente o que acontece no método `notifyTakeOff()`: após decolar, o `Boeing` notifica a `airportTower` de que ele saiu, e assim a mesma libera a pista para que o primeiro avião na fila pouse (`firstPlane.land()`). Dessa forma, as ações dos componentes são mediadas eficientemente e problemas são evitados.

### Testes
![alt text](https://github.com/STAFF-Group/observerMediator/blob/main/Imagens/TesteMediator.png?raw=true "Teste Mediator")
