package br.com.capivara;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import br.com.grafo.Grafo;

public class Leitura {
	// Função que transforma arquivo .Java em um Grafo
	public Grafo leituraArvore(String caminho) {
		Ast ast = new Ast(caminho);
		Grafo gra = ast.criarGrafo();
		return gra;
	}
	
	
	// Função que transforma uma File em um Document para ser manipulado por Xpath
	public Document leituraXML(String caminho) {
		File file = new File(caminho);
		InputStream stream;
		try {
			// Metodo que transforma File em Document
			stream = new FileInputStream(file);
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(stream);
			return document;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
