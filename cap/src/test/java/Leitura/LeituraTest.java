package Leitura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import br.com.capivara.Leitura;
import br.com.grafo.Grafo;

public class LeituraTest {

	final static String root = System.getProperty("user.dir")+"/src/main/resources/";
	private Grafo grafo;
	private Leitura leitura;
	private Document doc;
	String caminhoJava,caminhoXML;

	@Before
	public void beforeTeste() {
		leitura = new Leitura();		
		caminhoJava = root+"testeFor.java";
		caminhoXML = root+"Mult.xml";
		grafo = leitura.leituraArvore(caminhoJava);
	}

	@Test
	public void testLeituraXMLSAXException() {
		doc = leitura.leituraXML(caminhoJava);
		assertNull(doc);
	}
	
	@Test
	public void testLeituraXML() {
		doc = leitura.leituraXML(caminhoXML);
		assertNotNull(doc);
	}
	
	@Test
	public void testLeituraXMLNotExist() {
		doc = leitura.leituraXML(root+"Multi.xml");
		assertNull(doc);
	}
		
	@Test
	public void testLinhaGrafo() {
		assertEquals("for(i < 10)",grafo.getFolha(1).getTexto());
		assertNotEquals("for(i < 10)", grafo.getFolha(0).getTexto());
	}

}
