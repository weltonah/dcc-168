package Colorir;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.com.capivara.Ast;
import br.com.capivara.BuscaXml;
import br.com.capivara.Colorir;
import br.com.grafo.Cobertura;
import br.com.grafo.Grafo;



public class TestColorir2 {
	private static ArrayList<Cobertura> lista;
	final static String root = System.getProperty("user.dir")+"/src/main/resources/";
	private static Grafo grafo;
	private static String nomeClasse;
	private static Colorir colorir;
	private static Ast ast;
	@BeforeClass
	public static void test() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		BuscaXml bus = new BuscaXml();
		File file = new File(root + "coberturacalc2.xml");
		InputStream stream;
		stream = new FileInputStream(file);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document document = docBuilder.parse(stream);
		//lista = bus.LinhasCobertas(document, "TesteColorir.java");
		String caminho = root + "TesteColorirCalculadora.java";		
		ast = new Ast(caminho);
		grafo = ast.criarGrafo();					
		//(integra��o)  grafo = leitura.leituraArvore(caminho);		
		colorir = new Colorir();
		colorir.ColorirGrafo(document, grafo , "TesteColorirCalculadora.java");	
	}
	
	
	@Test
	public void testeColorir() {
		
		assertEquals(true, grafo.getFolha(0).isCobertura());
	
	}
	   
	@Test
	public void testeColorir2() {
		
		assertEquals(true, grafo.getFolha(1).isCobertura());
	
	}
	
	@Test
	public void testeColorir3() {
		
		assertEquals(false, grafo.getFolha(2).isCobertura());
	
	}
	   
	@Test
	public void testeColorir4() {
		
		assertEquals(false, grafo.getFolha(3).isCobertura());
	
	}
	@Test
	public void testeColorir5() {
		
		assertEquals(false, grafo.getFolha(4).isCobertura());
	
	}
	   
	@Test
	public void testeColorir6() {
		
		assertEquals(false, grafo.getFolha(5).isCobertura());
	
	}
	@Test
	public void testeColorir7() {
		
		assertEquals(false, grafo.getFolha(6).isCobertura());
	
	}
	   
	@Test
	public void testeColorir8() {
		
		assertEquals(false, grafo.getFolha(7).isCobertura());
	
	}
	@Test
	public void testeColorir9() {
		
		assertEquals(false, grafo.getFolha(8).isCobertura());
	
	}
	   
	@Test
	public void testeColorir10() {
		
		assertEquals(false, grafo.getFolha(9).isCobertura());
	
	}
	@Test
	public void testeColorir11() {
		
		assertEquals(false, grafo.getFolha(10).isCobertura());
	
	}
	   
	@Test
	public void testeColorir12() {
		
		assertEquals(false, grafo.getFolha(11).isCobertura());
	
	}
	@Test
	public void testeColorir13() {
		
		assertEquals(true, grafo.getFolha(12).isCobertura());
	
	}
	   
	@Test
	public void testeColorir14() {
		
		assertEquals(true, grafo.getFolha(13).isCobertura());
	
	}
	@Test
	public void testeColorir15() {
		
		assertEquals(true, grafo.getFolha(14).isCobertura());
	
	}
	   
	@Test
	public void testeColorir16() {
		
		assertEquals(true, grafo.getFolha(15).isCobertura());
	
	}
	@Test
	public void testeColorir17() {
		
		assertEquals(false, grafo.getFolha(16).isCobertura());
	
	}
	   
	@Test
	public void testeColorir18() {
		
		assertEquals(true, grafo.getFim().isCobertura());
	
	}
	
	
	
	
	
}
