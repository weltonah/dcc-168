package br.com.grafo;

import java.util.ArrayList;
import java.util.HashMap;

public class Grafo {
	
	
	//private HashMap<Integer, Folha> listaFolhas;
	private ArrayList<Folha> listaFolhas;
	private Folha fim;
	private Folha raiz;
	private String nomeMetodo;
	public Grafo(Folha e) {
		//this.listaFolhas = new HashMap<Integer, Folha>();
		this.listaFolhas = new ArrayList<Folha>();
		this.raiz = e;
		this.fim = new Folha();
		this.raiz.setCobertura(true);
		this.fim.setCobertura(true);
		this.fim.setTexto("FIM");
		//this.listaFolhas.put(cont, raiz);
		this.listaFolhas.add(raiz);
	}
	public void addMetodosInternos(Folha e) {
		this.raiz.addMetodosInternos(e);
	}
	public Folha getRaiz() {
		return raiz;
	}
	public Folha getFim() {
		return fim;
	}
//	public void addlista(Folha e) {
//		listaFolhas.put(cont, e);
//		cont ++;
//	}
	public void addlista(Folha e) {
		listaFolhas.add(e);
	}
	public Folha getFolha(int i) {
		return listaFolhas.get(i);
	}
	public void deletar(Folha e) {
		listaFolhas.remove(e);
	}
//	public int getCont() {
//		return cont;
//	}
	public int getCont() {
		return listaFolhas.size();
	}
	public ArrayList<Folha> GetHashMap(){
		return listaFolhas;
	}
	public int buscaFolha(Folha e) {
		return listaFolhas.indexOf(e);
	}
	public String getNomeMetodo() {
		return nomeMetodo;
	}
	public void setNomeMetodo(String nomeMetodo) {
		this.nomeMetodo = nomeMetodo;
	}
}

