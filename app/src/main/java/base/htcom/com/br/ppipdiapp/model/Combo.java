package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class Combo implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private String LINHA,	VALIDADE,	MANDATORIO,	CLIENTE,	CONTRATO,	CONTRATO_FASE,	REFERENCIA,	ORDEM,	FILTRO,
	SIGLA,	COD,	TIPO,	COD_REL,	TIPO_REL,	TITULO,	VALOR,	CLASSIFICACAO,	AEV,	CA,	OBSERVACAO,	OBSERVACAO_MASTER,
	CONTROLE_ROTINA,	CONTROLE_MSG,	ATUALIZACAO_NOME,	ATUALIZACAO_DATA,	INSERCAO_DATA,	INSERCAO_NOME;
	public String getLINHA() {
		return LINHA;
	}
	public void setLINHA(String lINHA) {
		LINHA = lINHA;
	}
	public String getVALIDADE() {
		return VALIDADE;
	}
	public void setVALIDADE(String vALIDADE) {
		VALIDADE = vALIDADE;
	}
	public String getMANDATORIO() {
		return MANDATORIO;
	}
	public void setMANDATORIO(String mANDATORIO) {
		MANDATORIO = mANDATORIO;
	}
	public String getCLIENTE() {
		return CLIENTE;
	}
	public void setCLIENTE(String cLIENTE) {
		CLIENTE = cLIENTE;
	}
	public String getCONTRATO() {
		return CONTRATO;
	}
	public void setCONTRATO(String cONTRATO) {
		CONTRATO = cONTRATO;
	}
	public String getCONTRATO_FASE() {
		return CONTRATO_FASE;
	}
	public void setCONTRATO_FASE(String cONTRATO_FASE) {
		CONTRATO_FASE = cONTRATO_FASE;
	}
	public String getREFERENCIA() {
		return REFERENCIA;
	}
	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}
	public String getORDEM() {
		return ORDEM;
	}
	public void setORDEM(String oRDEM) {
		ORDEM = oRDEM;
	}
	public String getFILTRO() {
		return FILTRO;
	}
	public void setFILTRO(String fILTRO) {
		FILTRO = fILTRO;
	}
	public String getSIGLA() {
		return SIGLA;
	}
	public void setSIGLA(String sIGLA) {
		SIGLA = sIGLA;
	}
	public String getCOD() {
		return COD;
	}
	public void setCOD(String cOD) {
		COD = cOD;
	}
	public String getTIPO() {
		return TIPO;
	}
	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}
	public String getCOD_REL() {
		return COD_REL;
	}
	public void setCOD_REL(String cOD_REL) {
		COD_REL = cOD_REL;
	}
	public String getTIPO_REL() {
		return TIPO_REL;
	}
	public void setTIPO_REL(String tIPO_REL) {
		TIPO_REL = tIPO_REL;
	}
	public String getTITULO() {
		return TITULO;
	}
	public void setTITULO(String tITULO) {
		TITULO = tITULO;
	}
	public String getVALOR() {
		return VALOR;
	}
	public void setVALOR(String vALOR) {
		VALOR = vALOR;
	}
	public String getCLASSIFICACAO() {
		return CLASSIFICACAO;
	}
	public void setCLASSIFICACAO(String cLASSIFICACAO) {
		CLASSIFICACAO = cLASSIFICACAO;
	}
	public String getAEV() {
		return AEV;
	}
	public void setAEV(String aEV) {
		AEV = aEV;
	}
	public String getCA() {
		return CA;
	}
	public void setCA(String cA) {
		CA = cA;
	}
	public String getOBSERVACAO() {
		return OBSERVACAO;
	}
	public void setOBSERVACAO(String oBSERVACAO) {
		OBSERVACAO = oBSERVACAO;
	}
	public String getOBSERVACAO_MASTER() {
		return OBSERVACAO_MASTER;
	}
	public void setOBSERVACAO_MASTER(String oBSERVACAO_MASTER) {
		OBSERVACAO_MASTER = oBSERVACAO_MASTER;
	}
	public String getCONTROLE_ROTINA() {
		return CONTROLE_ROTINA;
	}
	public void setCONTROLE_ROTINA(String cONTROLE_ROTINA) {
		CONTROLE_ROTINA = cONTROLE_ROTINA;
	}
	public String getCONTROLE_MSG() {
		return CONTROLE_MSG;
	}
	public void setCONTROLE_MSG(String cONTROLE_MSG) {
		CONTROLE_MSG = cONTROLE_MSG;
	}
	public String getATUALIZACAO_NOME() {
		return ATUALIZACAO_NOME;
	}
	public void setATUALIZACAO_NOME(String aTUALIZACAO_NOME) {
		ATUALIZACAO_NOME = aTUALIZACAO_NOME;
	}
	public String getATUALIZACAO_DATA() {
		return ATUALIZACAO_DATA;
	}
	public void setATUALIZACAO_DATA(String aTUALIZACAO_DATA) {
		ATUALIZACAO_DATA = aTUALIZACAO_DATA;
	}
	public String getINSERCAO_DATA() {
		return INSERCAO_DATA;
	}
	public void setINSERCAO_DATA(String iNSERCAO_DATA) {
		INSERCAO_DATA = iNSERCAO_DATA;
	}
	public String getINSERCAO_NOME() {
		return INSERCAO_NOME;
	}
	public void setINSERCAO_NOME(String iNSERCAO_NOME) {
		INSERCAO_NOME = iNSERCAO_NOME;
	}


}
