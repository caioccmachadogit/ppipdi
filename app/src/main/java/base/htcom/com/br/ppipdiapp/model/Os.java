package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class Os implements Serializable{
	private static final long serialVersionUID = 1L;
		@Column
		private String LINHA,CODIGO,VALIDADE,CLIENTE,CONTRATO,CONTRATO_FASE,REF_ROTA,PRE,ORDEM_COMPRA,LOTE_OV_NUM,LOTE_OV_LIN,LOTE_OV_ATV,
		OV_CHAMADO_NUM,OS_MOBILE_REC,CONTROLE_NUM,CONTROLE_CLI,OS_SITUACAO,OS_CONTROLE,SEQUENCIAL,TECNOLOGIA,COD_ENTIDADE,PONTA_DISTANTE,
		ENLACE_DIAGRAMA,BOX_DE,BOX_PARA,ALOCADO_EQUIP,ALOCADO_CABO,ESTRUT_ORDEM,LOTE_CONTROLE,CONFERENCIA_SITE,CONFERENCIA_EV01,
		CONFERENCIA_EV02,CONFERENCIA_EQUP,REGIAO,UF,PROJETO,OS_TIPO,ANEXOS,SOLIC_VISTORIA_NOME,SOLIC_VISTORIA_DATA,SOLIC_VISTORIA_MATR,
		PREV_OV_ATEND_INI,PREV_OV_ATEND_FIM,SOLIC_VISTORIA_EMPRESA,SOLIC_VISTORIA_EMPRESA_COD,SOLIC_VISTORIA_EMPRESA_KM,SOLIC_OV_SERV_NOME,
		SOLIC_OV_SERV_DATA,SOLIC_OV_SERV_INFO,CONC_OV_SERV_NOME,CONC_OV_SERV_DATA,CONC_OV_SERV_INFO,SITUACAO_ATENDIMENTO,DIVERGE,DIVERGE_OBS,
		OBSERVACAO_OS,ALERTA_CAPA,AREA_CHAO,OBSERVACAO_MASTER,CONTROLE_ROTINA,CONTROLE_MSG,ATUALIZACAO_NOME,ATUALIZACAO_DATA,ATUALIZACAO_DATA_INV,
		INSERCAO_DATA,INSERCAO_NOME;
		
	
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
		public String getREF_ROTA() {
			return REF_ROTA;
		}
		public void setREF_ROTA(String rEF_ROTA) {
			REF_ROTA = rEF_ROTA;
		}
		public String getPRE() {
			return PRE;
		}
		public void setPRE(String pRE) {
			PRE = pRE;
		}
		public String getORDEM_COMPRA() {
			return ORDEM_COMPRA;
		}
		public void setORDEM_COMPRA(String oRDEM_COMPRA) {
			ORDEM_COMPRA = oRDEM_COMPRA;
		}
		public String getLOTE_OV_NUM() {
			return LOTE_OV_NUM;
		}
		public void setLOTE_OV_NUM(String lOTE_OV_NUM) {
			LOTE_OV_NUM = lOTE_OV_NUM;
		}
		public String getLOTE_OV_LIN() {
			return LOTE_OV_LIN;
		}
		public void setLOTE_OV_LIN(String lOTE_OV_LIN) {
			LOTE_OV_LIN = lOTE_OV_LIN;
		}
		public String getLOTE_OV_ATV() {
			return LOTE_OV_ATV;
		}
		public void setLOTE_OV_ATV(String lOTE_OV_ATV) {
			LOTE_OV_ATV = lOTE_OV_ATV;
		}
		public String getOV_CHAMADO_NUM() {
			return OV_CHAMADO_NUM;
		}
		public void setOV_CHAMADO_NUM(String oV_CHAMADO_NUM) {
			OV_CHAMADO_NUM = oV_CHAMADO_NUM;
		}
		public String getOS_MOBILE_REC() {
			return OS_MOBILE_REC;
		}
		public void setOS_MOBILE_REC(String oS_MOBILE_REC) {
			OS_MOBILE_REC = oS_MOBILE_REC;
		}
		public String getCONTROLE_NUM() {
			return CONTROLE_NUM;
		}
		public void setCONTROLE_NUM(String cONTROLE_NUM) {
			CONTROLE_NUM = cONTROLE_NUM;
		}
		public String getCONTROLE_CLI() {
			return CONTROLE_CLI;
		}
		public void setCONTROLE_CLI(String cONTROLE_CLI) {
			CONTROLE_CLI = cONTROLE_CLI;
		}
		public String getOS_SITUACAO() {
			return OS_SITUACAO;
		}
		public void setOS_SITUACAO(String oS_SITUACAO) {
			OS_SITUACAO = oS_SITUACAO;
		}
		public String getOS_CONTROLE() {
			return OS_CONTROLE;
		}
		public void setOS_CONTROLE(String oS_CONTROLE) {
			OS_CONTROLE = oS_CONTROLE;
		}
		public String getSEQUENCIAL() {
			return SEQUENCIAL;
		}
		public void setSEQUENCIAL(String sEQUENCIAL) {
			SEQUENCIAL = sEQUENCIAL;
		}
		public String getTECNOLOGIA() {
			return TECNOLOGIA;
		}
		public void setTECNOLOGIA(String tECNOLOGIA) {
			TECNOLOGIA = tECNOLOGIA;
		}
		public String getCOD_ENTIDADE() {
			return COD_ENTIDADE;
		}
		public void setCOD_ENTIDADE(String cOD_ENTIDADE) {
			COD_ENTIDADE = cOD_ENTIDADE;
		}
		public String getPONTA_DISTANTE() {
			return PONTA_DISTANTE;
		}
		public void setPONTA_DISTANTE(String pONTA_DISTANTE) {
			PONTA_DISTANTE = pONTA_DISTANTE;
		}
		public String getENLACE_DIAGRAMA() {
			return ENLACE_DIAGRAMA;
		}
		public void setENLACE_DIAGRAMA(String eNLACE_DIAGRAMA) {
			ENLACE_DIAGRAMA = eNLACE_DIAGRAMA;
		}
		public String getBOX_DE() {
			return BOX_DE;
		}
		public void setBOX_DE(String bOX_DE) {
			BOX_DE = bOX_DE;
		}
		public String getBOX_PARA() {
			return BOX_PARA;
		}
		public void setBOX_PARA(String bOX_PARA) {
			BOX_PARA = bOX_PARA;
		}
		public String getALOCADO_EQUIP() {
			return ALOCADO_EQUIP;
		}
		public void setALOCADO_EQUIP(String aLOCADO_EQUIP) {
			ALOCADO_EQUIP = aLOCADO_EQUIP;
		}
		public String getALOCADO_CABO() {
			return ALOCADO_CABO;
		}
		public void setALOCADO_CABO(String aLOCADO_CABO) {
			ALOCADO_CABO = aLOCADO_CABO;
		}
		public String getESTRUT_ORDEM() {
			return ESTRUT_ORDEM;
		}
		public void setESTRUT_ORDEM(String eSTRUT_ORDEM) {
			ESTRUT_ORDEM = eSTRUT_ORDEM;
		}
		public String getLOTE_CONTROLE() {
			return LOTE_CONTROLE;
		}
		public void setLOTE_CONTROLE(String lOTE_CONTROLE) {
			LOTE_CONTROLE = lOTE_CONTROLE;
		}
		public String getCONFERENCIA_SITE() {
			return CONFERENCIA_SITE;
		}
		public void setCONFERENCIA_SITE(String cONFERENCIA_SITE) {
			CONFERENCIA_SITE = cONFERENCIA_SITE;
		}
		public String getCONFERENCIA_EV01() {
			return CONFERENCIA_EV01;
		}
		public void setCONFERENCIA_EV01(String cONFERENCIA_EV01) {
			CONFERENCIA_EV01 = cONFERENCIA_EV01;
		}
		public String getCONFERENCIA_EV02() {
			return CONFERENCIA_EV02;
		}
		public void setCONFERENCIA_EV02(String cONFERENCIA_EV02) {
			CONFERENCIA_EV02 = cONFERENCIA_EV02;
		}
		public String getCONFERENCIA_EQUP() {
			return CONFERENCIA_EQUP;
		}
		public void setCONFERENCIA_EQUP(String cONFERENCIA_EQUP) {
			CONFERENCIA_EQUP = cONFERENCIA_EQUP;
		}
		public String getREGIAO() {
			return REGIAO;
		}
		public void setREGIAO(String rEGIAO) {
			REGIAO = rEGIAO;
		}
		public String getUF() {
			return UF;
		}
		public void setUF(String uF) {
			UF = uF;
		}
		public String getPROJETO() {
			return PROJETO;
		}
		public void setPROJETO(String pROJETO) {
			PROJETO = pROJETO;
		}
		public String getOS_TIPO() {
			return OS_TIPO;
		}
		public void setOS_TIPO(String oS_TIPO) {
			OS_TIPO = oS_TIPO;
		}
		public String getANEXOS() {
			return ANEXOS;
		}
		public void setANEXOS(String aNEXOS) {
			ANEXOS = aNEXOS;
		}
		public String getSOLIC_VISTORIA_NOME() {
			return SOLIC_VISTORIA_NOME;
		}
		public void setSOLIC_VISTORIA_NOME(String sOLIC_VISTORIA_NOME) {
			SOLIC_VISTORIA_NOME = sOLIC_VISTORIA_NOME;
		}
		public String getSOLIC_VISTORIA_DATA() {
			return SOLIC_VISTORIA_DATA;
		}
		public void setSOLIC_VISTORIA_DATA(String sOLIC_VISTORIA_DATA) {
			SOLIC_VISTORIA_DATA = sOLIC_VISTORIA_DATA;
		}
		public String getSOLIC_VISTORIA_MATR() {
			return SOLIC_VISTORIA_MATR;
		}
		public void setSOLIC_VISTORIA_MATR(String sOLIC_VISTORIA_MATR) {
			SOLIC_VISTORIA_MATR = sOLIC_VISTORIA_MATR;
		}
		public String getPREV_OV_ATEND_INI() {
			return PREV_OV_ATEND_INI;
		}
		public void setPREV_OV_ATEND_INI(String pREV_OV_ATEND_INI) {
			PREV_OV_ATEND_INI = pREV_OV_ATEND_INI;
		}
		public String getPREV_OV_ATEND_FIM() {
			return PREV_OV_ATEND_FIM;
		}
		public void setPREV_OV_ATEND_FIM(String pREV_OV_ATEND_FIM) {
			PREV_OV_ATEND_FIM = pREV_OV_ATEND_FIM;
		}
		public String getSOLIC_VISTORIA_EMPRESA() {
			return SOLIC_VISTORIA_EMPRESA;
		}
		public void setSOLIC_VISTORIA_EMPRESA(String sOLIC_VISTORIA_EMPRESA) {
			SOLIC_VISTORIA_EMPRESA = sOLIC_VISTORIA_EMPRESA;
		}
		public String getSOLIC_VISTORIA_EMPRESA_COD() {
			return SOLIC_VISTORIA_EMPRESA_COD;
		}
		public void setSOLIC_VISTORIA_EMPRESA_COD(String sOLIC_VISTORIA_EMPRESA_COD) {
			SOLIC_VISTORIA_EMPRESA_COD = sOLIC_VISTORIA_EMPRESA_COD;
		}
		public String getSOLIC_VISTORIA_EMPRESA_KM() {
			return SOLIC_VISTORIA_EMPRESA_KM;
		}
		public void setSOLIC_VISTORIA_EMPRESA_KM(String sOLIC_VISTORIA_EMPRESA_KM) {
			SOLIC_VISTORIA_EMPRESA_KM = sOLIC_VISTORIA_EMPRESA_KM;
		}
		public String getSOLIC_OV_SERV_NOME() {
			return SOLIC_OV_SERV_NOME;
		}
		public void setSOLIC_OV_SERV_NOME(String sOLIC_OV_SERV_NOME) {
			SOLIC_OV_SERV_NOME = sOLIC_OV_SERV_NOME;
		}
		public String getSOLIC_OV_SERV_DATA() {
			return SOLIC_OV_SERV_DATA;
		}
		public void setSOLIC_OV_SERV_DATA(String sOLIC_OV_SERV_DATA) {
			SOLIC_OV_SERV_DATA = sOLIC_OV_SERV_DATA;
		}
		public String getSOLIC_OV_SERV_INFO() {
			return SOLIC_OV_SERV_INFO;
		}
		public void setSOLIC_OV_SERV_INFO(String sOLIC_OV_SERV_INFO) {
			SOLIC_OV_SERV_INFO = sOLIC_OV_SERV_INFO;
		}
		public String getCONC_OV_SERV_NOME() {
			return CONC_OV_SERV_NOME;
		}
		public void setCONC_OV_SERV_NOME(String cONC_OV_SERV_NOME) {
			CONC_OV_SERV_NOME = cONC_OV_SERV_NOME;
		}
		public String getCONC_OV_SERV_DATA() {
			return CONC_OV_SERV_DATA;
		}
		public void setCONC_OV_SERV_DATA(String cONC_OV_SERV_DATA) {
			CONC_OV_SERV_DATA = cONC_OV_SERV_DATA;
		}
		public String getCONC_OV_SERV_INFO() {
			return CONC_OV_SERV_INFO;
		}
		public void setCONC_OV_SERV_INFO(String cONC_OV_SERV_INFO) {
			CONC_OV_SERV_INFO = cONC_OV_SERV_INFO;
		}
		public String getSITUACAO_ATENDIMENTO() {
			return SITUACAO_ATENDIMENTO;
		}
		public void setSITUACAO_ATENDIMENTO(String sITUACAO_ATENDIMENTO) {
			SITUACAO_ATENDIMENTO = sITUACAO_ATENDIMENTO;
		}
		public String getDIVERGE() {
			return DIVERGE;
		}
		public void setDIVERGE(String dIVERGE) {
			DIVERGE = dIVERGE;
		}
		public String getDIVERGE_OBS() {
			return DIVERGE_OBS;
		}
		public void setDIVERGE_OBS(String dIVERGE_OBS) {
			DIVERGE_OBS = dIVERGE_OBS;
		}
		public String getOBSERVACAO_OS() {
			return OBSERVACAO_OS;
		}
		public void setOBSERVACAO_OS(String oBSERVACAO_OS) {
			OBSERVACAO_OS = oBSERVACAO_OS;
		}
		public String getALERTA_CAPA() {
			return ALERTA_CAPA;
		}
		public void setALERTA_CAPA(String aLERTA_CAPA) {
			ALERTA_CAPA = aLERTA_CAPA;
		}
		public String getAREA_CHAO() {
			return AREA_CHAO;
		}
		public void setAREA_CHAO(String aREA_CHAO) {
			AREA_CHAO = aREA_CHAO;
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
