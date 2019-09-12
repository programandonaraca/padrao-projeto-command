import java.util.Scanner;

import servicos.Invocador;

public class Principal {

	public static void main(String[] args) throws Exception {
		
		String comando = "";
		Scanner entrada = new Scanner(System.in);
		Invocador inv = new Invocador();
		
		while(!comando.equals("sair")){
			
			comando = entrada.nextLine();
			inv.receberComando(comando);
			
		}
		
		entrada.close();
		
	}
	
	
}
