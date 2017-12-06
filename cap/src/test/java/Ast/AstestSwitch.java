package Ast;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.capivara.Ast;
import br.com.grafo.Folha;
import br.com.grafo.Grafo;

public class AstestSwitch {

	final static String root = System.getProperty("user.dir")+"/src/main/resources/";
	private static Ast ast;
	private static Grafo graf;
	@BeforeClass
	public static void inicio() {
		String caminho = root+"testeSwitch.java";
		ast = new Ast(caminho);
		graf = ast.criarGrafo();
	}
	@Test
	public void Nome() {
		Folha raiz = graf.getRaiz();
		assertEquals("int testSwitch ()",raiz.getTexto());
	}
	@Test
	public void no0() {
		assertEquals("int testSwitch ()",graf.getFolha(0).getTexto());
	}
	@Test
	public void no1() {
		assertEquals("switch(b)",graf.getFolha(1).getTexto());
	}
	@Test
	public void no2() {
		assertEquals("1",graf.getFolha(2).getTexto());
	}
	@Test
	public void no3() {
		assertEquals("2",graf.getFolha(3).getTexto());
	}
	@Test
	public void no4() {
		assertEquals("3",graf.getFolha(4).getTexto());
	}
	@Test
	public void no5() {
		assertEquals("for(i < 10)",graf.getFolha(5).getTexto());
	}
	@Test
	public void no6() {
		assertEquals("b = c;",graf.getFolha(6).getTexto());
	}
	@Test
	public void no7() {
		assertEquals("break;",graf.getFolha(7).getTexto());
	}
	@Test
	public void no8() {
		assertEquals("4",graf.getFolha(8).getTexto());
	}
	@Test
	public void no9() {
		assertEquals("if(b < c)",graf.getFolha(9).getTexto());
	}
	@Test
	public void no10() {
		assertEquals("b++;",graf.getFolha(10).getTexto());
	}
	@Test
	public void no11() {
		assertEquals("d = 56;",graf.getFolha(11).getTexto());
	}
	@Test
	public void no12() {
		assertEquals("break;",graf.getFolha(12).getTexto());
	}
	@Test
	public void no13() {
		assertEquals("5",graf.getFolha(13).getTexto());
	}
	@Test
	public void no14() {
		assertEquals("return 3;",graf.getFolha(14).getTexto());
	}
	@Test
	public void no15() {
		assertEquals("System.out.println(d);",graf.getFolha(15).getTexto());
	}
	@Test
	public void no16() {
		assertEquals("return 5;",graf.getFolha(16).getTexto());
	}

}
