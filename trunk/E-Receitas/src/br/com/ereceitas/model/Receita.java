package br.com.ereceitas.model;

import java.io.Serializable;
import java.util.List;

public class Receita implements Serializable {

	private int id;
	private TipoReceita tipo;
	private String nome;
	private String dificuldade;
	private String tempoPreparo;
	private List<Ingrediente> ingredientes;
	private List<Preparo> preparos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoReceita getTipo() {
		return tipo;
	}

	public void setTipo(TipoReceita tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(String dificuldade) {
		this.dificuldade = dificuldade;
	}

	public String getTempoPreparo() {
		return tempoPreparo;
	}

	public void setTempoPreparo(String tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public List<Preparo> getPreparos() {
		return preparos;
	}

	public void setPreparos(List<Preparo> preparos) {
		this.preparos = preparos;
	}

}
