
public class Main {

	public static void main(String[] args) {
		Aplicativo instagram = new Aplicativo("Instagram");
		Aplicativo whatsapp = new Aplicativo("Whatsapp");
		Aplicativo americanas = new Aplicativo("Lojas Americanas");
		
		SistemaOperacional android = new SistemaOperacional("Android");
		SistemaOperacional ios = new SistemaOperacional("IOS");
		
		instagram.addObserver(android);
		instagram.addObserver(ios);
		
		whatsapp.addObserver(android);
		whatsapp.addObserver(ios);
		
		americanas.addObserver(android);
		
		instagram.enviarNotificacao("Nova versão disponível, clique aqui para atualizar");
		whatsapp.enviarNotificacao("Fulano enviou uma mensagem");
		americanas.enviarNotificacao("Um item na sua lista de desejos está em promoção");


	}
}
