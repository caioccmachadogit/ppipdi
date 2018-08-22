package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class StatusCarregamento implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private String _id,LINHA;
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

}
