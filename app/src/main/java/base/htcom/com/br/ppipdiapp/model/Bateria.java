package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class Bateria implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private String Id,	OV_CHAMADO_NUM,	LOCAL,	MARCA,	MODELO,	CAPACIDADE,	ORDEM;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getOV_CHAMADO_NUM() {
		return OV_CHAMADO_NUM;
	}
	public void setOV_CHAMADO_NUM(String oV_CHAMADO_NUM) {
		OV_CHAMADO_NUM = oV_CHAMADO_NUM;
	}
	public String getLOCAL() {
		return LOCAL;
	}
	public void setLOCAL(String lOCAL) {
		LOCAL = lOCAL;
	}
	public String getMARCA() {
		return MARCA;
	}
	public void setMARCA(String mARCA) {
		MARCA = mARCA;
	}
	public String getMODELO() {
		return MODELO;
	}
	public void setMODELO(String mODELO) {
		MODELO = mODELO;
	}
	public String getCAPACIDADE() {
		return CAPACIDADE;
	}
	public void setCAPACIDADE(String cAPACIDADE) {
		CAPACIDADE = cAPACIDADE;
	}
	public String getORDEM() {
		return ORDEM;
	}
	public void setORDEM(String oRDEM) {
		ORDEM = oRDEM;
	}
	
}
