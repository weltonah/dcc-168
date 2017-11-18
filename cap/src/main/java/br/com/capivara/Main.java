package br.com.capivara;

import br.com.grafo.Folha;

public class Main {
	public static void main(String[] args) {
		String code= "for() {"
				+ "}"
				+ "if(){ "
				+ "}";
		if(code.startsWith("if"))
			System.out.println("deu pau");
		if(code.startsWith("for"))
			System.out.println("deu certp");
	}
	
}
