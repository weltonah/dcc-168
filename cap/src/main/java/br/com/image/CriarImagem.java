package br.com.image;

import java.util.ArrayList;

// Using Swing's components and containers
import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import br.com.grafo.Folha;
import br.com.grafo.Grafo;
import br.com.grafo.ObjectosGrafoImagem;

/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class CriarImagem extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2707712944901661771L;

	//Classe principal que comanda a cobertura
	public CriarImagem(Grafo gra, boolean Cobertura) {
		super("Grafo");
		//cria estruta de grafo
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try {
			//preenche grafo com nos e vertices
			Busca(graph, gra, parent,Cobertura);
		} finally {
			graph.getModel().endUpdate();
		}
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}
	
	public void Busca(mxGraph graph, Grafo gra, Object parent, boolean cobertura) {
		// cria estruturas auxiliares
		ArrayList<Folha> lista = new ArrayList<Folha>();
		ArrayList<ObjectosGrafoImagem> listaObject = new ArrayList<ObjectosGrafoImagem>();
		int x = 700, y = 10;
		// Cria os Nós do grafo
		BuscaProfundidade(graph, x, y, lista, listaObject, 0, gra.getRaiz(), parent,cobertura);
		
		//Cria vertices do grafo
		for (ObjectosGrafoImagem objct : listaObject) {
			if (objct.getV2() != null) {
				graph.insertEdge(parent, null, "", objct.getV1(), objct.getV2());
			}
		}
	}
	// Busca recursiva criando os vertices
	public void BuscaProfundidade(mxGraph graph, int x, int y, ArrayList<Folha> listaTodosNos,
			ArrayList<ObjectosGrafoImagem> listaObject, int auxsize, Folha pai, Object parent, boolean cobertura) {
		if (!listaTodosNos.contains(pai)) {
			if (pai.getTexto() == null) {
				pai.setTexto("null");
			}
			String texto = pai.getTexto();
			int largura=1,comprimento=20;
			if(comprimento<pai.getTexto().length())
				comprimento=pai.getTexto().length();
			// preenche o No com as as linhas de codigo internas que estão presentes no Grafo
			for (int k = 0; k < pai.getMetodosInternos().size(); k++) {
				if (pai.getTexto().startsWith("if"))
					break;
				if (pai.getTexto().startsWith("while"))
					break;
				if (pai.getTexto().startsWith("for"))
					break;
				if (pai.getTexto().startsWith("switch"))
					break;
				if (pai.getMetodosInternos().get(k).getTexto().equals(pai.getTexto())) {
					continue;
				}
				if(comprimento<pai.getMetodosInternos().get(k).getTexto().length())
					comprimento=pai.getMetodosInternos().get(k).getTexto().length();
				texto = texto + "\n" + pai.getMetodosInternos().get(k).getTexto();
				largura++;
			}
			// Cria o Nó do Grafo 
			Object v1;
			if(cobertura) {
				if(pai.isCobertura()) {
					 v1 = graph.insertVertex(parent, null, texto, x, y, comprimento*6, largura*15, "fillColor=green;fontColor=white");
				}else {
					 v1 = graph.insertVertex(parent, null, texto, x, y, comprimento*6, largura*15, "fillColor=#e06666;fontColor=white");
				}
			}else {
				 v1 = graph.insertVertex(parent, null, texto, x, y, comprimento*6, largura*15);
			}
			// Cria vertice para o seguinte no
			listaTodosNos.add(pai);
			if (!(listaObject.size() == 0)) {
				listaObject.get(auxsize).setV2(v1);
			}
			// cria vertice para o No FIM
			if (pai.getTexto().equals("FIM")) {
				ObjectosGrafoImagem auxobject = new ObjectosGrafoImagem();
				auxobject.setV1(v1);
				auxobject.setFolhav1(pai);
				listaObject.add(auxobject);
			}
			y = y + 50;
			ArrayList<Folha> FilhosFolhas = pai.getFilhos();
			// Chama recursivamente para os filhos do No
			for (int i = 0; i < FilhosFolhas.size(); i++) {
				// if
				if (FilhosFolhas.size() == 2) {
					if (i == 0) {
						x = 740;
					} else {
						x = 640;
					}
				}
				ObjectosGrafoImagem auxobject = new ObjectosGrafoImagem();
				auxobject.setV1(v1);
				auxobject.setFolhav1(pai);
				listaObject.add(auxobject);
				auxsize = listaObject.size() - 1;
				BuscaProfundidade(graph, x, y, listaTodosNos, listaObject, auxsize, FilhosFolhas.get(i), parent, cobertura);

			}
		} else {
			// Cria vertice com nos já existentes anteriormente
			if (pai.getTexto().equals("FIM")) {
				for (ObjectosGrafoImagem obj : listaObject) {
					if (obj.getFolhav1().equals(pai)) {
						ObjectosGrafoImagem auxobject = new ObjectosGrafoImagem();
						auxobject.setV1(listaObject.get(auxsize).getV1());
						auxobject.setV2(obj.getV1());
						auxobject.setFolhav1(pai);
						listaObject.add(auxobject);
						break;
					}
				}
			} else {
				for (ObjectosGrafoImagem obj : listaObject) {
					if (obj.getFolhav1().equals(pai)) {
						ObjectosGrafoImagem auxobject = new ObjectosGrafoImagem();
						auxobject.setV1(listaObject.get(auxsize).getV1());
						auxobject.setV2(obj.getV1());
						auxobject.setFolhav1(pai);
						listaObject.add(auxobject);
						break;
					}
				}
			}
		}
	}

}
