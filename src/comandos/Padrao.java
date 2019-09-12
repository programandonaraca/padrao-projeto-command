package comandos;

import anotacoes.Comando;
import anotacoes.Modulo;
import interfaces.Command;

@Modulo("padrao")
public class Padrao implements Command {
	
	@Comando("imprimir")
	public void imprimir() {
		System.out.println("olá");
	}
	
	@Comando("ajuda")
	public void imprimirAjuda() {
		System.out.println("precisando de ajuda?");
	}
	
	@Override
	public String carregar() {
		return this.getClass().getCanonicalName();
	}
}
