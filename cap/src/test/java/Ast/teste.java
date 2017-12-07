package Ast;

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

import br.com.capivara.Ast;
import br.com.capivara.BuscaXml;
import br.com.capivara.Colorir;
import br.com.grafo.Cobertura;
import br.com.grafo.Grafo;

public class teste {

	final static String root = System.getProperty("user.dir")+"/src/main/resources/";
	private static Ast ast;
	private static Grafo graf;
	@Before
	public void test() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		BuscaXml bus = new BuscaXml();
		File file = new File(root +"Mult.xml");
		InputStream stream;

		stream = new FileInputStream(file);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document document = docBuilder.parse(stream);
		String caminho = root+"mult.java";
		ast = new Ast(caminho);
		graf = ast.criarGrafo();
		
		Colorir col = new Colorir();
		col.ColorirGrafo(document, graf, "mult.java");
		
	}
	@Test
	public void linhas1() {
		assertTrue(graf.getFolha(1).isCobertura());
	}

}
