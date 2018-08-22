package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class StatusControleUpload implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private String _id,LINHA,DATA_ENVIO;
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
	public String getDATA_ENVIO() {
		return DATA_ENVIO;
	}
	public void setDATA_ENVIO(String dATA_ENVIO) {
		DATA_ENVIO = dATA_ENVIO;
	}

}
