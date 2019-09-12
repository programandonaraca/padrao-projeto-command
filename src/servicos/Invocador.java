package servicos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import anotacoes.Comando;
import modulos.Modulos;

public class Invocador {

	private List<Modulos> parametros = Arrays.asList(Modulos.values());

	/*
	 * recebe os argumentos os divide em um array, onde a primeria parte é nome do módulo
	 * e o segundo é a ação a ser invocada
	 */
	public void receberComando(String parametro) {
		if(parametro.contains(" ")){
			String temp[] = parametro.split(" ");
			String moduloNome = filtraParametro(temp);
			selecionarModulo(moduloNome, temp[1]);
		}else {
			System.out.println("comando inválido!");
		}
		
	}
	
	/* procura um módulo registrado de mesmo nome que o parametro passado
	 * como argumento
	 */
	private String filtraParametro(String[] parametro) {
		return parametros.stream()
		.filter( p -> p.toString().equalsIgnoreCase(parametro[0]))
		.findAny()
		.get().getNomeClasse();
	}
	
	/*
	 * seleciona um módulo passando a ação que vai ser executada
	 */
	private void selecionarModulo(String modulo, String acao) {
		try {
			invocarComando(modulo, acao);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * por reflexão a ação é procurada dentro da classe referenciada pelo módulo,
	 * caso seja encontrada, a classe é instanciada e o método invocado  
	 */
	private void invocarComando(String modulo, String parametro)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		Class clazz = Class.forName(modulo);
		for(Method m : clazz.getDeclaredMethods()) {
			if(m.isAnnotationPresent(Comando.class) && m.getAnnotation(Comando.class).value().equals(parametro)) {
				Object tempObject = clazz.newInstance();
				m.invoke(tempObject);
			}
		}
	}

}
