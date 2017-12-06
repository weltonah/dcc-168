package Ast;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.capivara.Ast;
import br.com.grafo.Folha;
import br.com.grafo.Grafo;

public class AstestTudo {
	final static String root = System.getProperty("user.dir")+"/src/main/resources/";
	private static Ast ast;
	private static Grafo graf;
	@BeforeClass
	public static void inicio() {
		String caminho = root+"testeTudo.java";
		ast = new Ast(caminho);
		graf = ast.criarGrafo();
	}
	@Test
	public void Nome() {
		Folha raiz = graf.getRaiz();
		assertEquals("int testeTudoo (int ti,String bb)",raiz.getTexto());
	}
	@Test
	public void no0() {
		assertEquals("int testeTudoo (int ti,String bb)",graf.getFolha(0).getTexto());
	}
	@Test
	public void no1() {
		assertEquals("for(i < 10)",graf.getFolha(1).getTexto());
	}
	@Test
	public void no2() {
		assertEquals("System.out.println(i)",graf.getFolha(2).getTexto());
	}
	@Test
	public void no2cont0() {
		assertEquals("i++",graf.getFolha(2).getMetodosInternos().get(1).getTexto());
	}
	@Test
	public void no3() {
		assertEquals("int a = 5;",graf.getFolha(3).getTexto());
	}
	@Test
	public void no4() {
		assertEquals("if(a > 35)",graf.getFolha(4).getTexto());
	}
	@Test
	public void no5() {
		assertEquals("if(b < 88)",graf.getFolha(5).getTexto());
	}
	@Test
	public void no6() {
		assertEquals("if(c == 100)",graf.getFolha(6).getTexto());
	}
	@Test
	public void no7() {
		assertEquals("int t = 0",graf.getFolha(7).getTexto());
	}
	@Test
	public void no8() {
		assertEquals("for(t < 25)",graf.getFolha(8).getTexto());
	}
	@Test
	public void no9() {
		assertEquals("t++",graf.getFolha(9).getTexto());
	}
	@Test
	public void no10() {
		assertEquals("int j = 0",graf.getFolha(10).getTexto());
	}
	@Test
	public void no11() {
		assertEquals("for(j < 88)",graf.getFolha(11).getTexto());
	}
	@Test
	public void no12() {
		assertEquals("if(b == 77)",graf.getFolha(12).getTexto());
	}
	@Test
	public void no13() {
		assertEquals("b = c;",graf.getFolha(13).getTexto());
	}
	@Test
	public void no14() {
		assertEquals("b = c - 1;",graf.getFolha(14).getTexto());
	}
	@Test
	public void no15() {
		assertEquals("j++",graf.getFolha(15).getTexto());
	}
	@Test
	public void no16() {
		assertEquals("int k = 0",graf.getFolha(16).getTexto());
	}
	@Test
	public void no17() {
		assertEquals("for(k < 75)",graf.getFolha(17).getTexto());
	}
	@Test
	public void no18() {
		assertEquals("if(b == 77)",graf.getFolha(18).getTexto());
	}
	
	@Test
	public void no19() {
		assertEquals("b = c;",graf.getFolha(19).getTexto());
	}
	@Test
	public void no20() {
		assertEquals("if(b == 800)",graf.getFolha(20).getTexto());
	}
	@Test
	public void no21() {
		assertEquals(null,graf.getFolha(21).getTexto());
	}
	@Test
	public void no22() {
		assertEquals("c = a * a;",graf.getFolha(22).getTexto());
	}
	@Test
	public void no23() {
		assertEquals("k++",graf.getFolha(23).getTexto());
	}
	@Test
	public void no24() {
		assertEquals("System.out.println(a);",graf.getFolha(24).getTexto());
	}
	@Test
	public void no25() {
		assertEquals("return ti;",graf.getFolha(25).getTexto());
	}
}
