package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class StatusOs implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private String _id,LINHA,OV_CHAMADO_NUM,COD_ENTIDADE,ENVIADO,ENVIADO_FOTOS,DATA_FINALIZACAO;
	public String getDATA_FINALIZACAO() {
		return DATA_FINALIZACAO;
	}
	public void setDATA_FINALIZACAO(String dATA_FINALIZACAO) {
		DATA_FINALIZACAO = dATA_FINALIZACAO;
	}
	public String getENVIADO_FOTOS() {
		return ENVIADO_FOTOS;
	}
	public void setENVIADO_FOTOS(String eNVIADO_FOTOS) {
		ENVIADO_FOTOS = eNVIADO_FOTOS;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getLINHA() {
		return LINHA;
	}
	public void setLINHA(String lINHA) {
		LINHA = lINHA;
	}
	public String getOV_CHAMADO_NUM() {
		return OV_CHAMADO_NUM;
	}
	public void setOV_CHAMADO_NUM(String oV_CHAMADO_NUM) {
		OV_CHAMADO_NUM = oV_CHAMADO_NUM;
	}
	public String getCOD_ENTIDADE() {
		return COD_ENTIDADE;
	}
	public void setCOD_ENTIDADE(String cOD_ENTIDADE) {
		COD_ENTIDADE = cOD_ENTIDADE;
	}
	public String getENVIADO() {
		return ENVIADO;
	}
	public void setENVIADO(String eNVIADO) {
		ENVIADO = eNVIADO;
	}
	

}
