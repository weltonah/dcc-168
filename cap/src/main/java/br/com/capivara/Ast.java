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

	// Cria a AST
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

	// Cria o Grafo e preenche o NO Raiz
	public Grafo criarGrafo() {
		Grafo gra = null;
		List<Node> listnoUm = ast.getChildNodes().get(ast.getChildNodes().size() - 1).getChildNodes().get(1)
				.getChildNodes();
		// Cria a Folha Raiz do Grafo que possui o nome do metodo
		Folha e = new Folha();
		String titulo = listnoUm.get(listnoUm.size() - 2) + " " + listnoUm.get(0) + " (";
		if (listnoUm.size() > 3) {
			titulo = titulo + listnoUm.get(1);
			for (int i = 2; i < listnoUm.size() - 2; i++) {
				titulo = titulo + "," + listnoUm.get(i);
			}
		}

		titulo = titulo + ")";
		e.setTexto(titulo);
		e.setLinha(
				ast.getChildNodes().get(ast.getChildNodes().size() - 1).getChildNodes().get(1).getBegin().get().line);
		gra = new Grafo(e);
		gra.setNomeMetodo(listnoUm.get(0).toString());
		List<Node> list = listnoUm.get(listnoUm.size() - 1).getChildNodes();
		BuscaProfundidade(list, gra.getRaiz(), gra);

		return gra;
	}

	// Inicia a Busca
	public void BuscaProfundidade(List<Node> list, Folha pai, Grafo gra) {
		Folha foo = new Folha();
		foo.setTexto("variavel");
		ExplorarBusca(list, pai, gra, foo, null);
		// corrige se o ultima Folha que foi adicionada foi um return
		if (!foo.getTexto().startsWith("return")) {
			gra.getFolha(gra.getCont() - 1).addFolha(gra.getFim());
		}
		// imprime o Grafo
		imprimirGrafo(gra);
	}

	// Busca de profundidade
	public Folha ExplorarBusca(List<Node> list, Folha pai, Grafo gra, Folha Ultimovalor, List<Folha> ContBreak) {
		Folha f = null;
		boolean flag = false;
		boolean flagRetorno = false;

		for (int i = 0; i < list.size(); i++) {
			if (flagRetorno)
				f = null;
			if (f == null)
				f = new Folha();
			Node node = list.get(i);

			if (!node.toString().startsWith("if") && !node.toString().startsWith("for")
					&& !node.toString().startsWith("while") && !node.toString().startsWith("switch")) {
				f.setTexto(node.toString());

				if (node.toString().startsWith("return")) {
					if (list.size() == 1) {
						pai.setLinha(node.getBegin().get().line);
						pai.setTexto(node.toString());
						pai.addFolha(gra.getFim());
						Ultimovalor.setTexto("return");
						return pai;
					} else {
						if (Ultimovalor.getTexto().contentEquals("variavel")) {
							f.setLinha(node.getBegin().get().line);
							pai.addFolha(f);
							f.setTexto(node.toString());
							f.addFolha(gra.getFim());
							gra.addlista(f);
							Ultimovalor.setTexto("return");
							return pai;
						} else {
							pai.setLinha(node.getBegin().get().line);
							pai.setTexto(node.toString());
							pai.addFolha(gra.getFim());
							Ultimovalor.setTexto("return");
							return pai;
						}
					}
				} else {
					if ((node.toString().startsWith("break") || node.toString().startsWith("continue"))
							&& ContBreak != null) {
						System.out.println("TESTESTRE" + node.toString() + "Linha: " + node.getBegin().get().line);
						if (list.size() == 1) {
							pai.setLinha(node.getBegin().get().line);
							pai.setTexto(node.toString());
							Ultimovalor.setTexto("ContBreak");
							ContBreak.add(pai);
							return pai;
						} else {
							if (Ultimovalor.getTexto().contentEquals("variavel")) {
								f.setLinha(node.getBegin().get().line);
								pai.addFolha(f);
								f.setTexto(node.toString());
								gra.addlista(f);
								ContBreak.add(f);
								Ultimovalor.setTexto("ContBreak");
								return pai;
							} else {
								pai.setLinha(node.getBegin().get().line);
								pai.setTexto(node.toString());
								Ultimovalor.setTexto("ContBreak");
								ContBreak.add(pai);
								return pai;
							}
						}
					} else {
						if (!gra.getRaiz().equals(pai) && pai.getLinha() == -1)
							pai.setLinha(node.getBegin().get().line);
						if (!flag) {
							pai.addMetodosInternos(f);
							if (pai.getTexto() == null) {
								pai.setTexto(node.toString());
							}
							f = pai;
						} else {
							pai = f;
							if (!gra.GetHashMap().contains(pai))
								gra.addlista(pai);
							pai.addMetodosInternos(f);
							flag = false;
							f = pai;
						}
					}
				}
				Ultimovalor.setTexto("variavel");
				flagRetorno = true;

			} else {
				Folha e1 = null, e2 = null;
				if (node.toString().startsWith("if")) {
					if (Ultimovalor.getTexto() == null) {
						f = pai;
					} else {
						if (Ultimovalor.getTexto().contentEquals("variavel")) {
							pai.addFolha(f);
							gra.addlista(f);
						} else {
						}
					}
					f.setLinha(node.getBegin().get().line);
					AddIf(node.getChildNodes(), f);
					Folha folhaFlag = new Folha();
					Folha folhaFlag2 = new Folha();
					if (node.getChildNodes().size() == 3) {
						Folha f1 = new Folha();
						Folha f2 = new Folha();
						f.addFolha(f1);
						gra.addlista(f1);
						f.addFolha(f2);
						gra.addlista(f2);
						if (node.getChildNodes().get(1).getChildNodes().size() != 0)
							e1 = ExplorarBusca(node.getChildNodes().get(1).getChildNodes(), f1, gra, folhaFlag,
									ContBreak);
						Folha f4 = new Folha();
						if (folhaFlag.getTexto() == null) {
							f1.addFolha(f4);
						} else {
							if (folhaFlag.getTexto().contentEquals("variavel")) {
								gra.getFolha(gra.buscaFolha(e1)).addFolha(f4);
							} else {
								if (e1.getTexto().contentEquals("vazio")) {
									for (Folha no : BuscarPai(gra, e1)) {
										no.addFolha(f4);
										no.deletar(e1);
									}
									gra.deletar(gra.getFolha(gra.buscaFolha(e1)));
								} else {
									if (!folhaFlag.getTexto().contentEquals("ContBreak"))
										gra.getFolha(gra.buscaFolha(e1)).addFolha(f4);
								}
							}
						}
						if (node.getChildNodes().get(2).getChildNodes().size() != 0)
							e2 = ExplorarBusca(node.getChildNodes().get(2).getChildNodes(), f2, gra, folhaFlag2,
									ContBreak);

						if (folhaFlag2.getTexto() == null) {
							f2.addFolha(f4);
						} else {
							if (folhaFlag2.getTexto().contentEquals("variavel")) {
								gra.getFolha(gra.buscaFolha(e2)).addFolha(f4);
							} else {
								if (folhaFlag.getTexto().contentEquals("return")) {

								} else {
									if (e2.getTexto().contentEquals("vazio")) {
										for (Folha no : BuscarPai(gra, e2)) {
											no.addFolha(f4);
											no.deletar(e2);
										}
										gra.deletar(gra.getFolha(gra.buscaFolha(e2)));
									} else {
										if (!folhaFlag.getTexto().contentEquals("ContBreak"))
											gra.getFolha(gra.buscaFolha(e2)).addFolha(f4);
									}
								}
							}
						}
						Ultimovalor.setTexto("if 2");
						f4.setTexto("vazio");
						gra.addlista(f4);
						f = f4;
						pai = f;
					} else {
						Folha f3 = new Folha();
						f.addFolha(f3);
						gra.addlista(f3);
						if (node.getChildNodes().get(1).getChildNodes().size() != 0)
							e1 = ExplorarBusca(node.getChildNodes().get(1).getChildNodes(), f3, gra, folhaFlag,
									ContBreak);
						Folha f4 = new Folha();

						if (folhaFlag.getTexto() == null) {
							f3.addFolha(f4);
						} else {
							if (folhaFlag.getTexto().contentEquals("variavel")) {
								gra.getFolha(gra.buscaFolha(e1)).addFolha(f4);
							} else {
								if (folhaFlag.getTexto().contentEquals("return")) {

								} else {
									if (e1.getTexto().contentEquals("vazio")) {
										for (Folha no : BuscarPai(gra, e1)) {
											no.addFolha(f4);
											no.deletar(e1);
										}
										gra.deletar(gra.getFolha(gra.buscaFolha(e1)));
									} else {
										if (!folhaFlag.getTexto().contentEquals("ContBreak"))
											gra.getFolha(gra.buscaFolha(e1)).addFolha(f4);
									}
								}
							}

						}
						f4.setTexto("vazio");
						f.addFolha(f4);
						Ultimovalor.setTexto("if 1");
						gra.addlista(f4);
						f = f4;
						pai = f;
					}
				} else {
					if (node.toString().startsWith("while")) {
						// veio de variavel
						if (Ultimovalor.getTexto() == null) {
							f = pai;
						} else {
							if (Ultimovalor.getTexto().contentEquals("variavel")) {
								pai.addFolha(f);
								gra.addlista(f);
							}
						}
						f.setLinha(node.getBegin().get().line);
						AddWhile(node.getChildNodes(), f);
						Folha f3 = new Folha();
						f.addFolha(f3);
						gra.addlista(f3);
						Folha folhaFlag = new Folha();
						List<Folha> listContBreak = new ArrayList<Folha>();
						if (node.getChildNodes().get(1).getChildNodes().size() != 0) {
							e1 = ExplorarBusca(node.getChildNodes().get(1).getChildNodes(), f3, gra, folhaFlag,
									listContBreak);
						}

						if (folhaFlag.getTexto() == null) {
							f3.addFolha(f);
						} else {
							if (folhaFlag.getTexto().contentEquals("variavel")) {
								gra.getFolha(gra.buscaFolha(e1)).addFolha(f);
							} else {
								if (folhaFlag.getTexto().contentEquals("return")) {

								} else {
									if (e1.getTexto().contentEquals("vazio")) {
										for (Folha no : BuscarPai(gra, e1)) {
											no.addFolha(f);
											no.deletar(e1);
										}
										gra.deletar(gra.getFolha(gra.buscaFolha(e1)));
									} else {
										if (!folhaFlag.getTexto().contentEquals("ContBreak"))
											gra.getFolha(gra.buscaFolha(e1)).addFolha(f);
									}
								}
							}
						}
						Folha f4 = new Folha();
						for (Folha y : listContBreak) {
							if (y.getTexto().contentEquals("continue;")) {
								y.addFolha(f);
							}
							if (y.getTexto().contentEquals("break;")) {
								y.addFolha(f4);
							}
						}

						Ultimovalor.setTexto("while");
						f4.setTexto("vazio");
						f.addFolha(f4);
						gra.addlista(f4);
						pai = f4;
						f = f4;
					} else {
						if (node.toString().startsWith("for")) {
							Folha DeclaracaoVariavel = new Folha();
							DeclaracaoVariavel.setTexto(node.getChildNodes().get(0).toString());

							if (Ultimovalor.getTexto() == null) {
								pai.setLinha(node.getBegin().get().line);
								pai.setTexto(node.getChildNodes().get(0).toString());
								pai.addMetodosInternos(DeclaracaoVariavel);
								Folha f8 = new Folha();
								f = f8;
								pai.addFolha(f);
								gra.addlista(f);
							} else {
								if (Ultimovalor.getTexto().contentEquals("variavel")) {
									pai.addMetodosInternos(DeclaracaoVariavel);
									pai.addFolha(f);
									gra.addlista(f);
								} else {
									pai.setLinha(node.getBegin().get().line);
									pai.setTexto(node.getChildNodes().get(0).toString());
									pai.addMetodosInternos(DeclaracaoVariavel);
									Folha f8 = new Folha();
									f = f8;
									pai.addFolha(f);
									gra.addlista(f);
								}
							}
							f.setLinha(node.getBegin().get().line);
							AddFor(node.getChildNodes(), f);
							Folha DeclaracaoIncremento = new Folha();
							DeclaracaoIncremento.setTexto(node.getChildNodes().get(2).toString());
							DeclaracaoIncremento.setLinha(node.getChildNodes().get(2).getBegin().get().line);
							Folha DeclaracaoInterna = new Folha();
							f.addFolha(DeclaracaoInterna);
							gra.addlista(DeclaracaoInterna);
							Folha folhaFlag = new Folha();
							DeclaracaoInterna.setLinha(node.getChildNodes().get(3).getBegin().get().line);
							List<Folha> listContBreak = new ArrayList<Folha>();
							if (node.getChildNodes().get(3).getChildNodes().size() != 0)
								e1 = ExplorarBusca(node.getChildNodes().get(3).getChildNodes(), DeclaracaoInterna, gra,
										folhaFlag, listContBreak);

							if (folhaFlag.getTexto() == null) {
								f.deletar(DeclaracaoInterna);
								gra.deletar(DeclaracaoInterna);
								f.addFolha(DeclaracaoIncremento);
								gra.addlista(DeclaracaoIncremento);
								DeclaracaoIncremento.addFolha(f);
							} else {
								if (folhaFlag.getTexto().contentEquals("while")
										|| folhaFlag.getTexto().contentEquals("for")) {
									if (e1.getTexto().contentEquals("vazio")) {
										if (listContBreak.size() == 0) {
											gra.getFolha(gra.buscaFolha(e1)).setTexto(DeclaracaoIncremento.getTexto());
											gra.getFolha(gra.buscaFolha(e1)).addFolha(f);
										} else {
											gra.getFolha(gra.buscaFolha(e1)).setTexto(DeclaracaoIncremento.getTexto());
											gra.getFolha(gra.buscaFolha(e1)).addFolha(f);
											DeclaracaoIncremento = gra.getFolha(gra.buscaFolha(e1));
										}
									} else {
										gra.getFolha(gra.buscaFolha(e1)).addFolha(DeclaracaoIncremento);
										gra.addlista(DeclaracaoIncremento);
										DeclaracaoIncremento.addFolha(f);
									}

								} else {
									if (folhaFlag.getTexto().contentEquals("variavel")
											|| folhaFlag.getTexto().contentEquals("variavel pai")) {
										if (listContBreak.size() == 0) {
											gra.getFolha(gra.buscaFolha(e1)).addMetodosInternos(DeclaracaoIncremento);
											gra.getFolha(gra.buscaFolha(e1)).addFolha(f);
										} else {
											gra.getFolha(gra.buscaFolha(e1)).setTexto(DeclaracaoIncremento.getTexto());
											gra.getFolha(gra.buscaFolha(e1)).addFolha(f);
											DeclaracaoIncremento = gra.getFolha(gra.buscaFolha(e1));
										}

									} else {
										if (folhaFlag.getTexto().contentEquals("return")) {

										} else {
											if (e1.getTexto().contentEquals("vazio")) {

												for (Folha no : BuscarPai(gra, e1)) {
													no.deletar(e1);
													no.addFolha(DeclaracaoIncremento);
												}
												gra.deletar(gra.getFolha(gra.buscaFolha(e1)));
												DeclaracaoIncremento.addFolha(f);

												gra.addlista(DeclaracaoIncremento);
											} else {
												if (!folhaFlag.getTexto().contentEquals("ContBreak")) {
													if (listContBreak.size() == 0) {
														gra.getFolha(gra.buscaFolha(e1))
																.addMetodosInternos(DeclaracaoIncremento);
														gra.getFolha(gra.buscaFolha(e1)).addFolha(f);
													} else {
														gra.getFolha(gra.buscaFolha(e1)).addFolha(DeclaracaoIncremento);
														gra.addlista(DeclaracaoIncremento);
													}

												} else {
													gra.addlista(DeclaracaoIncremento);
												}
											}
										}

									}
								}
							}
							Folha f4 = new Folha();
							for (Folha y : listContBreak) {
								if (y.getTexto().contentEquals("continue;")) {
									y.addFolha(DeclaracaoIncremento);
								}
								if (y.getTexto().contentEquals("break;")) {
									y.addFolha(f4);
								}
							}
							f4.setTexto("vazio");
							f.addFolha(f4);
							gra.addlista(f4);
							pai = f4;
							f = f4;
							Ultimovalor.setTexto("for");
						} else {
							if (node.toString().startsWith("switch")) {
								// veio de variavel
								if (Ultimovalor.getTexto() == null) {
									f = pai;
								} else {
									if (Ultimovalor.getTexto().contentEquals("variavel")) {
										pai.addFolha(f);
										gra.addlista(f);
									}
								}

								f.setLinha(node.getBegin().get().line);
								AddSwitch(node.getChildNodes(), f);
								Folha f4 = new Folha();
								for (int y = 1; y < node.getChildNodes().size(); y++) {
									Folha f3 = new Folha();
									f.addFolha(f3);
									gra.addlista(f3);
									Folha folhaFlag = new Folha();
									e1 = ExplorarBusca(node.getChildNodes().get(y).getChildNodes(), f3, gra, folhaFlag,
											null);
									if (folhaFlag.getTexto() == null) {
										f3.addFolha(f4);
									} else {
										if (folhaFlag.getTexto().contentEquals("return")) {
										} else {
											if (folhaFlag.getTexto().contentEquals("variavel")) {

												gra.getFolha(gra.buscaFolha(e1)).addFolha(f4);
											} else {
												if (e1.getTexto().contentEquals("vazio")) {
													for (Folha no : BuscarPai(gra, e1)) {
														no.addFolha(f4);
														no.deletar(e1);
													}
													gra.deletar(gra.getFolha(gra.buscaFolha(e1)));
												} else {
													if (!folhaFlag.getTexto().contentEquals("ContBreak")) {
														gra.getFolha(gra.buscaFolha(e1)).addFolha(f4);
													}
												}
											}
										}
									}
								}
								Ultimovalor.setTexto("switch");
								f4.setTexto("vazio");
								gra.addlista(f4);
								f = f4;
								pai = f;
							}
						}
					}
				}
				flag = true;
				flagRetorno = false;
			}
		}
		return f;
	}

	// Busca todos os Pais de um determinado NO
	public ArrayList<Folha> BuscarPai(Grafo gra, Folha e) {
		ArrayList<Folha> result = new ArrayList<Folha>();
		for (Folha folhaaux : gra.GetHashMap()) {
			for (Folha folhaaux2 : folhaaux.getFilhos()) {
				if (folhaaux2.equals(e)) {
					result.add(folhaaux);
				}
			}
		}
		return result;
	}

	// Imprime o Grafo
	public void imprimirGrafo(Grafo gra) {
		for (Folha folhaaux : gra.GetHashMap()) {
			System.out.println(folhaaux.getTexto() + "####    linha:" + folhaaux.getLinha());
			for (Folha folhaaux2 : folhaaux.getFilhos()) {
				System.out.println(folhaaux2.getTexto() + "$$$$$ 99999");
			}
			for (Folha folhaaux2 : folhaaux.getMetodosInternos()) {
				System.out.println(folhaaux2.getTexto() + " INTERNOS");
			}
		}
	}

	// Preenche o IF
	public void AddIf(List<Node> list, Folha pai) {
		pai.setTexto("if(" + list.get(0).toString() + ")");
		Folha e = new Folha();
		e.setTexto(list.get(0).toString());
		pai.addMetodosInternos(e);
	}

	// Preenche o While
	public void AddWhile(List<Node> list, Folha pai) {
		pai.setTexto("while(" + list.get(0).toString() + ")");
		Folha e = new Folha();
		e.setTexto(list.get(0).toString());
		pai.addMetodosInternos(e);
	}

	// Preenche o switch
	public void AddSwitch(List<Node> list, Folha pai) {
		pai.setTexto("switch(" + list.get(0).toString() + ")");
		Folha e = new Folha();
		e.setTexto(list.get(0).toString());
		pai.addMetodosInternos(e);
	}

	// Preenche o For
	public void AddFor(List<Node> list, Folha pai) {
		pai.setTexto("for(" + list.get(1).toString() + ")");
		Folha e = new Folha();
		e.setTexto(list.get(1).toString());
		pai.addMetodosInternos(e);
	}
}
