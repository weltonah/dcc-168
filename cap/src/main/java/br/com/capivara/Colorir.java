package br.com.capivara;

import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import br.com.grafo.Cobertura;
import br.com.grafo.Folha;
import br.com.grafo.Grafo;

public class Colorir {
	public void ColorirGrafo(Document doc, Grafo gra, String NomeClasse) {
		BuscaXml bus = new BuscaXml();
		try {
			ArrayList<Cobertura> listaColorir = bus.LinhasCobertas(doc, NomeClasse);
			System.out.println(listaColorir.size());
			ArrayList<Folha> listaFolhas = gra.GetHashMap();
			for(Folha f : listaFolhas) {
				for(Cobertura cob : listaColorir) {
					if(cob.getNr() == f.getLinha() && cob.getCi()>0) {
						f.setCobertura(true);
					}
				}
			}
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
