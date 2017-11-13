package capivara;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.capivara.Ast;
import br.com.grafo.Folha;
import br.com.grafo.Grafo;

public class AstestFor {


	final static String root = "/home/welton/WorkspaceJAVA/trabalho Teste de Soft/cap/src/main/resources/";
	private static Ast ast;
	private static Grafo graf;
	@BeforeClass
	public static void inicio() {
		String caminho = root+"testeFor.java";
		ast = new Ast(caminho);
		graf = ast.criarGrafo();
	}
	@Test
	public void Nome() {
		Folha raiz = graf.getRaiz();
		assertEquals("void main (String[] args)",raiz.getTexto());
	}
	@Test
	public void no0() {
		assertEquals("int a = 0;",graf.getFolha(0).getMetodosInternos().get(0).getTexto());
	}
	@Test
	public void no00() {
		assertEquals("int i = 0;",graf.getFolha(0).getMetodosInternos().get(1).getTexto());
	}

}