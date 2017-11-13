package br.com.grafo;

import java.util.ArrayList;

public class Folha {
	private String Texto;
	private ArrayList<Folha> filhos;
	private ArrayList<Folha> metodosInternos;
	
	public Folha() {
		filhos = new ArrayList<Folha>();
		metodosInternos = new ArrayList<Folha>();
		Texto = null;
	}
	
	public String getTexto() {
		return Texto;
	}
	public void setTexto(String texto) {
		Texto = texto;
	}
	public ArrayList<Folha> getFilhos() {
		return filhos;
	}
	public void setFilhos(ArrayList<Folha> filhos) {
		this.filhos = filhos;
	}
	
	public void addFolha(Folha folha) {
		filhos.add(folha);
	}
	public void addMetodosInternos(Folha folha) {
		metodosInternos.add(folha);
	}
	public ArrayList<Folha> getMetodosInternos() {
		return metodosInternos;
	}
	
}
