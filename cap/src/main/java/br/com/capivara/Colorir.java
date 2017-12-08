package br.com.capivara;

import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import br.com.grafo.Cobertura;
import br.com.grafo.Folha;
import br.com.grafo.Grafo;

public class Colorir {

	// Metodo que colore os nos do Grafo
	public void ColorirGrafo(Document doc, Grafo gra, String NomeClasse) {
		BuscaXml bus = new BuscaXml();
		try {
			// Busca um Array onde possui as linhas cobertas do grafo
			ArrayList<Cobertura> listaColorir = bus.LinhasCobertas(doc, NomeClasse);
			ArrayList<Folha> listaFolhas = gra.GetHashMap();
			// percorre o grafo colorindo as Folhas Cobertas
			for (Cobertura cob : listaColorir) {
				for (Folha f : listaFolhas) {
					if ((cob.getNr() == f.getLinha()) && cob.getCi() > 0) {
						f.setCobertura(true);
					} else {
						for (Folha aux : f.getMetodosInternos()) {
							if ((cob.getNr() == aux.getLinha()) && cob.getCi() > 0) {
								f.setCobertura(true);
							}
						}

					}

				}
			}
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
