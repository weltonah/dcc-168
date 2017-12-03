package capivara;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.com.capivara.BuscaXml;
import br.com.grafo.Cobertura;

public class BuscaXmlTest {
	private static ArrayList<Cobertura> lista;
	final static String root = System.getProperty("user.dir")+"/src/main/resources/";
	@Before
	public void test() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		BuscaXml bus = new BuscaXml();
		File file = new File(root +"Mult.xml");
		InputStream stream;

		stream = new FileInputStream(file);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document document = docBuilder.parse(stream);
		lista = bus.LinhasCobertas(document, "mult.java");
	}
	@Test
	public void linhas1() {
		assertTrue(lista.get(0).getCi()>0);
	}
	@Test
	public void linhas2() {
		assertTrue(lista.get(1).getCi()>0);
	}
	@Test
	public void linhas3() {
		assertTrue(lista.get(2).getCi()>0);
	}
	@Test
	public void linhas4() {
		assertTrue(lista.get(3).getCi()>0);
	}
	@Test
	public void linhas5() {
		assertTrue(lista.get(4).getCi()>0);
	}
	@Test
	public void linhas6() {
		assertTrue(lista.get(5).getCi()==0);
	}
	@Test
	public void linhas7() {
		assertTrue(lista.get(6).getCi()>0);
	}
	@Test
	public void linhas8() {
		assertTrue(lista.get(7).getCi()>0);
	}

}
