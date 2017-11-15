package br.com.capivara;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
		gra.getFolha(gra.getCont() - 1).addFolha(gra.getFim());

		for (Folha folhaaux : gra.GetHashMap()) {
			System.out.println(folhaaux.getTexto() + "####");
			for (Folha folhaaux2 : folhaaux.getFilhos()) {
				System.out.println(folhaaux2.getTexto() + "$$$$$ 99999");
			}
			for (Folha folhaaux2 : folhaaux.getMetodosInternos()) {
				System.out.println(folhaaux2.getTexto() + " INTERNOS");
			}
		}
		AtualizarGrafo(gra);
		for (Folha folhaaux : gra.GetHashMap()) {
			System.out.println(folhaaux.getTexto() + "####");
			for (Folha folhaaux2 : folhaaux.getFilhos()) {
				System.out.println(folhaaux2.getTexto() + "$$$$$ 99999");
			}
			for (Folha folhaaux2 : folhaaux.getMetodosInternos()) {
				System.out.println(folhaaux2.getTexto() + " INTERNOS");
			}
		}
	}

	public void AtualizarGrafo(Grafo gra) {
		ArrayList<Folha> listFolha = new ArrayList<Folha>();
		for (Folha f : gra.GetHashMap()) {
			for (Folha f1 : f.getFilhos()) {
				if (f1.getTexto() == null) {
					listFolha.add(f1);
				}
			}
			for (Folha f1 : listFolha) {
				f.deletar(f1);
			}
		}
		for (Folha f1 : listFolha) {
			gra.deletar(f1);
		}

		// listFolha = new ArrayList<Folha>();
		// for (Folha f : gra.GetHashMap()) {
		// if(f.getFilhos().size()>1 && (!f.getTexto().contains("if")
		// && !f.getTexto().contains("for") && !f.getTexto().contains("while"))) {
		// for (Folha f1 : f.getFilhos()) {
		// if((!f1.getTexto().contains("if")
		// && !f1.getTexto().contains("for") && !f1.getTexto().contains("while")) ) {
		// listFolha.add(f1);
		// }
		// }
		// for (Folha f1 : listFolha) {
		// f.deletar(f1);
		// }
		// }
		// }
		//// for (Folha f1 : listFolha) {
		//// gra.deletar(f1);
		//// }

		for (int i = gra.GetHashMap().size() - 1; i > 0; i--) {
			Folha e = gra.GetHashMap().get(i);
			if (!e.getTexto().contains("if") && !e.getTexto().contains("for") && !e.getTexto().contains("while")) {
				int cont = 0;
				Folha f2 = null;
				for (Folha f : gra.GetHashMap()) {
					if ((!f.equals(e))) {
						for (Folha f1 : f.getFilhos()) {
							if (f1.equals(e)) {
								cont++;
								f2 = f;
							}
						}
					}
					if (cont > 1)
						break;
				}
				if ((cont == 1) && (!f2.getTexto().contains("if") && !f2.getTexto().contains("for")
						&& !f2.getTexto().contains("while"))) {
					System.out.println(f2.getTexto());
					System.out.println(e.getTexto() + "PPPPPPPPPPPPPP");
					f2.addMetodosInternos(e);
					for (Folha ty : e.getFilhos())
						f2.addFolha(ty);
					gra.deletar(e);
					f2.deletar(e);
				}
			}
		}

	}

	public Folha ExplorarBusca(List<Node> list, Folha pai, Grafo gra) {
		Folha f = null;
		boolean flag = false;
		boolean flagRetorno = false;
		for (Node nod : list)
			System.out.println(nod.toString() + "            Filhos");

		for (int i = 0; i < list.size(); i++) {
			for (Folha folhaaux : gra.GetHashMap()) {
				System.out.println(folhaaux.getTexto() + "####");
				for (Folha folhaaux2 : folhaaux.getFilhos()) {
					System.out.println(folhaaux2.getTexto() + "$$$$$ 99999");
				}
				for (Folha folhaaux2 : folhaaux.getMetodosInternos()) {
					System.out.println(folhaaux2.getTexto() + " INTERNOS");
				}
			}
			if (flagRetorno)
				f = null;
			if (f == null)
				f = new Folha();
			Node node = list.get(i);
			if (!node.toString().contains("if") && !node.toString().contains("for")
					&& !node.toString().contains("while")) {
				f.setTexto(node.toString());
				System.out.println(node.toString() + " variavel");
				if (node.toString().contains("return ")) {
					Folha rert = new Folha();
					gra.addlista(rert);
					pai.addFolha(rert);
					rert.addFolha(gra.getFim());
					System.out.println(" return ");
					return null;
				}
				if (!flag) {
					pai.addMetodosInternos(f);
					if (pai.getTexto() == null) {
						pai.setTexto(node.toString());
					}
					System.out.println(pai.getTexto() + " Pai Atual");
				} else {
					pai = f;
					gra.addlista(pai);
					pai.addMetodosInternos(f);
					flag = false;
					System.out.println(pai.getTexto() + " Se transformou em Pai");
				}
				flagRetorno = true;
				// f = null;

			} else {
				Folha e1 = null, e2 = null;
				if (node.toString().contains("if")) {
					AddIf(node.getChildNodes(), f);
					pai.addFolha(f);
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
					pai.addFolha(f);
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
				// if (node.toString().contains("for")) {
				// for (Node nod : node.getChildNodes())
				// System.out.println(nod.toString() + " For");
				//
				// System.out.println(gra.getCont() + "----------");
				// System.out.println("No unico");
				// Folha faux4 = new Folha();
				// faux4.setTexto(node.getChildNodes().get(0).toString());
				// // gra.addlista(faux4);
				// AddFor(node.getChildNodes(), f);
				// gra.addlista(f);
				// pai.addMetodosInternos(faux4);
				// pai.addFolha(f);
				//
				// Folha f3 = new Folha();
				// f.addFolha(f3);
				// gra.addlista(f3);
				// int t = gra.getCont();
				// System.out.println(f.getTexto() + " +++++++++++++++++++++");
				// e1 = ExplorarBusca(node.getChildNodes().get(3).getChildNodes(), f3, gra);
				// System.out.println(f3.getTexto() + " +++++++++++++++++++$$");
				// // System.out.println(e1.getTexto() + " +++++++++++++++++++**");
				// if (f3.getTexto() == null) {
				// f.deletar(f3);
				// gra.deletar(f3);
				// System.out.println("999999999");
				// System.out.println(node.getChildNodes().get(2).toString());
				// Folha faux = new Folha();
				// faux.setTexto(node.getChildNodes().get(2).toString());
				// gra.addlista(faux);
				// f.addFolha(faux);
				// faux.addFolha(f);
				// System.out.println(list.size() + " " + i + 1 + " 8888888888");
				// if (i + 1 <= list.size()) {
				// Folha f1 = new Folha();
				// f.addFolha(f1);
				// f = f1;
				// } else {
				// f = null;
				// }
				// } else {
				// // if (e1 != null) {
				// if (e1.getTexto() != null) {
				// System.out.println(e1.getTexto() + " }}}}}}}}}}}}}}}}}}}}}}}}}}");
				// Folha faux = new Folha();
				// faux.setTexto(node.getChildNodes().get(2).toString());
				// // gra.getFolha(gra.GetHashMap().indexOf(e1)).addFolha(faux);
				// gra.getFolha(gra.getCont() - 1).addFolha(faux);
				// gra.addlista(faux);
				// faux.addFolha(f);
				//
				// if (i + 1 <= list.size()) {
				// Folha f1 = new Folha();
				// f.addFolha(f1);
				// f = f1;
				// } else {
				// f = null;
				// }
				//
				// } else {
				//
				// System.out.println(e1.getTexto() + " ///////////////");
				// Folha faux = new Folha();
				// faux.setTexto(node.getChildNodes().get(2).toString());
				// f.addFolha(faux);
				// gra.addlista(faux);
				// faux.addFolha(f);
				//
				// if (i + 1 <= list.size()) {
				// Folha f1 = new Folha();
				// f.addFolha(f1);
				// f = f1;
				// } else {
				// f = null;
				// }
				// }
				// }
				if (node.toString().contains("for")) {
					for (Node nod : node.getChildNodes())
						System.out.println(nod.toString() + "            For");

					System.out.println(gra.getCont() + "----------");
					System.out.println("No unico");

					Folha DeclaracaoVariavel = new Folha();
					DeclaracaoVariavel.setTexto(node.getChildNodes().get(0).toString());
					// gra.addlista(faux4);
					
					if(NumeroPais(gra,f)==null) {
						pai.addFolha(DeclaracaoVariavel);
						gra.addlista(DeclaracaoVariavel);
						DeclaracaoVariavel.addFolha(f);
						gra.addlista(f);
					}else {
						pai.addMetodosInternos(DeclaracaoVariavel);
						pai.addFolha(f);
						gra.addlista(f);
					}
					AddFor(node.getChildNodes(), f);
					
					Folha DeclaracaoIncremento = new Folha();
					DeclaracaoIncremento.setTexto(node.getChildNodes().get(2).toString());
					//DeclaracaoIncremento.addFolha(f);

					Folha DeclaracaoInterna = new Folha();
					f.addFolha(DeclaracaoInterna);
					gra.addlista(DeclaracaoInterna);
					
					System.out.println(f.getTexto() + " +++++++++++++++++++++");
					e1 = ExplorarBusca(node.getChildNodes().get(3).getChildNodes(), DeclaracaoInterna, gra);
					System.out.println(DeclaracaoInterna.getTexto() + " +++++++++++++++++++$$");
					
					if (DeclaracaoInterna.getTexto() == null) {
						//nada
						if(e1 == null) {
							f.addFolha(DeclaracaoIncremento);
							DeclaracaoIncremento.addFolha(f);
						}else {
							//termina com if
							if(NumeroPais(gra,e1)==null) {
								
							}
							//termina com while
							else {
								NumeroPais(gra,e1).addFolha(DeclaracaoIncremento);
								gra.addlista(DeclaracaoIncremento);
								DeclaracaoIncremento.addFolha(f);
							}
						}
						
					}else {
						
					}
					
					
					
					
					// System.out.println(e1.getTexto() + " +++++++++++++++++++**");
					if (f3.getTexto() == null) {
						f.deletar(f3);
						gra.deletar(f3);
						System.out.println("999999999");
						System.out.println(node.getChildNodes().get(2).toString());
						Folha faux = new Folha();
						faux.setTexto(node.getChildNodes().get(2).toString());
						gra.addlista(faux);
						f.addFolha(faux);
						faux.addFolha(f);
						System.out.println(list.size() + " " + i + 1 + " 8888888888");
						if (i + 1 <= list.size()) {
							Folha f1 = new Folha();
							f.addFolha(f1);
							f = f1;
						} else {
							f = null;
						}
					} else {
						// if (e1 != null) {
						if (e1.getTexto() != null) {
							System.out.println(e1.getTexto() + " }}}}}}}}}}}}}}}}}}}}}}}}}}");
							Folha faux = new Folha();
							faux.setTexto(node.getChildNodes().get(2).toString());
							// gra.getFolha(gra.GetHashMap().indexOf(e1)).addFolha(faux);
							gra.getFolha(gra.getCont() - 1).addFolha(faux);
							gra.addlista(faux);
							faux.addFolha(f);

							if (i + 1 <= list.size()) {
								Folha f1 = new Folha();
								f.addFolha(f1);
								f = f1;
							} else {
								f = null;
							}

						} else {

							System.out.println(e1.getTexto() + " ///////////////");
							Folha faux = new Folha();
							faux.setTexto(node.getChildNodes().get(2).toString());
							f.addFolha(faux);
							gra.addlista(faux);
							faux.addFolha(f);

							if (i + 1 <= list.size()) {
								Folha f1 = new Folha();
								f.addFolha(f1);
								f = f1;
							} else {
								f = null;
							}
						}
					}
				}
				flag = true;
				flagRetorno = false;
				System.out.println("Fim de Ciclo");
			}
		}
		return f;

	}

	public Folha NumeroPais(Grafo gra, Folha e) {
		int cont = 0;Folha f2 = null;
		if (!e.getTexto().contains("if") && !e.getTexto().contains("for") && !e.getTexto().contains("while")) {
			for (Folha f : gra.GetHashMap()) {
				if ((!f.equals(e)) && (!f.getTexto().contains("if") && 
						!f.getTexto().contains("for") && !f.getTexto().contains("while"))) {
					for (Folha f1 : f.getFilhos()) {
						if (f1.equals(e)) {
							cont++;
							f2 = f;
						}
					}
				}
				if (cont > 1)
					break;
			}
		}
		if(cont>1)
			return null;
			else
			return f2;
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

	public void AddFor(List<Node> list, Folha pai) {
		pai.setTexto("for(" + list.get(1).toString() + ")");
		Folha e = new Folha();
		e.setTexto(list.get(1).toString());
		pai.addMetodosInternos(e);
	}
}
