package Ast;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.capivara.Ast;
import br.com.grafo.Folha;
import br.com.grafo.Grafo;

public class AsttestWhile {

	final static String root = System.getProperty("user.dir")+"/src/main/resources/";
	private static Ast ast;
	private static Grafo graf;
	@BeforeClass
	public static void inicio() {
		String caminho = root+"testeWhile.java";
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
	@Test
	public void no1() {
		assertEquals("while(i < 10)",graf.getFolha(1).getTexto());
	}
	@Test
	public void no1Para2() {
		assertEquals("a = a + i;",graf.getFolha(1).getFilhos().get(0).getTexto());
	}
	@Test
	public void no2() {
		assertEquals("a = a + i;",graf.getFolha(2).getTexto());
	}
	@Test
	public void no2ParaNo1() {
		assertEquals("while(i < 10)",graf.getFolha(2).getFilhos().get(0).getTexto());
	}
	@Test
	public void no3() {
		assertEquals("int b = 9;",graf.getFolha(3).getTexto());
	}
	@Test
	public void no1Parano3() {
		assertEquals("int b = 9;",graf.getFolha(1).getFilhos().get(1).getTexto());
	}
	@Test
	public void no4() {
		assertEquals("while(j < 10)",graf.getFolha(4).getTexto());
	}
	@Test
	public void no8ParaNo4() {
		assertEquals("while(j < 10)",graf.getFolha(8).getFilhos().get(0).getTexto());
	}
	@Test
	public void no5() {
		assertEquals("a = a + i;",graf.getFolha(5).getTexto());
	}
	@Test
	public void no6() {
		assertEquals("while(k < 15)",graf.getFolha(6).getTexto());
	}
	@Test
	public void no7() {
		assertEquals("k++;",graf.getFolha(7).getTexto());
	}
	@Test
	public void no6Parano7() {
		assertEquals("k++;",graf.getFolha(6).getFilhos().get(0).getTexto());
	}
	@Test
	public void no8() {
		assertEquals("j++;",graf.getFolha(8).getTexto());
	}
	@Test
	public void no6Parano8() {
		assertEquals("j++;",graf.getFolha(6).getFilhos().get(1).getTexto());
	}
	@Test
	public void no9() {
		assertEquals("while(b < 10)",graf.getFolha(9).getTexto());
	}
	@Test
	public void no10() {
		assertEquals("b = b + i;",graf.getFolha(10).getTexto());
	}
	@Test
	public void no11() {
		assertEquals("while(b < 15)",graf.getFolha(11).getTexto());
	}
	@Test
	public void no12() {
		assertEquals(null,graf.getFolha(12).getTexto());
	}
	@Test
	public void no13() {
		assertEquals("System.out.println(a);",graf.getFolha(13).getTexto());
	}
	
}
