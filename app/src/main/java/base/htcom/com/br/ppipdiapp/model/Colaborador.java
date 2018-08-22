package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

public class Colaborador implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email_1,senha,empresa,usuario;
	public String getEmail() {
		return email_1;
	}
	public void setEmail(String email_1) {
		this.email_1 = email_1;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
