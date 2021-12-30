
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
