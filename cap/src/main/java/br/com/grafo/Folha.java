package br.com.grafo;

import java.util.ArrayList;

public class Folha {
	private String Texto;
	private ArrayList<Folha> filhos;
	private ArrayList<Folha> metodosInternos;
	private boolean cobertura;
	private int linha;
	
	public Folha() {
		filhos = new ArrayList<Folha>();
		metodosInternos = new ArrayList<Folha>();
		Texto = null;
		cobertura = false;
		linha = -1;
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
	public void deletar(Folha e) {
		filhos.remove(e);
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

	public boolean isCobertura() {
		return cobertura;
	}

	public void setCobertura(boolean cobertura) {
		this.cobertura = cobertura;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}
	
}
