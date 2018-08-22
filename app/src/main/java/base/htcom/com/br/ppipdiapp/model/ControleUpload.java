package base.htcom.com.br.ppipdiapp.model;

import java.io.Serializable;

import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.Column;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseBeans;

@DatabaseBeans
public class ControleUpload implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column
	private String LINHA,	CODIGO,	VALIDADE,	CLIENTE,	CONTRATO,	CONTRATO_FASE,	COD_ENTIDADE,	ESTRUT_ORDEM,	LOTE_CONTROLE,
	OV_CHAMADO_NUM,	APROVADO,	ARQ_STATUS,	Arquivo_Carregado,	COMPARTILHAMENTO,	ARQ_NOME_REF,	ARQ_APLICACAO,	ARQ_OBSERVACAO,
	ENDERECO_VALIDADO,	COLUNA_REF_ID,	COLUNA_REF_logradouro_tipo,	COLUNA_REF_logradouro_nome,	COLUNA_REF_logradouro_num,	COLUNA_REF_logradouro_bairro,
	COLUNA_REF_logradouro_cidade,	COLUNA_REF_logradouro_uf,	COLUNA_REF_logradouro_cep_P,	COLUNA_REF_logradouro_cep_S,	COLUNA_REF_latitude,
	COLUNA_REF_longitude,	Documento_Numero,	DATA_DOC,	DATA_DOC_INV,	GRUPO,	Session_ID,	IP,	APLICACAO,	Acao,	ARQ_TIPO,
	ARQ_NOME_ORIGINAL,	ARQ_ORIGEM,	ARQ_NOME_SIST,	ARQ_NOME_SIST_NM,	ARQ_DESTINO,	ARQ_DIM,	ARQ_TAM,	EXPIRA_EM,	EMAIL_ENVIADO_DATA,
	EMAIL_ENVIADO_PARA,	DOWNLOAD_NUM,	OBSERVACAO,	INSERCAO_email,	INSERCAO_ga,	INSERCAO_IP,	system_obs,	INSERCAO_Data_INV,
	INSERCAO_DATA,	INSERCAO_NOME,	EXCLUSAO_email,	EXCLUSAO_ga,	EXCLUSAO_IP,	EXCLUSAO_Data_INV,	EXCLUSAO_DATA,	EXCLUSAO_NOME;
	public String getLinha() {
		return LINHA;
	}
	public void setLinha(String linha) {
		LINHA = linha;
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
	public String getOV_CHAMADO_NUM() {
		return OV_CHAMADO_NUM;
	}
	public void setOV_CHAMADO_NUM(String oV_CHAMADO_NUM) {
		OV_CHAMADO_NUM = oV_CHAMADO_NUM;
	}
	public String getAPROVADO() {
		return APROVADO;
	}
	public void setAPROVADO(String aPROVADO) {
		APROVADO = aPROVADO;
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
	public String getARQ_APLICACAO() {
		return ARQ_APLICACAO;
	}
	public void setARQ_APLICACAO(String aRQ_APLICACAO) {
		ARQ_APLICACAO = aRQ_APLICACAO;
	}
	public String getARQ_OBSERVACAO() {
		return ARQ_OBSERVACAO;
	}
	public void setARQ_OBSERVACAO(String aRQ_OBSERVACAO) {
		ARQ_OBSERVACAO = aRQ_OBSERVACAO;
	}
	public String getENDERECO_VALIDADO() {
		return ENDERECO_VALIDADO;
	}
	public void setENDERECO_VALIDADO(String eNDERECO_VALIDADO) {
		ENDERECO_VALIDADO = eNDERECO_VALIDADO;
	}
	public String getCOLUNA_REF_ID() {
		return COLUNA_REF_ID;
	}
	public void setCOLUNA_REF_ID(String cOLUNA_REF_ID) {
		COLUNA_REF_ID = cOLUNA_REF_ID;
	}
	public String getCOLUNA_REF_logradouro_tipo() {
		return COLUNA_REF_logradouro_tipo;
	}
	public void setCOLUNA_REF_logradouro_tipo(String cOLUNA_REF_logradouro_tipo) {
		COLUNA_REF_logradouro_tipo = cOLUNA_REF_logradouro_tipo;
	}
	public String getCOLUNA_REF_logradouro_nome() {
		return COLUNA_REF_logradouro_nome;
	}
	public void setCOLUNA_REF_logradouro_nome(String cOLUNA_REF_logradouro_nome) {
		COLUNA_REF_logradouro_nome = cOLUNA_REF_logradouro_nome;
	}
	public String getCOLUNA_REF_logradouro_num() {
		return COLUNA_REF_logradouro_num;
	}
	public void setCOLUNA_REF_logradouro_num(String cOLUNA_REF_logradouro_num) {
		COLUNA_REF_logradouro_num = cOLUNA_REF_logradouro_num;
	}
	public String getCOLUNA_REF_logradouro_bairro() {
		return COLUNA_REF_logradouro_bairro;
	}
	public void setCOLUNA_REF_logradouro_bairro(String cOLUNA_REF_logradouro_bairro) {
		COLUNA_REF_logradouro_bairro = cOLUNA_REF_logradouro_bairro;
	}
	public String getCOLUNA_REF_logradouro_cidade() {
		return COLUNA_REF_logradouro_cidade;
	}
	public void setCOLUNA_REF_logradouro_cidade(String cOLUNA_REF_logradouro_cidade) {
		COLUNA_REF_logradouro_cidade = cOLUNA_REF_logradouro_cidade;
	}
	public String getCOLUNA_REF_logradouro_uf() {
		return COLUNA_REF_logradouro_uf;
	}
	public void setCOLUNA_REF_logradouro_uf(String cOLUNA_REF_logradouro_uf) {
		COLUNA_REF_logradouro_uf = cOLUNA_REF_logradouro_uf;
	}
	public String getCOLUNA_REF_logradouro_cep_P() {
		return COLUNA_REF_logradouro_cep_P;
	}
	public void setCOLUNA_REF_logradouro_cep_P(String cOLUNA_REF_logradouro_cep_P) {
		COLUNA_REF_logradouro_cep_P = cOLUNA_REF_logradouro_cep_P;
	}
	public String getCOLUNA_REF_logradouro_cep_S() {
		return COLUNA_REF_logradouro_cep_S;
	}
	public void setCOLUNA_REF_logradouro_cep_S(String cOLUNA_REF_logradouro_cep_S) {
		COLUNA_REF_logradouro_cep_S = cOLUNA_REF_logradouro_cep_S;
	}
	public String getCOLUNA_REF_latitude() {
		return COLUNA_REF_latitude;
	}
	public void setCOLUNA_REF_latitude(String cOLUNA_REF_latitude) {
		COLUNA_REF_latitude = cOLUNA_REF_latitude;
	}
	public String getCOLUNA_REF_longitude() {
		return COLUNA_REF_longitude;
	}
	public void setCOLUNA_REF_longitude(String cOLUNA_REF_longitude) {
		COLUNA_REF_longitude = cOLUNA_REF_longitude;
	}
	public String getDocumento_Numero() {
		return Documento_Numero;
	}
	public void setDocumento_Numero(String documento_Numero) {
		Documento_Numero = documento_Numero;
	}
	public String getDATA_DOC() {
		return DATA_DOC;
	}
	public void setDATA_DOC(String dATA_DOC) {
		DATA_DOC = dATA_DOC;
	}
	public String getDATA_DOC_INV() {
		return DATA_DOC_INV;
	}
	public void setDATA_DOC_INV(String dATA_DOC_INV) {
		DATA_DOC_INV = dATA_DOC_INV;
	}
	public String getGRUPO() {
		return GRUPO;
	}
	public void setGRUPO(String gRUPO) {
		GRUPO = gRUPO;
	}
	public String getSession_ID() {
		return Session_ID;
	}
	public void setSession_ID(String session_ID) {
		Session_ID = session_ID;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getAPLICACAO() {
		return APLICACAO;
	}
	public void setAPLICACAO(String aPLICACAO) {
		APLICACAO = aPLICACAO;
	}
	public String getAcao() {
		return Acao;
	}
	public void setAcao(String acao) {
		Acao = acao;
	}
	public String getARQ_TIPO() {
		return ARQ_TIPO;
	}
	public void setARQ_TIPO(String aRQ_TIPO) {
		ARQ_TIPO = aRQ_TIPO;
	}
	public String getARQ_NOME_ORIGINAL() {
		return ARQ_NOME_ORIGINAL;
	}
	public void setARQ_NOME_ORIGINAL(String aRQ_NOME_ORIGINAL) {
		ARQ_NOME_ORIGINAL = aRQ_NOME_ORIGINAL;
	}
	public String getARQ_ORIGEM() {
		return ARQ_ORIGEM;
	}
	public void setARQ_ORIGEM(String aRQ_ORIGEM) {
		ARQ_ORIGEM = aRQ_ORIGEM;
	}
	public String getARQ_NOME_SIST() {
		return ARQ_NOME_SIST;
	}
	public void setARQ_NOME_SIST(String aRQ_NOME_SIST) {
		ARQ_NOME_SIST = aRQ_NOME_SIST;
	}
	public String getARQ_NOME_SIST_NM() {
		return ARQ_NOME_SIST_NM;
	}
	public void setARQ_NOME_SIST_NM(String aRQ_NOME_SIST_NM) {
		ARQ_NOME_SIST_NM = aRQ_NOME_SIST_NM;
	}
	public String getARQ_DESTINO() {
		return ARQ_DESTINO;
	}
	public void setARQ_DESTINO(String aRQ_DESTINO) {
		ARQ_DESTINO = aRQ_DESTINO;
	}
	public String getARQ_DIM() {
		return ARQ_DIM;
	}
	public void setARQ_DIM(String aRQ_DIM) {
		ARQ_DIM = aRQ_DIM;
	}
	public String getARQ_TAM() {
		return ARQ_TAM;
	}
	public void setARQ_TAM(String aRQ_TAM) {
		ARQ_TAM = aRQ_TAM;
	}
	public String getEXPIRA_EM() {
		return EXPIRA_EM;
	}
	public void setEXPIRA_EM(String eXPIRA_EM) {
		EXPIRA_EM = eXPIRA_EM;
	}
	public String getEMAIL_ENVIADO_DATA() {
		return EMAIL_ENVIADO_DATA;
	}
	public void setEMAIL_ENVIADO_DATA(String eMAIL_ENVIADO_DATA) {
		EMAIL_ENVIADO_DATA = eMAIL_ENVIADO_DATA;
	}
	public String getEMAIL_ENVIADO_PARA() {
		return EMAIL_ENVIADO_PARA;
	}
	public void setEMAIL_ENVIADO_PARA(String eMAIL_ENVIADO_PARA) {
		EMAIL_ENVIADO_PARA = eMAIL_ENVIADO_PARA;
	}
	public String getDOWNLOAD_NUM() {
		return DOWNLOAD_NUM;
	}
	public void setDOWNLOAD_NUM(String dOWNLOAD_NUM) {
		DOWNLOAD_NUM = dOWNLOAD_NUM;
	}
	public String getOBSERVACAO() {
		return OBSERVACAO;
	}
	public void setOBSERVACAO(String oBSERVACAO) {
		OBSERVACAO = oBSERVACAO;
	}
	public String getINSERCAO_email() {
		return INSERCAO_email;
	}
	public void setINSERCAO_email(String iNSERCAO_email) {
		INSERCAO_email = iNSERCAO_email;
	}
	public String getINSERCAO_ga() {
		return INSERCAO_ga;
	}
	public void setINSERCAO_ga(String iNSERCAO_ga) {
		INSERCAO_ga = iNSERCAO_ga;
	}
	public String getINSERCAO_IP() {
		return INSERCAO_IP;
	}
	public void setINSERCAO_IP(String iNSERCAO_IP) {
		INSERCAO_IP = iNSERCAO_IP;
	}
	public String getSystem_obs() {
		return system_obs;
	}
	public void setSystem_obs(String system_obs) {
		this.system_obs = system_obs;
	}
	public String getINSERCAO_Data_INV() {
		return INSERCAO_Data_INV;
	}
	public void setINSERCAO_Data_INV(String iNSERCAO_Data_INV) {
		INSERCAO_Data_INV = iNSERCAO_Data_INV;
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
	public String getEXCLUSAO_email() {
		return EXCLUSAO_email;
	}
	public void setEXCLUSAO_email(String eXCLUSAO_email) {
		EXCLUSAO_email = eXCLUSAO_email;
	}
	public String getEXCLUSAO_ga() {
		return EXCLUSAO_ga;
	}
	public void setEXCLUSAO_ga(String eXCLUSAO_ga) {
		EXCLUSAO_ga = eXCLUSAO_ga;
	}
	public String getEXCLUSAO_IP() {
		return EXCLUSAO_IP;
	}
	public void setEXCLUSAO_IP(String eXCLUSAO_IP) {
		EXCLUSAO_IP = eXCLUSAO_IP;
	}
	public String getEXCLUSAO_Data_INV() {
		return EXCLUSAO_Data_INV;
	}
	public void setEXCLUSAO_Data_INV(String eXCLUSAO_Data_INV) {
		EXCLUSAO_Data_INV = eXCLUSAO_Data_INV;
	}
	public String getEXCLUSAO_DATA() {
		return EXCLUSAO_DATA;
	}
	public void setEXCLUSAO_DATA(String eXCLUSAO_DATA) {
		EXCLUSAO_DATA = eXCLUSAO_DATA;
	}
	public String getEXCLUSAO_NOME() {
		return EXCLUSAO_NOME;
	}
	public void setEXCLUSAO_NOME(String eXCLUSAO_NOME) {
		EXCLUSAO_NOME = eXCLUSAO_NOME;
	}
	
	


}
