package br.com.capivara;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import br.com.grafo.Cobertura;

public class BuscaXml {
	
	// Função que retorna as Folhas que possuem cobertura
	public ArrayList<Cobertura> LinhasCobertas(Document document, String NomeClasse) throws XPathExpressionException{
		ArrayList<Cobertura> lista = new ArrayList<Cobertura>();
		XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        // realiza busca em XMl com Xpath
        String aux = "//sourcefile[@name='"+NomeClasse+"']/line";
        XPathExpression expr = xpath.compile(aux);
        NodeList Linhas = (NodeList) expr.evaluate(document, XPathConstants.NODESET);	
        //Percorre o resultado
        for(int i = 0; i < Linhas.getLength(); i++) {
        	Cobertura corb = new Cobertura();
        	Node artigoNode = Linhas.item(i);
        	// Coloca o numero da cobertura vistas instrumentação
        	corb.setCi(Integer.parseInt(artigoNode.getAttributes().getNamedItem("ci").getTextContent()));
        	// Coloca o numero de coberturas perdidas na intrumentação
        	corb.setMi(Integer.parseInt(artigoNode.getAttributes().getNamedItem("mi").getTextContent()));
        	// Coloca o numero da linha
        	corb.setNr(Integer.parseInt(artigoNode.getAttributes().getNamedItem("nr").getTextContent()));
        	lista.add(corb);
        }
		return lista;
	}
}
