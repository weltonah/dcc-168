package capivara;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.capivara.Ast;
import br.com.grafo.Folha;
import br.com.grafo.Grafo;

public class AsttestIf {
	
	final static String root = "/home/welton/WorkspaceJAVA/trabalho Teste de Soft/cap/src/main/resources/";
	private static Ast ast;
	private static Grafo graf;
	@BeforeClass
	public static void inicio() {
		String caminho = root+"testeIf.java";
		ast = new Ast(caminho);
		graf = ast.criarGrafo();
	}
	@Test
	public void Nome() {
		Folha raiz = graf.getRaiz();
		assertEquals("void main (String[] args)",raiz.getTexto());
	}
	@Test
	public void PrimeiraFolha() {
		Folha raiz = graf.getRaiz();
		assertEquals("int a = 9;",graf.getFolha(0).getMetodosInternos().get(0).getTexto());
	}
	@Test
	public void no1() {
		Folha raiz = graf.getRaiz();
		assertEquals("if(a > 9)",graf.getFolha(1).getTexto());
	}
	@Test
	public void no1Parano2() {
		Folha raiz = graf.getRaiz();
		assertEquals("a = 100;",graf.getFolha(1).getFilhos().get(0).getTexto());
	}
	@Test
	public void No2() {
		Folha raiz = graf.getRaiz();
		assertEquals("a = 100;",graf.getFolha(2).getMetodosInternos().get(0).getTexto());
	}
	@Test
	public void No1ParaNo3() {
		assertEquals("a = -5;",graf.getFolha(1).getFilhos().get(1).getTexto());
	}
	@Test
	public void No3() {
		assertEquals("a = -5;",graf.getFolha(3).getMetodosInternos().get(0).getTexto());
	}
	@Test
	public void No2ParaNo4() {
		assertEquals("int b = 10;",graf.getFolha(2).getFilhos().get(0).getTexto());
	}
	@Test
	public void No3ParaNo4() {
		assertEquals("int b = 10;",graf.getFolha(3).getFilhos().get(0).getTexto());
	}
	@Test
	public void No4() {
		assertEquals("int b = 10;",graf.getFolha(4).getMetodosInternos().get(0).getTexto());
	}
	@Test
	public void No5() {
		assertEquals("if(a > 100 && b == 10 || a > b)",graf.getFolha(5).getTexto());
	}
	@Test
	public void No4ParaNo5() {
		assertEquals("if(a > 100 && b == 10 || a > b)",graf.getFolha(4).getFilhos().get(0).getTexto());
	}
	@Test
	public void No5ParaNo6() {
		assertEquals("b = 25;",graf.getFolha(5).getFilhos().get(0).getTexto());
	}
	@Test
	public void No6() {
		assertEquals("b = 25;",graf.getFolha(6).getTexto());
	}
	@Test
	public void No6Para7() {
		assertEquals("if(b == 11)",graf.getFolha(6).getFilhos().get(0).getTexto());
	}
	@Test
	public void No7() {
		assertEquals("if(b == 11)",graf.getFolha(7).getTexto());
	}
	@Test
	public void No7ParaNo8() {
		assertEquals("a = b;",graf.getFolha(7).getFilhos().get(0).getMetodosInternos().get(0).getTexto());
	}
	@Test
	public void No8() {
		assertEquals("a = b;",graf.getFolha(8).getMetodosInternos().get(0).getTexto());
	}
	@Test
	public void No8ParaNo9() {
		assertEquals("int d = 89;",graf.getFolha(8).getFilhos().get(0).getMetodosInternos().get(1).getTexto());
	}
	@Test
	public void No9() {
		assertEquals("int d = 89;",graf.getFolha(9).getMetodosInternos().get(1).getTexto());
	}
	@Test
	public void No5ParaNo9() {
		assertEquals("int d = 89;",graf.getFolha(5).getFilhos().get(1).getMetodosInternos().get(1).getTexto());
	}
	@Test
	public void No10() {
		assertEquals("if(c < d)",graf.getFolha(10).getTexto());
	}
	@Test
	public void No9ParaNo10() {
		assertEquals("if(c < d)",graf.getFolha(9).getFilhos().get(0).getTexto());
	}
	@Test
	public void No11() {
		assertEquals("c = 20;",graf.getFolha(11).getTexto());
	}
	@Test
	public void No10ParaNo12() {
		assertEquals("if(c < d - 1)",graf.getFolha(10).getFilhos().get(1).getTexto());
	}
	@Test
	public void No11ParaNo12() {
		assertEquals("if(c < d - 1)",graf.getFolha(11).getFilhos().get(0).getTexto());
	}
	@Test
	public void No12() {
		assertEquals("if(c < d - 1)",graf.getFolha(12).getTexto());
	}
	@Test
	public void No13() {
		assertEquals("c = c - 1;",graf.getFolha(13).getTexto());
	}
	@Test
	public void No14() {
		assertEquals("System.out.println(a);",graf.getFolha(14).getTexto());
	}
	
	

}
