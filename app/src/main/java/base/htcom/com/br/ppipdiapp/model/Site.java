package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class Site implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private String LINHA,CODIGO,VALIDADE,SITUACAO,CLIENTE,CONTRATO,CONTRATO_FASE,LOTE_CONTROLE,ID_NATIVO_GTS,OPERADORA_CEDENTE,
	COD_PATRIM_CEDENTE,TORRE_FABRICANTE,DATA_ATIVACAO,	DATA_INSTALACAO,	DATA_RECEBIMENTO,	OS_RECEBIMENTO,	DATA_PREVENTIVA,
	OS_PREVENTIVA,	DATA_MANUTENCAO,	OS_MANUTENCAO,	IMOVEL_TIPO,	IMOVEL_LICENCIAVEL,	IMOVEL_LICENCIADO,	TIPO,	NOME,
	SIGLA,	TIPO_CLI,	COD_CLI,	ID_PRIMARIO,	ID_DETENTOR_01,	ID_DETENTOR_02,	ID_DETENTOR_03,	ID_DETENTOR_04,	ID_DETENTOR_05,
	ID_DETENTOR_06,	ID_DETENTOR_07,	ID_DETENTOR_08,	DETENTOR,	DETENTOR_OBSERVACAO,	REGIAO,	LOGRADOURO_NOME,	LOGRADOURO_NUMERO,
	LOGRADOURO_COMPLEM,	LOGRADOURO_BAIRRO,	LOGRADOURO_CIDADE,	LOGRADOURO_UF,	LOGRADOURO_PAIS,	LOGRADOURO_REFERENCIA,	LOGRADOURO_CEP_PRI,
	LOGRADOURO_CEP_COMP,	LOGRADOURO_NOME_2,	LOGRADOURO_NUMERO_2,	LOGRADOURO_COMPLEM_2,	LOGRADOURO_BAIRRO_2,	LOGRADOURO_CIDADE_2,
	LOGRADOURO_UF_2,	LOGRADOURO_PAIS_2,	LOGRADOURO_REFERENCIA_2,	LOGRADOURO_CEP_PRI_2,	LOGRADOURO_CEP_COMP_2,	TIPO_IMOVEL,	ZONA,
	CLASSIFICACAO,	CN,	TEL_1,	TEL_2,	FAX,	LATITUDE,	LONGITUDE,	ALTITUDE,	EDIFICACAO_ALTURA,	ESTRUTURA_1_TIPO,	ESTRUTURA_1_ALTURA,
	ESTRUTURA_2_TIPO,	ESTRUTURA_2_ALTURA,	PREF_ESTRUTURA,	ESTRUTURA_DATA,	ESTRUTURA_FABR,	SITE_TIPO,	SITE_ESTRUT,	SITE_CLASSIF,	EV_AREA_AMPLIACAO,
	AREA_CHAO,	ABRIGO_TIPO,	ABRIGO_TIPO_1,	ABRIGO_TIPO_2,	ABRIGO_TIPO_3,	ABRIGO_TIPO_4,	ABRIGO_TIPO_5,	ABRIGO_TIPO_6,	TORRE_OBST,
	RESPONSAVEL_1_NOME,	RESPONSAVEL_1_CARGO,	RESPONSAVEL_1_EMAIL,	RESPONSAVEL_1_CN_TEL,	RESPONSAVEL_1_TELEFONE,	RESPONSAVEL_1_RAMAL,
	RESPONSAVEL_1_CN_CEL,	RESPONSAVEL_1_CELULAR,	RESPONSAVEL_2_NOME,	RESPONSAVEL_2_CARGO,	RESPONSAVEL_2_EMAIL,	RESPONSAVEL_2_CN_TEL,
	RESPONSAVEL_2_TELEFONE,	RESPONSAVEL_2_RAMAL,	RESPONSAVEL_2_CN_CEL,	RESPONSAVEL_2_CELULAR,	KM_SEDE,	OBSERVACAO,	DIST_BUSCA,	OBSERVACAO_MASTER,
	CONTROLE_ROTINA,	CONTROLE_MSG,	ATUALIZACAO_NOME,	ATUALIZACAO_DATA,	ATUALIZACAO_DATA_INV,	INSERCAO_DATA,	INSERCAO_NOME;
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
	public String getSITUACAO() {
		return SITUACAO;
	}
	public void setSITUACAO(String sITUACAO) {
		SITUACAO = sITUACAO;
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
	public String getLOTE_CONTROLE() {
		return LOTE_CONTROLE;
	}
	public void setLOTE_CONTROLE(String lOTE_CONTROLE) {
		LOTE_CONTROLE = lOTE_CONTROLE;
	}
	public String getID_NATIVO_GTS() {
		return ID_NATIVO_GTS;
	}
	public void setID_NATIVO_GTS(String iD_NATIVO_GTS) {
		ID_NATIVO_GTS = iD_NATIVO_GTS;
	}
	public String getOPERADORA_CEDENTE() {
		return OPERADORA_CEDENTE;
	}
	public void setOPERADORA_CEDENTE(String oPERADORA_CEDENTE) {
		OPERADORA_CEDENTE = oPERADORA_CEDENTE;
	}
	public String getCOD_PATRIM_CEDENTE() {
		return COD_PATRIM_CEDENTE;
	}
	public void setCOD_PATRIM_CEDENTE(String cOD_PATRIM_CEDENTE) {
		COD_PATRIM_CEDENTE = cOD_PATRIM_CEDENTE;
	}
	public String getTORRE_FABRICANTE() {
		return TORRE_FABRICANTE;
	}
	public void setTORRE_FABRICANTE(String tORRE_FABRICANTE) {
		TORRE_FABRICANTE = tORRE_FABRICANTE;
	}
	public String getDATA_ATIVACAO() {
		return DATA_ATIVACAO;
	}
	public void setDATA_ATIVACAO(String dATA_ATIVACAO) {
		DATA_ATIVACAO = dATA_ATIVACAO;
	}
	public String getDATA_INSTALACAO() {
		return DATA_INSTALACAO;
	}
	public void setDATA_INSTALACAO(String dATA_INSTALACAO) {
		DATA_INSTALACAO = dATA_INSTALACAO;
	}
	public String getDATA_RECEBIMENTO() {
		return DATA_RECEBIMENTO;
	}
	public void setDATA_RECEBIMENTO(String dATA_RECEBIMENTO) {
		DATA_RECEBIMENTO = dATA_RECEBIMENTO;
	}
	public String getOS_RECEBIMENTO() {
		return OS_RECEBIMENTO;
	}
	public void setOS_RECEBIMENTO(String oS_RECEBIMENTO) {
		OS_RECEBIMENTO = oS_RECEBIMENTO;
	}
	public String getDATA_PREVENTIVA() {
		return DATA_PREVENTIVA;
	}
	public void setDATA_PREVENTIVA(String dATA_PREVENTIVA) {
		DATA_PREVENTIVA = dATA_PREVENTIVA;
	}
	public String getOS_PREVENTIVA() {
		return OS_PREVENTIVA;
	}
	public void setOS_PREVENTIVA(String oS_PREVENTIVA) {
		OS_PREVENTIVA = oS_PREVENTIVA;
	}
	public String getDATA_MANUTENCAO() {
		return DATA_MANUTENCAO;
	}
	public void setDATA_MANUTENCAO(String dATA_MANUTENCAO) {
		DATA_MANUTENCAO = dATA_MANUTENCAO;
	}
	public String getOS_MANUTENCAO() {
		return OS_MANUTENCAO;
	}
	public void setOS_MANUTENCAO(String oS_MANUTENCAO) {
		OS_MANUTENCAO = oS_MANUTENCAO;
	}
	public String getIMOVEL_TIPO() {
		return IMOVEL_TIPO;
	}
	public void setIMOVEL_TIPO(String iMOVEL_TIPO) {
		IMOVEL_TIPO = iMOVEL_TIPO;
	}
	public String getIMOVEL_LICENCIAVEL() {
		return IMOVEL_LICENCIAVEL;
	}
	public void setIMOVEL_LICENCIAVEL(String iMOVEL_LICENCIAVEL) {
		IMOVEL_LICENCIAVEL = iMOVEL_LICENCIAVEL;
	}
	public String getIMOVEL_LICENCIADO() {
		return IMOVEL_LICENCIADO;
	}
	public void setIMOVEL_LICENCIADO(String iMOVEL_LICENCIADO) {
		IMOVEL_LICENCIADO = iMOVEL_LICENCIADO;
	}
	public String getTIPO() {
		return TIPO;
	}
	public void setTIPO(String tIPO) {
		TIPO = tIPO;
	}
	public String getNOME() {
		return NOME;
	}
	public void setNOME(String nOME) {
		NOME = nOME;
	}
	public String getSIGLA() {
		return SIGLA;
	}
	public void setSIGLA(String sIGLA) {
		SIGLA = sIGLA;
	}
	public String getTIPO_CLI() {
		return TIPO_CLI;
	}
	public void setTIPO_CLI(String tIPO_CLI) {
		TIPO_CLI = tIPO_CLI;
	}
	public String getCOD_CLI() {
		return COD_CLI;
	}
	public void setCOD_CLI(String cOD_CLI) {
		COD_CLI = cOD_CLI;
	}
	public String getID_PRIMARIO() {
		return ID_PRIMARIO;
	}
	public void setID_PRIMARIO(String iD_PRIMARIO) {
		ID_PRIMARIO = iD_PRIMARIO;
	}
	public String getID_DETENTOR_01() {
		return ID_DETENTOR_01;
	}
	public void setID_DETENTOR_01(String iD_DETENTOR_01) {
		ID_DETENTOR_01 = iD_DETENTOR_01;
	}
	public String getID_DETENTOR_02() {
		return ID_DETENTOR_02;
	}
	public void setID_DETENTOR_02(String iD_DETENTOR_02) {
		ID_DETENTOR_02 = iD_DETENTOR_02;
	}
	public String getID_DETENTOR_03() {
		return ID_DETENTOR_03;
	}
	public void setID_DETENTOR_03(String iD_DETENTOR_03) {
		ID_DETENTOR_03 = iD_DETENTOR_03;
	}
	public String getID_DETENTOR_04() {
		return ID_DETENTOR_04;
	}
	public void setID_DETENTOR_04(String iD_DETENTOR_04) {
		ID_DETENTOR_04 = iD_DETENTOR_04;
	}
	public String getID_DETENTOR_05() {
		return ID_DETENTOR_05;
	}
	public void setID_DETENTOR_05(String iD_DETENTOR_05) {
		ID_DETENTOR_05 = iD_DETENTOR_05;
	}
	public String getID_DETENTOR_06() {
		return ID_DETENTOR_06;
	}
	public void setID_DETENTOR_06(String iD_DETENTOR_06) {
		ID_DETENTOR_06 = iD_DETENTOR_06;
	}
	public String getID_DETENTOR_07() {
		return ID_DETENTOR_07;
	}
	public void setID_DETENTOR_07(String iD_DETENTOR_07) {
		ID_DETENTOR_07 = iD_DETENTOR_07;
	}
	public String getID_DETENTOR_08() {
		return ID_DETENTOR_08;
	}
	public void setID_DETENTOR_08(String iD_DETENTOR_08) {
		ID_DETENTOR_08 = iD_DETENTOR_08;
	}
	public String getDETENTOR() {
		return DETENTOR;
	}
	public void setDETENTOR(String dETENTOR) {
		DETENTOR = dETENTOR;
	}
	public String getDETENTOR_OBSERVACAO() {
		return DETENTOR_OBSERVACAO;
	}
	public void setDETENTOR_OBSERVACAO(String dETENTOR_OBSERVACAO) {
		DETENTOR_OBSERVACAO = dETENTOR_OBSERVACAO;
	}
	public String getREGIAO() {
		return REGIAO;
	}
	public void setREGIAO(String rEGIAO) {
		REGIAO = rEGIAO;
	}
	public String getLOGRADOURO_NOME() {
		return LOGRADOURO_NOME;
	}
	public void setLOGRADOURO_NOME(String lOGRADOURO_NOME) {
		LOGRADOURO_NOME = lOGRADOURO_NOME;
	}
	public String getLOGRADOURO_NUMERO() {
		return LOGRADOURO_NUMERO;
	}
	public void setLOGRADOURO_NUMERO(String lOGRADOURO_NUMERO) {
		LOGRADOURO_NUMERO = lOGRADOURO_NUMERO;
	}
	public String getLOGRADOURO_COMPLEM() {
		return LOGRADOURO_COMPLEM;
	}
	public void setLOGRADOURO_COMPLEM(String lOGRADOURO_COMPLEM) {
		LOGRADOURO_COMPLEM = lOGRADOURO_COMPLEM;
	}
	public String getLOGRADOURO_BAIRRO() {
		return LOGRADOURO_BAIRRO;
	}
	public void setLOGRADOURO_BAIRRO(String lOGRADOURO_BAIRRO) {
		LOGRADOURO_BAIRRO = lOGRADOURO_BAIRRO;
	}
	public String getLOGRADOURO_CIDADE() {
		return LOGRADOURO_CIDADE;
	}
	public void setLOGRADOURO_CIDADE(String lOGRADOURO_CIDADE) {
		LOGRADOURO_CIDADE = lOGRADOURO_CIDADE;
	}
	public String getLOGRADOURO_UF() {
		return LOGRADOURO_UF;
	}
	public void setLOGRADOURO_UF(String lOGRADOURO_UF) {
		LOGRADOURO_UF = lOGRADOURO_UF;
	}
	public String getLOGRADOURO_PAIS() {
		return LOGRADOURO_PAIS;
	}
	public void setLOGRADOURO_PAIS(String lOGRADOURO_PAIS) {
		LOGRADOURO_PAIS = lOGRADOURO_PAIS;
	}
	public String getLOGRADOURO_REFERENCIA() {
		return LOGRADOURO_REFERENCIA;
	}
	public void setLOGRADOURO_REFERENCIA(String lOGRADOURO_REFERENCIA) {
		LOGRADOURO_REFERENCIA = lOGRADOURO_REFERENCIA;
	}
	public String getLOGRADOURO_CEP_PRI() {
		return LOGRADOURO_CEP_PRI;
	}
	public void setLOGRADOURO_CEP_PRI(String lOGRADOURO_CEP_PRI) {
		LOGRADOURO_CEP_PRI = lOGRADOURO_CEP_PRI;
	}
	public String getLOGRADOURO_CEP_COMP() {
		return LOGRADOURO_CEP_COMP;
	}
	public void setLOGRADOURO_CEP_COMP(String lOGRADOURO_CEP_COMP) {
		LOGRADOURO_CEP_COMP = lOGRADOURO_CEP_COMP;
	}
	public String getLOGRADOURO_NOME_2() {
		return LOGRADOURO_NOME_2;
	}
	public void setLOGRADOURO_NOME_2(String lOGRADOURO_NOME_2) {
		LOGRADOURO_NOME_2 = lOGRADOURO_NOME_2;
	}
	public String getLOGRADOURO_NUMERO_2() {
		return LOGRADOURO_NUMERO_2;
	}
	public void setLOGRADOURO_NUMERO_2(String lOGRADOURO_NUMERO_2) {
		LOGRADOURO_NUMERO_2 = lOGRADOURO_NUMERO_2;
	}
	public String getLOGRADOURO_COMPLEM_2() {
		return LOGRADOURO_COMPLEM_2;
	}
	public void setLOGRADOURO_COMPLEM_2(String lOGRADOURO_COMPLEM_2) {
		LOGRADOURO_COMPLEM_2 = lOGRADOURO_COMPLEM_2;
	}
	public String getLOGRADOURO_BAIRRO_2() {
		return LOGRADOURO_BAIRRO_2;
	}
	public void setLOGRADOURO_BAIRRO_2(String lOGRADOURO_BAIRRO_2) {
		LOGRADOURO_BAIRRO_2 = lOGRADOURO_BAIRRO_2;
	}
	public String getLOGRADOURO_CIDADE_2() {
		return LOGRADOURO_CIDADE_2;
	}
	public void setLOGRADOURO_CIDADE_2(String lOGRADOURO_CIDADE_2) {
		LOGRADOURO_CIDADE_2 = lOGRADOURO_CIDADE_2;
	}
	public String getLOGRADOURO_UF_2() {
		return LOGRADOURO_UF_2;
	}
	public void setLOGRADOURO_UF_2(String lOGRADOURO_UF_2) {
		LOGRADOURO_UF_2 = lOGRADOURO_UF_2;
	}
	public String getLOGRADOURO_PAIS_2() {
		return LOGRADOURO_PAIS_2;
	}
	public void setLOGRADOURO_PAIS_2(String lOGRADOURO_PAIS_2) {
		LOGRADOURO_PAIS_2 = lOGRADOURO_PAIS_2;
	}
	public String getLOGRADOURO_REFERENCIA_2() {
		return LOGRADOURO_REFERENCIA_2;
	}
	public void setLOGRADOURO_REFERENCIA_2(String lOGRADOURO_REFERENCIA_2) {
		LOGRADOURO_REFERENCIA_2 = lOGRADOURO_REFERENCIA_2;
	}
	public String getLOGRADOURO_CEP_PRI_2() {
		return LOGRADOURO_CEP_PRI_2;
	}
	public void setLOGRADOURO_CEP_PRI_2(String lOGRADOURO_CEP_PRI_2) {
		LOGRADOURO_CEP_PRI_2 = lOGRADOURO_CEP_PRI_2;
	}
	public String getLOGRADOURO_CEP_COMP_2() {
		return LOGRADOURO_CEP_COMP_2;
	}
	public void setLOGRADOURO_CEP_COMP_2(String lOGRADOURO_CEP_COMP_2) {
		LOGRADOURO_CEP_COMP_2 = lOGRADOURO_CEP_COMP_2;
	}
	public String getTIPO_IMOVEL() {
		return TIPO_IMOVEL;
	}
	public void setTIPO_IMOVEL(String tIPO_IMOVEL) {
		TIPO_IMOVEL = tIPO_IMOVEL;
	}
	public String getZONA() {
		return ZONA;
	}
	public void setZONA(String zONA) {
		ZONA = zONA;
	}
	public String getCLASSIFICACAO() {
		return CLASSIFICACAO;
	}
	public void setCLASSIFICACAO(String cLASSIFICACAO) {
		CLASSIFICACAO = cLASSIFICACAO;
	}
	public String getCN() {
		return CN;
	}
	public void setCN(String cN) {
		CN = cN;
	}
	public String getTEL_1() {
		return TEL_1;
	}
	public void setTEL_1(String tEL_1) {
		TEL_1 = tEL_1;
	}
	public String getTEL_2() {
		return TEL_2;
	}
	public void setTEL_2(String tEL_2) {
		TEL_2 = tEL_2;
	}
	public String getFAX() {
		return FAX;
	}
	public void setFAX(String fAX) {
		FAX = fAX;
	}
	public String getLATITUDE() {
		return LATITUDE;
	}
	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}
	public String getLONGITUDE() {
		return LONGITUDE;
	}
	public void setLONGITUDE(String lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}
	public String getALTITUDE() {
		return ALTITUDE;
	}
	public void setALTITUDE(String aLTITUDE) {
		ALTITUDE = aLTITUDE;
	}
	public String getEDIFICACAO_ALTURA() {
		return EDIFICACAO_ALTURA;
	}
	public void setEDIFICACAO_ALTURA(String eDIFICACAO_ALTURA) {
		EDIFICACAO_ALTURA = eDIFICACAO_ALTURA;
	}
	public String getESTRUTURA_1_TIPO() {
		return ESTRUTURA_1_TIPO;
	}
	public void setESTRUTURA_1_TIPO(String eSTRUTURA_1_TIPO) {
		ESTRUTURA_1_TIPO = eSTRUTURA_1_TIPO;
	}
	public String getESTRUTURA_1_ALTURA() {
		return ESTRUTURA_1_ALTURA;
	}
	public void setESTRUTURA_1_ALTURA(String eSTRUTURA_1_ALTURA) {
		ESTRUTURA_1_ALTURA = eSTRUTURA_1_ALTURA;
	}
	public String getESTRUTURA_2_TIPO() {
		return ESTRUTURA_2_TIPO;
	}
	public void setESTRUTURA_2_TIPO(String eSTRUTURA_2_TIPO) {
		ESTRUTURA_2_TIPO = eSTRUTURA_2_TIPO;
	}
	public String getESTRUTURA_2_ALTURA() {
		return ESTRUTURA_2_ALTURA;
	}
	public void setESTRUTURA_2_ALTURA(String eSTRUTURA_2_ALTURA) {
		ESTRUTURA_2_ALTURA = eSTRUTURA_2_ALTURA;
	}
	public String getPREF_ESTRUTURA() {
		return PREF_ESTRUTURA;
	}
	public void setPREF_ESTRUTURA(String pREF_ESTRUTURA) {
		PREF_ESTRUTURA = pREF_ESTRUTURA;
	}
	public String getESTRUTURA_DATA() {
		return ESTRUTURA_DATA;
	}
	public void setESTRUTURA_DATA(String eSTRUTURA_DATA) {
		ESTRUTURA_DATA = eSTRUTURA_DATA;
	}
	public String getESTRUTURA_FABR() {
		return ESTRUTURA_FABR;
	}
	public void setESTRUTURA_FABR(String eSTRUTURA_FABR) {
		ESTRUTURA_FABR = eSTRUTURA_FABR;
	}
	public String getSITE_TIPO() {
		return SITE_TIPO;
	}
	public void setSITE_TIPO(String sITE_TIPO) {
		SITE_TIPO = sITE_TIPO;
	}
	public String getSITE_ESTRUT() {
		return SITE_ESTRUT;
	}
	public void setSITE_ESTRUT(String sITE_ESTRUT) {
		SITE_ESTRUT = sITE_ESTRUT;
	}
	public String getSITE_CLASSIF() {
		return SITE_CLASSIF;
	}
	public void setSITE_CLASSIF(String sITE_CLASSIF) {
		SITE_CLASSIF = sITE_CLASSIF;
	}
	public String getEV_AREA_AMPLIACAO() {
		return EV_AREA_AMPLIACAO;
	}
	public void setEV_AREA_AMPLIACAO(String eV_AREA_AMPLIACAO) {
		EV_AREA_AMPLIACAO = eV_AREA_AMPLIACAO;
	}
	public String getAREA_CHAO() {
		return AREA_CHAO;
	}
	public void setAREA_CHAO(String aREA_CHAO) {
		AREA_CHAO = aREA_CHAO;
	}
	public String getABRIGO_TIPO() {
		return ABRIGO_TIPO;
	}
	public void setABRIGO_TIPO(String aBRIGO_TIPO) {
		ABRIGO_TIPO = aBRIGO_TIPO;
	}
	public String getABRIGO_TIPO_1() {
		return ABRIGO_TIPO_1;
	}
	public void setABRIGO_TIPO_1(String aBRIGO_TIPO_1) {
		ABRIGO_TIPO_1 = aBRIGO_TIPO_1;
	}
	public String getABRIGO_TIPO_2() {
		return ABRIGO_TIPO_2;
	}
	public void setABRIGO_TIPO_2(String aBRIGO_TIPO_2) {
		ABRIGO_TIPO_2 = aBRIGO_TIPO_2;
	}
	public String getABRIGO_TIPO_3() {
		return ABRIGO_TIPO_3;
	}
	public void setABRIGO_TIPO_3(String aBRIGO_TIPO_3) {
		ABRIGO_TIPO_3 = aBRIGO_TIPO_3;
	}
	public String getABRIGO_TIPO_4() {
		return ABRIGO_TIPO_4;
	}
	public void setABRIGO_TIPO_4(String aBRIGO_TIPO_4) {
		ABRIGO_TIPO_4 = aBRIGO_TIPO_4;
	}
	public String getABRIGO_TIPO_5() {
		return ABRIGO_TIPO_5;
	}
	public void setABRIGO_TIPO_5(String aBRIGO_TIPO_5) {
		ABRIGO_TIPO_5 = aBRIGO_TIPO_5;
	}
	public String getABRIGO_TIPO_6() {
		return ABRIGO_TIPO_6;
	}
	public void setABRIGO_TIPO_6(String aBRIGO_TIPO_6) {
		ABRIGO_TIPO_6 = aBRIGO_TIPO_6;
	}
	public String getTORRE_OBST() {
		return TORRE_OBST;
	}
	public void setTORRE_OBST(String tORRE_OBST) {
		TORRE_OBST = tORRE_OBST;
	}
	public String getRESPONSAVEL_1_NOME() {
		return RESPONSAVEL_1_NOME;
	}
	public void setRESPONSAVEL_1_NOME(String rESPONSAVEL_1_NOME) {
		RESPONSAVEL_1_NOME = rESPONSAVEL_1_NOME;
	}
	public String getRESPONSAVEL_1_CARGO() {
		return RESPONSAVEL_1_CARGO;
	}
	public void setRESPONSAVEL_1_CARGO(String rESPONSAVEL_1_CARGO) {
		RESPONSAVEL_1_CARGO = rESPONSAVEL_1_CARGO;
	}
	public String getRESPONSAVEL_1_EMAIL() {
		return RESPONSAVEL_1_EMAIL;
	}
	public void setRESPONSAVEL_1_EMAIL(String rESPONSAVEL_1_EMAIL) {
		RESPONSAVEL_1_EMAIL = rESPONSAVEL_1_EMAIL;
	}
	public String getRESPONSAVEL_1_CN_TEL() {
		return RESPONSAVEL_1_CN_TEL;
	}
	public void setRESPONSAVEL_1_CN_TEL(String rESPONSAVEL_1_CN_TEL) {
		RESPONSAVEL_1_CN_TEL = rESPONSAVEL_1_CN_TEL;
	}
	public String getRESPONSAVEL_1_TELEFONE() {
		return RESPONSAVEL_1_TELEFONE;
	}
	public void setRESPONSAVEL_1_TELEFONE(String rESPONSAVEL_1_TELEFONE) {
		RESPONSAVEL_1_TELEFONE = rESPONSAVEL_1_TELEFONE;
	}
	public String getRESPONSAVEL_1_RAMAL() {
		return RESPONSAVEL_1_RAMAL;
	}
	public void setRESPONSAVEL_1_RAMAL(String rESPONSAVEL_1_RAMAL) {
		RESPONSAVEL_1_RAMAL = rESPONSAVEL_1_RAMAL;
	}
	public String getRESPONSAVEL_1_CN_CEL() {
		return RESPONSAVEL_1_CN_CEL;
	}
	public void setRESPONSAVEL_1_CN_CEL(String rESPONSAVEL_1_CN_CEL) {
		RESPONSAVEL_1_CN_CEL = rESPONSAVEL_1_CN_CEL;
	}
	public String getRESPONSAVEL_1_CELULAR() {
		return RESPONSAVEL_1_CELULAR;
	}
	public void setRESPONSAVEL_1_CELULAR(String rESPONSAVEL_1_CELULAR) {
		RESPONSAVEL_1_CELULAR = rESPONSAVEL_1_CELULAR;
	}
	public String getRESPONSAVEL_2_NOME() {
		return RESPONSAVEL_2_NOME;
	}
	public void setRESPONSAVEL_2_NOME(String rESPONSAVEL_2_NOME) {
		RESPONSAVEL_2_NOME = rESPONSAVEL_2_NOME;
	}
	public String getRESPONSAVEL_2_CARGO() {
		return RESPONSAVEL_2_CARGO;
	}
	public void setRESPONSAVEL_2_CARGO(String rESPONSAVEL_2_CARGO) {
		RESPONSAVEL_2_CARGO = rESPONSAVEL_2_CARGO;
	}
	public String getRESPONSAVEL_2_EMAIL() {
		return RESPONSAVEL_2_EMAIL;
	}
	public void setRESPONSAVEL_2_EMAIL(String rESPONSAVEL_2_EMAIL) {
		RESPONSAVEL_2_EMAIL = rESPONSAVEL_2_EMAIL;
	}
	public String getRESPONSAVEL_2_CN_TEL() {
		return RESPONSAVEL_2_CN_TEL;
	}
	public void setRESPONSAVEL_2_CN_TEL(String rESPONSAVEL_2_CN_TEL) {
		RESPONSAVEL_2_CN_TEL = rESPONSAVEL_2_CN_TEL;
	}
	public String getRESPONSAVEL_2_TELEFONE() {
		return RESPONSAVEL_2_TELEFONE;
	}
	public void setRESPONSAVEL_2_TELEFONE(String rESPONSAVEL_2_TELEFONE) {
		RESPONSAVEL_2_TELEFONE = rESPONSAVEL_2_TELEFONE;
	}
	public String getRESPONSAVEL_2_RAMAL() {
		return RESPONSAVEL_2_RAMAL;
	}
	public void setRESPONSAVEL_2_RAMAL(String rESPONSAVEL_2_RAMAL) {
		RESPONSAVEL_2_RAMAL = rESPONSAVEL_2_RAMAL;
	}
	public String getRESPONSAVEL_2_CN_CEL() {
		return RESPONSAVEL_2_CN_CEL;
	}
	public void setRESPONSAVEL_2_CN_CEL(String rESPONSAVEL_2_CN_CEL) {
		RESPONSAVEL_2_CN_CEL = rESPONSAVEL_2_CN_CEL;
	}
	public String getRESPONSAVEL_2_CELULAR() {
		return RESPONSAVEL_2_CELULAR;
	}
	public void setRESPONSAVEL_2_CELULAR(String rESPONSAVEL_2_CELULAR) {
		RESPONSAVEL_2_CELULAR = rESPONSAVEL_2_CELULAR;
	}
	public String getKM_SEDE() {
		return KM_SEDE;
	}
	public void setKM_SEDE(String kM_SEDE) {
		KM_SEDE = kM_SEDE;
	}
	public String getOBSERVACAO() {
		return OBSERVACAO;
	}
	public void setOBSERVACAO(String oBSERVACAO) {
		OBSERVACAO = oBSERVACAO;
	}
	public String getDIST_BUSCA() {
		return DIST_BUSCA;
	}
	public void setDIST_BUSCA(String dIST_BUSCA) {
		DIST_BUSCA = dIST_BUSCA;
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
