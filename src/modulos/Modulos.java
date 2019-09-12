package modulos;

import comandos.Cadastro;
import comandos.Padrao;

public enum Modulos {
	
	CADASTRO (new Cadastro().carregar()),
	PADRAO (new Padrao().carregar());
	
	private String nomeClasse;
	
	private Modulos(String nomeClasse){
		this.nomeClasse = nomeClasse;
	}
	
	public String getNomeClasse() {
		return nomeClasse;
	}

	
}
