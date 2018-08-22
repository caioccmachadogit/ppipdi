package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class CarregamentoPlanta implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private String LINHA,	CODIGO,	VALIDADE,	COD_ENTIDADE,	DOC_REF,	NIVEL_REV,	LOTE_CONTROLE,	UF,	CN,	OS_INSERCAO,	OS_RETIRADA,
	FLAG_CONDICAO,	ESTRUT_ORDEM,	CAMPO_TIPO,	ORDENADOR,	INFO_01,	INFO_02,	INFO_03,	INFO_04,	INFO_05,	INFO_06,	INFO_07,
	INFO_08,	INFO_09,	INFO_10,	INFO_11,	INFO_12,	INFO_13,	INFO_14,	INFO_15,	INFO_16,	INFO_17,	INFO_18,	INFO_19,
	INFO_20,	INFO_21,	INFO_22,	INFO_23,	INFO_24,	INFO_25,	INFO_26,	INFO_27,	INFO_28,	INFO_29,	INFO_30,	INFO_31,
	INFO_32,INFO_33,	INFO_34,	INFO_35,	INFO_36,	INFO_37,	INFO_38,	INFO_39,	INFO_40,	INFO_41,	INFO_42,	INFO_43,
	INFO_44,	INFO_45,	INFO_46,	INFO_47,	INFO_48,	INFO_49,	INFO_50,	INFO_51,	INFO_52,	INFO_53,	INFO_54,
	INFO_55,	INFO_56,	INFO_57,	INFO_58,	INFO_59,	INFO_60,	CONTROLE_MSG,	ATUALIZACAO_NOME,	ATUALIZACAO_DATA,	ATUALIZACAO_DATA_INV,
	INSERCAO_DATA,	INSERCAO_NOME;
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
	public String getCOD_ENTIDADE() {
		return COD_ENTIDADE;
	}
	public void setCOD_ENTIDADE(String cOD_ENTIDADE) {
		COD_ENTIDADE = cOD_ENTIDADE;
	}
	public String getDOC_REF() {
		return DOC_REF;
	}
	public void setDOC_REF(String dOC_REF) {
		DOC_REF = dOC_REF;
	}
	public String getNIVEL_REV() {
		return NIVEL_REV;
	}
	public void setNIVEL_REV(String nIVEL_REV) {
		NIVEL_REV = nIVEL_REV;
	}
	public String getLOTE_CONTROLE() {
		return LOTE_CONTROLE;
	}
	public void setLOTE_CONTROLE(String lOTE_CONTROLE) {
		LOTE_CONTROLE = lOTE_CONTROLE;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
	public String getCN() {
		return CN;
	}
	public void setCN(String cN) {
		CN = cN;
	}
	public String getOS_INSERCAO() {
		return OS_INSERCAO;
	}
	public void setOS_INSERCAO(String oS_INSERCAO) {
		OS_INSERCAO = oS_INSERCAO;
	}
	public String getOS_RETIRADA() {
		return OS_RETIRADA;
	}
	public void setOS_RETIRADA(String oS_RETIRADA) {
		OS_RETIRADA = oS_RETIRADA;
	}
	public String getFLAG_CONDICAO() {
		return FLAG_CONDICAO;
	}
	public void setFLAG_CONDICAO(String fLAG_CONDICAO) {
		FLAG_CONDICAO = fLAG_CONDICAO;
	}
	public String getESTRUT_ORDEM() {
		return ESTRUT_ORDEM;
	}
	public void setESTRUT_ORDEM(String eSTRUT_ORDEM) {
		ESTRUT_ORDEM = eSTRUT_ORDEM;
	}
	public String getCAMPO_TIPO() {
		return CAMPO_TIPO;
	}
	public void setCAMPO_TIPO(String cAMPO_TIPO) {
		CAMPO_TIPO = cAMPO_TIPO;
	}
	public String getORDENADOR() {
		return ORDENADOR;
	}
	public void setORDENADOR(String oRDENADOR) {
		ORDENADOR = oRDENADOR;
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
	public String getINFO_25() {
		return INFO_25;
	}
	public void setINFO_25(String iNFO_25) {
		INFO_25 = iNFO_25;
	}
	public String getINFO_26() {
		return INFO_26;
	}
	public void setINFO_26(String iNFO_26) {
		INFO_26 = iNFO_26;
	}
	public String getINFO_27() {
		return INFO_27;
	}
	public void setINFO_27(String iNFO_27) {
		INFO_27 = iNFO_27;
	}
	public String getINFO_28() {
		return INFO_28;
	}
	public void setINFO_28(String iNFO_28) {
		INFO_28 = iNFO_28;
	}
	public String getINFO_29() {
		return INFO_29;
	}
	public void setINFO_29(String iNFO_29) {
		INFO_29 = iNFO_29;
	}
	public String getINFO_30() {
		return INFO_30;
	}
	public void setINFO_30(String iNFO_30) {
		INFO_30 = iNFO_30;
	}
	public String getINFO_31() {
		return INFO_31;
	}
	public void setINFO_31(String iNFO_31) {
		INFO_31 = iNFO_31;
	}
	public String getINFO_32() {
		return INFO_32;
	}
	public void setINFO_32(String iNFO_32) {
		INFO_32 = iNFO_32;
	}
	public String getINFO_33() {
		return INFO_33;
	}
	public void setINFO_33(String iNFO_33) {
		INFO_33 = iNFO_33;
	}
	public String getINFO_34() {
		return INFO_34;
	}
	public void setINFO_34(String iNFO_34) {
		INFO_34 = iNFO_34;
	}
	public String getINFO_35() {
		return INFO_35;
	}
	public void setINFO_35(String iNFO_35) {
		INFO_35 = iNFO_35;
	}
	public String getINFO_36() {
		return INFO_36;
	}
	public void setINFO_36(String iNFO_36) {
		INFO_36 = iNFO_36;
	}
	public String getINFO_37() {
		return INFO_37;
	}
	public void setINFO_37(String iNFO_37) {
		INFO_37 = iNFO_37;
	}
	public String getINFO_38() {
		return INFO_38;
	}
	public void setINFO_38(String iNFO_38) {
		INFO_38 = iNFO_38;
	}
	public String getINFO_39() {
		return INFO_39;
	}
	public void setINFO_39(String iNFO_39) {
		INFO_39 = iNFO_39;
	}
	public String getINFO_40() {
		return INFO_40;
	}
	public void setINFO_40(String iNFO_40) {
		INFO_40 = iNFO_40;
	}
	public String getINFO_41() {
		return INFO_41;
	}
	public void setINFO_41(String iNFO_41) {
		INFO_41 = iNFO_41;
	}
	public String getINFO_42() {
		return INFO_42;
	}
	public void setINFO_42(String iNFO_42) {
		INFO_42 = iNFO_42;
	}
	public String getINFO_43() {
		return INFO_43;
	}
	public void setINFO_43(String iNFO_43) {
		INFO_43 = iNFO_43;
	}
	public String getINFO_44() {
		return INFO_44;
	}
	public void setINFO_44(String iNFO_44) {
		INFO_44 = iNFO_44;
	}
	public String getINFO_45() {
		return INFO_45;
	}
	public void setINFO_45(String iNFO_45) {
		INFO_45 = iNFO_45;
	}
	public String getINFO_46() {
		return INFO_46;
	}
	public void setINFO_46(String iNFO_46) {
		INFO_46 = iNFO_46;
	}
	public String getINFO_47() {
		return INFO_47;
	}
	public void setINFO_47(String iNFO_47) {
		INFO_47 = iNFO_47;
	}
	public String getINFO_48() {
		return INFO_48;
	}
	public void setINFO_48(String iNFO_48) {
		INFO_48 = iNFO_48;
	}
	public String getINFO_49() {
		return INFO_49;
	}
	public void setINFO_49(String iNFO_49) {
		INFO_49 = iNFO_49;
	}
	public String getINFO_50() {
		return INFO_50;
	}
	public void setINFO_50(String iNFO_50) {
		INFO_50 = iNFO_50;
	}
	public String getINFO_51() {
		return INFO_51;
	}
	public void setINFO_51(String iNFO_51) {
		INFO_51 = iNFO_51;
	}
	public String getINFO_52() {
		return INFO_52;
	}
	public void setINFO_52(String iNFO_52) {
		INFO_52 = iNFO_52;
	}
	public String getINFO_53() {
		return INFO_53;
	}
	public void setINFO_53(String iNFO_53) {
		INFO_53 = iNFO_53;
	}
	public String getINFO_54() {
		return INFO_54;
	}
	public void setINFO_54(String iNFO_54) {
		INFO_54 = iNFO_54;
	}
	public String getINFO_55() {
		return INFO_55;
	}
	public void setINFO_55(String iNFO_55) {
		INFO_55 = iNFO_55;
	}
	public String getINFO_56() {
		return INFO_56;
	}
	public void setINFO_56(String iNFO_56) {
		INFO_56 = iNFO_56;
	}
	public String getINFO_57() {
		return INFO_57;
	}
	public void setINFO_57(String iNFO_57) {
		INFO_57 = iNFO_57;
	}
	public String getINFO_58() {
		return INFO_58;
	}
	public void setINFO_58(String iNFO_58) {
		INFO_58 = iNFO_58;
	}
	public String getINFO_59() {
		return INFO_59;
	}
	public void setINFO_59(String iNFO_59) {
		INFO_59 = iNFO_59;
	}
	public String getINFO_60() {
		return INFO_60;
	}
	public void setINFO_60(String iNFO_60) {
		INFO_60 = iNFO_60;
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
