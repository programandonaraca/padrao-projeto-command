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
	 * recebe os argumentos os divide em um array, onde a primeria parte � nome do m�dulo
	 * e o segundo � a a��o a ser invocada
	 */
	public void receberComando(String parametro) {
		if(parametro.contains(" ")){
			String temp[] = parametro.split(" ");
			String moduloNome = filtraParametro(temp);
			selecionarModulo(moduloNome, temp[1]);
		}else {
			System.out.println("comando inv�lido!");
		}
		
	}
	
	/* procura um m�dulo registrado de mesmo nome que o parametro passado
	 * como argumento
	 */
	private String filtraParametro(String[] parametro) {
		return parametros.stream()
		.filter( p -> p.toString().equalsIgnoreCase(parametro[0]))
		.findAny()
		.get().getNomeClasse();
	}
	
	/*
	 * seleciona um m�dulo passando a a��o que vai ser executada
	 */
	private void selecionarModulo(String modulo, String acao) {
		try {
			invocarComando(modulo, acao);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * por reflex�o a a��o � procurada dentro da classe referenciada pelo m�dulo,
	 * caso seja encontrada, a classe � instanciada e o m�todo invocado  
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
