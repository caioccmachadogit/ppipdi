package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class ArqPref implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private String LINHA,	CODIGO,	VALIDADE,	CLIENTE,	CONTRATO,	CONTRATO_FASE,	COD_ENTIDADE,	OV_CHAMADO_NUM,	ARQ_STATUS,
	Arquivo_Carregado,	COMPARTILHAMENTO,	ARQ_NOME_REF,	OS_VERIF_20XXX,	OBSERVACAO_MASTER,	CONTROLE_ROTINA,	CONTROLE_MSG,	ATUALIZACAO_NOME,
	ATUALIZACAO_DATA,	ATUALIZACAO_DATA_INV,	INSERCAO_DATA,	INSERCAO_NOME, CAMPO_REVISAO;
	public String getCAMPO_REVISAO() {
		return CAMPO_REVISAO;
	}
	public void setCAMPO_REVISAO(String cAMPO_REVISAO) {
		CAMPO_REVISAO = cAMPO_REVISAO;
	}
	public String getLINHA() {
		return LINHA;
	}
	public void setLINHA(String lINHA) {
		LINHA = lINHA;
	}
	public String getCODIGO() {
		return CODIGO;
	}
	public void setCODIGO(String cODIGO) {
		CODIGO = cODIGO;
	}
	public String getVALIDADE() {
		return VALIDADE;
	}
	public void setVALIDADE(String vALIDADE) {
		VALIDADE = vALIDADE;
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
	public String getCOD_ENTIDADE() {
		return COD_ENTIDADE;
	}
	public void setCOD_ENTIDADE(String cOD_ENTIDADE) {
		COD_ENTIDADE = cOD_ENTIDADE;
	}
	public String getOV_CHAMADO_NUM() {
		return OV_CHAMADO_NUM;
	}
	public void setOV_CHAMADO_NUM(String oV_CHAMADO_NUM) {
		OV_CHAMADO_NUM = oV_CHAMADO_NUM;
	}
	public String getARQ_STATUS() {
		return ARQ_STATUS;
	}
	public void setARQ_STATUS(String aRQ_STATUS) {
		ARQ_STATUS = aRQ_STATUS;
	}
	public String getArquivo_Carregado() {
		return Arquivo_Carregado;
	}
	public void setArquivo_Carregado(String arquivo_Carregado) {
		Arquivo_Carregado = arquivo_Carregado;
	}
	public String getCOMPARTILHAMENTO() {
		return COMPARTILHAMENTO;
	}
	public void setCOMPARTILHAMENTO(String cOMPARTILHAMENTO) {
		COMPARTILHAMENTO = cOMPARTILHAMENTO;
	}
	public String getARQ_NOME_REF() {
		return ARQ_NOME_REF;
	}
	public void setARQ_NOME_REF(String aRQ_NOME_REF) {
		ARQ_NOME_REF = aRQ_NOME_REF;
	}
	public String getOS_VERIF_20XXX() {
		return OS_VERIF_20XXX;
	}
	public void setOS_VERIF_20XXX(String oS_VERIF_20XXX) {
		OS_VERIF_20XXX = oS_VERIF_20XXX;
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
	public String getATUALIZACAO_DATA_INV() {
		return ATUALIZACAO_DATA_INV;
	}
	public void setATUALIZACAO_DATA_INV(String aTUALIZACAO_DATA_INV) {
		ATUALIZACAO_DATA_INV = aTUALIZACAO_DATA_INV;
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
