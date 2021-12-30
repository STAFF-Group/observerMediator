
public class Aplicativo extends Observable {

	private String nome;
	
	public Aplicativo(String nome) {
		this.nome = nome;
	}
		
	public void enviarNotificacao(String s) { 
		
		System.out.println("\nO aplicativo " + nome + " enviou a notifica��o: " + s);
		this.updateInfo(this.nome + ": " + s);
	}
	
}
