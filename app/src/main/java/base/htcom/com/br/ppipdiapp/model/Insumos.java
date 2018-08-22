package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class Insumos implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private String LINHA,	VALIDADE,	MANDATORIO,	CLIENTE,	CONTRATO,	CONTRATO_FASE,	REFERENCIA,	ORDEM,	FILTRO,	SIGLA,
	COD,	TIPO,	INFO_01,	INFO_02,	INFO_03,	INFO_04,	INFO_05,	INFO_06,	INFO_07,
	INFO_08,	INFO_09,	INFO_10,	INFO_11,	INFO_12,	INFO_13,	INFO_14,	INFO_15,	INFO_16,	INFO_17,	INFO_18,	INFO_19,
	INFO_20,	INFO_21,	INFO_22,	INFO_23,	INFO_24,
	OBSERVACAO, OBSERVACAO_MASTER, CONTROLE_ROTINA, CONTROLE_MSG,	ATUALIZACAO_NOME,	ATUALIZACAO_DATA,	ATUALIZACAO_DATA_INV,
	INSERCAO_DATA,	INSERCAO_NOME;
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
	public String getINFO_01() {
		return INFO_01;
	}
	public void setINFO_01(String iNFO_01) {
		INFO_01 = iNFO_01;
	}
	public String getINFO_02() {
		return INFO_02;
	}
	public void setINFO_02(String iNFO_02) {
		INFO_02 = iNFO_02;
	}
	public String getINFO_03() {
		return INFO_03;
	}
	public void setINFO_03(String iNFO_03) {
		INFO_03 = iNFO_03;
	}
	public String getINFO_04() {
		return INFO_04;
	}
	public void setINFO_04(String iNFO_04) {
		INFO_04 = iNFO_04;
	}
	public String getINFO_05() {
		return INFO_05;
	}
	public void setINFO_05(String iNFO_05) {
		INFO_05 = iNFO_05;
	}
	public String getINFO_06() {
		return INFO_06;
	}
	public void setINFO_06(String iNFO_06) {
		INFO_06 = iNFO_06;
	}
	public String getINFO_07() {
		return INFO_07;
	}
	public void setINFO_07(String iNFO_07) {
		INFO_07 = iNFO_07;
	}
	public String getINFO_08() {
		return INFO_08;
	}
	public void setINFO_08(String iNFO_08) {
		INFO_08 = iNFO_08;
	}
	public String getINFO_09() {
		return INFO_09;
	}
	public void setINFO_09(String iNFO_09) {
		INFO_09 = iNFO_09;
	}
	public String getINFO_10() {
		return INFO_10;
	}
	public void setINFO_10(String iNFO_10) {
		INFO_10 = iNFO_10;
	}
	public String getINFO_11() {
		return INFO_11;
	}
	public void setINFO_11(String iNFO_11) {
		INFO_11 = iNFO_11;
	}
	public String getINFO_12() {
		return INFO_12;
	}
	public void setINFO_12(String iNFO_12) {
		INFO_12 = iNFO_12;
	}
	public String getINFO_13() {
		return INFO_13;
	}
	public void setINFO_13(String iNFO_13) {
		INFO_13 = iNFO_13;
	}
	public String getINFO_14() {
		return INFO_14;
	}
	public void setINFO_14(String iNFO_14) {
		INFO_14 = iNFO_14;
	}
	public String getINFO_15() {
		return INFO_15;
	}
	public void setINFO_15(String iNFO_15) {
		INFO_15 = iNFO_15;
	}
	public String getINFO_16() {
		return INFO_16;
	}
	public void setINFO_16(String iNFO_16) {
		INFO_16 = iNFO_16;
	}
	public String getINFO_17() {
		return INFO_17;
	}
	public void setINFO_17(String iNFO_17) {
		INFO_17 = iNFO_17;
	}
	public String getINFO_18() {
		return INFO_18;
	}
	public void setINFO_18(String iNFO_18) {
		INFO_18 = iNFO_18;
	}
	public String getINFO_19() {
		return INFO_19;
	}
	public void setINFO_19(String iNFO_19) {
		INFO_19 = iNFO_19;
	}
	public String getINFO_20() {
		return INFO_20;
	}
	public void setINFO_20(String iNFO_20) {
		INFO_20 = iNFO_20;
	}
	public String getINFO_21() {
		return INFO_21;
	}
	public void setINFO_21(String iNFO_21) {
		INFO_21 = iNFO_21;
	}
	public String getINFO_22() {
		return INFO_22;
	}
	public void setINFO_22(String iNFO_22) {
		INFO_22 = iNFO_22;
	}
	public String getINFO_23() {
		return INFO_23;
	}
	public void setINFO_23(String iNFO_23) {
		INFO_23 = iNFO_23;
	}
	public String getINFO_24() {
		return INFO_24;
	}
	public void setINFO_24(String iNFO_24) {
		INFO_24 = iNFO_24;
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
