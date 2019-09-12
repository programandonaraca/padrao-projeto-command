package comandos;

import anotacoes.Comando;
import anotacoes.Modulo;
import interfaces.Command;

@Modulo("cadastro")
public class Cadastro implements Command{

	@Comando("cadastrar")
	public void cadastrar() {
		System.out.println("foi cadastrado");
	}
	
	@Comando("remover")
	public void remover() {
		System.out.println("foi deletado");
	}
	
	@Override
	public String carregar() {
		return this.getClass().getCanonicalName();
	}
	
}
