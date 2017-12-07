package Ast;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.capivara.Ast;
import br.com.grafo.Folha;
import br.com.grafo.Grafo;

public class AstesteForWhileBreakContinue {

	final static String root = System.getProperty("user.dir")+"/src/main/resources/";
	private static Ast ast;
	private static Grafo graf;
	@BeforeClass
	public static void inicio() {
		String caminho = root+"testeForWhileBreakContinue.java";
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
		assertEquals("int i = 0",graf.getFolha(0).getMetodosInternos().get(1).getTexto());
	}
	@Test
	public void no1() {
		assertEquals("for(i < 10)",graf.getFolha(1).getTexto());
	}
	@Test
	public void no2() {
		assertEquals("if(i == 8)",graf.getFolha(2).getTexto());
	}
	@Test
	public void no3() {
		assertEquals("continue;",graf.getFolha(3).getTexto());
	}
	@Test
	public void no3para4() {
		assertEquals("i++",graf.getFolha(3).getFilhos().get(0).getTexto());
	}
	@Test
	public void no4() {
		assertEquals("i++",graf.getFolha(4).getTexto());
	}
	@Test
	public void no5() {
		assertEquals("a = a + i;",graf.getFolha(5).getTexto());
	}
	@Test
	public void no6() {
		assertEquals("for(j < 10)",graf.getFolha(6).getTexto());
	}
	@Test
	public void no7() {
		assertEquals("if(b > 10)",graf.getFolha(7).getTexto());
	}
	@Test
	public void no8() {
		assertEquals("break;",graf.getFolha(8).getTexto());
	}
	@Test
	public void no8para() {
		assertEquals("int d = 0;",graf.getFolha(8).getFilhos().get(0).getTexto());
	}
	@Test
	public void no9() {
		assertEquals("b = 90;",graf.getFolha(9).getTexto());
	}
	@Test
	public void no10() {
		assertEquals("j++",graf.getFolha(10).getTexto());
	}
	@Test
	public void no11() {
		assertEquals("int d = 0;",graf.getFolha(11).getTexto());
	}
	@Test
	public void no12() {
		assertEquals("while(b < 30)",graf.getFolha(12).getTexto());
	}
	@Test
	public void no13() {
		assertEquals("if(d == 10)",graf.getFolha(13).getTexto());
	}
	@Test
	public void no14() {
		assertEquals("continue;",graf.getFolha(14).getTexto());
	}
	@Test
	public void no14para15() {
		assertEquals("while(b < 30)",graf.getFolha(14).getFilhos().get(0).getTexto());
	}
	@Test
	public void no15() {
		assertEquals("d++;",graf.getFolha(15).getTexto());
	}
	@Test
	public void no16() {
		assertEquals("int k = 0;",graf.getFolha(16).getTexto());
	}
	@Test
	public void no17() {
		assertEquals("while(k < 30)",graf.getFolha(17).getTexto());
	}
	@Test
	public void no18() {
		assertEquals("if(k == 10)",graf.getFolha(18).getTexto());
	}
	@Test
	public void no19() {
		assertEquals("break;",graf.getFolha(19).getTexto());
	}
	@Test
	public void no19para21() {
		assertEquals("System.out.println(a);",graf.getFolha(19).getFilhos().get(0).getTexto());
	}
	@Test
	public void no20() {
		assertEquals("k++;",graf.getFolha(20).getTexto());
	}
	@Test
	public void no21() {
		assertEquals("System.out.println(a);",graf.getFolha(21).getTexto());
	}
}
