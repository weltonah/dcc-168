package br.com.capivara;

public class Leitura {
	public void leituraArvore(String caminho) {
		Ast ast = new Ast(caminho);
		ast.criarGrafo();
	}

}
