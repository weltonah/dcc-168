package br.com.grafo;

import java.util.ArrayList;
import java.util.HashMap;

public class Grafo {
	
	
	private HashMap<Integer, Folha> listaFolhas;
	private Folha raiz;
	private int cont;
	public Grafo(Folha e) {
		this.listaFolhas = new HashMap<Integer, Folha>();
		this.raiz = e;
		this.cont = 0;
		this.listaFolhas.put(cont, raiz);
		this.cont ++;
	}
	public void addMetodosInternos(Folha e) {
		this.raiz.addMetodosInternos(e);
	}
	public Folha getRaiz() {
		return raiz;
	}
	public void addlista(Folha e) {
		listaFolhas.put(cont, e);
		cont ++;
	}
	public Folha getFolha(int i) {
		return listaFolhas.get(i);
	}
	public int getCont() {
		return cont;
	}
}

