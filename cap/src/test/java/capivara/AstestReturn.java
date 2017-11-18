package capivara;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.capivara.Ast;
import br.com.grafo.Folha;
import br.com.grafo.Grafo;

public class AstestReturn {

	final static String root = System.getProperty("user.dir")+"/src/main/resources/";
	private static Ast ast;
	private static Grafo graf;
	@BeforeClass
	public static void inicio() {
		String caminho = root+"testeReturn.java";
		ast = new Ast(caminho);
		graf = ast.criarGrafo();
	}
	@Test
	public void Nome() {
		Folha raiz = graf.getRaiz();
		assertEquals("int exemploRetunr (int y)",raiz.getTexto());
	}
	@Test
	public void no0() {
		assertEquals("int exemploRetunr (int y)",graf.getFolha(0).getTexto());
	}
	@Test
	public void no1() {
		assertEquals("if(a < b)",graf.getFolha(1).getTexto());
	}
	@Test
	public void no2() {
		assertEquals("return b;",graf.getFolha(2).getTexto());
	}
	@Test
	public void no3() {
		assertEquals("b = c;",graf.getFolha(3).getTexto());
	}
	@Test
	public void no4() {
		assertEquals("if(a == c)",graf.getFolha(4).getTexto());
	}
	@Test
	public void no5() {
		assertEquals("a++;",graf.getFolha(5).getTexto());
	}
	@Test
	public void no6() {
		assertEquals("b = c - a;",graf.getFolha(6).getTexto());
	}
	@Test
	public void no7() {
		assertEquals("return c;",graf.getFolha(7).getTexto());
	}
	@Test
	public void no8() {
		assertEquals("if(c > 900)",graf.getFolha(8).getTexto());
	}
	@Test
	public void no9() {
		assertEquals("return c + 4;",graf.getFolha(9).getTexto());
	}
	@Test
	public void no10() {
		assertEquals("int i = 0",graf.getFolha(10).getTexto());
	}
	@Test
	public void no11() {
		assertEquals("for(i < 60)",graf.getFolha(11).getTexto());
	}
	@Test
	public void no12() {
		assertEquals("if(i == 8)",graf.getFolha(12).getTexto());
	}
	@Test
	public void no13() {
		assertEquals("return i;",graf.getFolha(13).getTexto());
	}
	@Test
	public void no14() {
		assertEquals("i++",graf.getFolha(14).getTexto());
	}
	@Test
	public void no15() {
		assertEquals("while(b >= c)",graf.getFolha(15).getTexto());
	}
	@Test
	public void no16() {
		assertEquals("while(b == y)",graf.getFolha(16).getTexto());
	}
	@Test
	public void no17() {
		assertEquals("if(c == 6)",graf.getFolha(17).getTexto());
	}
	@Test
	public void no18() {
		assertEquals("return b + c;",graf.getFolha(18).getTexto());
	}
	@Test
	public void no19() {
		assertEquals("a = a * b;",graf.getFolha(19).getTexto());
	}
	@Test
	public void no20() {
		assertEquals("return y;",graf.getFolha(20).getTexto());
	}

}
