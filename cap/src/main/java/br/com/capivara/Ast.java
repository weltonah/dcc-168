package br.com.capivara;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import br.com.grafo.Folha;
import br.com.grafo.Grafo;

public class Ast {
	private CompilationUnit ast;

	public Ast(String caminho) {
		FileInputStream in;
		try {
			in = new FileInputStream(caminho);
			// parse the file
			ast = JavaParser.parse(in);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Ast.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public Grafo criarGrafo() {
		Grafo gra = null;
		List<Node> listnoUm = ast.getChildNodes().get(0).getChildNodes().get(1).getChildNodes();
		Folha e = new Folha();
		e.setTexto(listnoUm.get(2) + " " + listnoUm.get(0) + " (" + listnoUm.get(1) + ")");
		gra = new Grafo(e);
		List<Node> list = listnoUm.get(3).getChildNodes();

		// for (Node node : raiz) {
		// if (node.toString().contains("if") || node.toString().contains("for")
		// || node.toString().contains("while")) {
		// Folha f = new Folha();
		// BuscaProfundidade(node.getChildNodes(),gra.getRaiz(),f, gra);
		// break;
		// } else {
		// Folha f = new Folha();
		// f.setTexto(node.toString());
		// gra.addMetodosInternos(f);
		// }
		// }
		BuscaProfundidade(list, gra.getRaiz(), gra);

		return gra;
	}

	public void BuscaProfundidade(List<Node> list, Folha pai, Grafo gra) {

		ExplorarBusca(list, pai, gra);

	}

	public Folha ExplorarBusca(List<Node> list, Folha pai, Grafo gra) {
		Folha f = null;
		boolean flag = false;
		boolean flagRetorno = false;
		Folha paiAtual = pai;
		for (Node nod : list)
			System.out.println(nod.toString() + "            Filhos");
		for (int i = 0; i < list.size(); i++) {
			if (flagRetorno)
				f = null;
			if (f == null)
				f = new Folha();
			Node node = list.get(i);
			if (!node.toString().contains("if") && !node.toString().contains("for")
					&& !node.toString().contains("while")) {
				f.setTexto(node.toString());
				System.out.println(node.toString() + " variavel");

				if (!flag) {
					paiAtual.addMetodosInternos(f);
					if (paiAtual.getTexto() == null) {
						paiAtual.setTexto(node.toString());
					}
					System.out.println(paiAtual.getTexto() + " Pai Atual");
				} else {
					paiAtual = f;
					gra.addlista(paiAtual);
					paiAtual.addMetodosInternos(f);
					flag = false;
					System.out.println(paiAtual.getTexto() + " Se transformou em Pai");
				}
				flagRetorno = true;
				// f = null;

			} else {
				Folha e1 = null, e2 = null;
				if (node.toString().contains("if")) {
					AddIf(node.getChildNodes(), f);
					paiAtual.addFolha(f);
					gra.addlista(f);
					// System.out.println(node.getChildNodes().size()+ " /////");
					// for (Node nod : node.getChildNodes())
					// System.out.println(nod.toString()+ " %%%");
					if (node.getChildNodes().size() == 3) {
						Folha f1 = new Folha();
						Folha f2 = new Folha();
						f.addFolha(f1);
						gra.addlista(f1);
						f.addFolha(f2);
						gra.addlista(f2);
						e2 = ExplorarBusca(node.getChildNodes().get(2).getChildNodes(), f2, gra);
						e1 = ExplorarBusca(node.getChildNodes().get(1).getChildNodes(), f1, gra);
					} else {
						Folha f3 = new Folha();
						f.addFolha(f3);
						gra.addlista(f3);
						e1 = ExplorarBusca(node.getChildNodes().get(1).getChildNodes(), f3, gra);
					}

					if (e1 != null) {
						if (i + 1 != list.size()) {
							Folha f1 = new Folha();
							gra.getFolha(gra.getCont() - 1).addFolha(f1);
							System.out.println(e1.getFilhos().size());
							if (e2 != null) {
								gra.getFolha(gra.getCont() - 2).addFolha(f1);
							} else {
								f.addFolha(f1);
							}
							f = f1;
						}
					} else {
						f = null;
					}
				}
				if (node.toString().contains("while")) {
					AddWhile(node.getChildNodes(), f);
					paiAtual.addFolha(f);
					gra.addlista(f);

					Folha f3 = new Folha();
					f.addFolha(f3);
					gra.addlista(f3);
					e1 = ExplorarBusca(node.getChildNodes().get(1).getChildNodes(), f3, gra);

					if (e1 != null) {
						if (i + 1 != list.size()) {
							gra.getFolha(gra.getCont() - 1).addFolha(f);
							Folha f1 = new Folha();
							f.addFolha(f1);
							f = f1;
						}
					} else {
						f = null;
					}

				}
				 if(node.toString().contains("for")) {
					 for (Node nod : node.getChildNodes())
							System.out.println(nod.toString() + "            For");
					 
					 
				 }
				flag = true;
				flagRetorno = false;
			}
		}
		return f;

	}

	public void AddIf(List<Node> list, Folha pai) {
		pai.setTexto("if(" + list.get(0).toString() + ")");
		Folha e = new Folha();
		e.setTexto(list.get(0).toString());
		pai.addMetodosInternos(e);
	}

	public void AddWhile(List<Node> list, Folha pai) {
		pai.setTexto("while(" + list.get(0).toString() + ")");
		Folha e = new Folha();
		e.setTexto(list.get(0).toString());
		pai.addMetodosInternos(e);
	}
}
