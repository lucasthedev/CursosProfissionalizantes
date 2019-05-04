package com.cursos.profi.ws;

import java.sql.Date;

public class UsuarioCurso {
	
	private int id;
	private int idCurso;
	private String nome;
	private String email;
	private String dataNascimento;
	private String telefone;
	private String descricaoCurso;
	
	public String getDescricaoCurso() {
		return descricaoCurso;
	}
	public void setDescricaoCurso(String descricaoCurso) {
		this.descricaoCurso = descricaoCurso;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	

}
