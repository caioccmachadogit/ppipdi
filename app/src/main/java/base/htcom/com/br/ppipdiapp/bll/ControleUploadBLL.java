package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import base.htcom.com.br.ppipdiapp.model.ControleUpload;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class ControleUploadBLL {
	private final String TAG = getClass().getSimpleName();
	private final String NomeTabela = "controle_upload";
	public static String createTable = "CREATE TABLE 'controle_upload' ("+
			"LINHA INTEGER PRIMARY KEY AUTOINCREMENT,"+
			  "CODIGO varchar(50) DEFAULT NULL,"+
			  "VALIDADE varchar(1) DEFAULT NULL,"+
			  "CLIENTE varchar(10) DEFAULT NULL,"+
			  "CONTRATO varchar(20) DEFAULT NULL,"+
			  "CONTRATO_FASE varchar(30) DEFAULT NULL,"+
			  "COD_ENTIDADE varchar(20) DEFAULT NULL,"+
			  "ESTRUT_ORDEM varchar(3) DEFAULT NULL,"+
			  "LOTE_CONTROLE varchar(40) DEFAULT NULL,"+
			  "OV_CHAMADO_NUM varchar(30) DEFAULT NULL,"+
			  "APROVADO varchar(1) DEFAULT NULL,"+
			  "ARQ_STATUS varchar(10) DEFAULT NULL,"+
			  "Arquivo_Carregado varchar(120) DEFAULT NULL,"+
			  "COMPARTILHAMENTO varchar(1) DEFAULT NULL,"+
			  "ARQ_NOME_REF varchar(40) DEFAULT NULL,"+
			  "ARQ_APLICACAO varchar(12) DEFAULT NULL,"+
			  "ARQ_OBSERVACAO varchar(255) DEFAULT NULL,"+
			  "ENDERECO_VALIDADO varchar(20) DEFAULT NULL,"+
			  "COLUNA_REF_ID varchar(3) DEFAULT NULL,"+
			  "COLUNA_REF_logradouro_tipo varchar(3) DEFAULT NULL,"+
			  "COLUNA_REF_logradouro_nome varchar(3) DEFAULT NULL,"+
			  "COLUNA_REF_logradouro_num varchar(3) DEFAULT NULL,"+
			  "COLUNA_REF_logradouro_bairro varchar(3) DEFAULT NULL,"+
			  "COLUNA_REF_logradouro_cidade varchar(3) DEFAULT NULL,"+
			  "COLUNA_REF_logradouro_uf varchar(3) DEFAULT NULL,"+
			  "COLUNA_REF_logradouro_cep_P varchar(3) DEFAULT NULL,"+
			  "COLUNA_REF_logradouro_cep_S varchar(3) DEFAULT NULL,"+
			  "COLUNA_REF_latitude varchar(3) DEFAULT NULL,"+
			  "COLUNA_REF_longitude varchar(3) DEFAULT NULL,"+
			  "Documento_Numero varchar(100) DEFAULT NULL,"+
			  "DATA_DOC varchar(30) DEFAULT NULL,"+
			  "DATA_DOC_INV varchar(30) DEFAULT NULL,"+
			  "GRUPO varchar(50) DEFAULT NULL,"+
			  "Session_ID varchar(50) DEFAULT NULL,"+
			  "IP varchar(50) DEFAULT NULL,"+
			  "APLICACAO varchar(50) DEFAULT NULL,"+
			  "Acao varchar(50) DEFAULT NULL,"+
			  "ARQ_TIPO varchar(30) DEFAULT NULL,"+
			  "ARQ_NOME_ORIGINAL varchar(255) DEFAULT NULL,"+
			  "ARQ_ORIGEM varchar(255) DEFAULT NULL,"+
			  "ARQ_NOME_SIST varchar(255) DEFAULT NULL,"+
			  "ARQ_NOME_SIST_NM varchar(255) DEFAULT NULL,"+
			  "ARQ_DESTINO varchar(255) DEFAULT NULL,"+
			  "ARQ_DIM varchar(12) DEFAULT NULL,"+
			  "ARQ_TAM varchar(12) DEFAULT NULL,"+
			  "EXPIRA_EM varchar(50) DEFAULT NULL,"+
			  "EMAIL_ENVIADO_DATA varchar(40) DEFAULT NULL,"+
			  "EMAIL_ENVIADO_PARA varchar(120) DEFAULT NULL,"+
			  "DOWNLOAD_NUM varchar(6) DEFAULT NULL,"+
			  "OBSERVACAO varchar(250) DEFAULT NULL,"+
			  "INSERCAO_email varchar(80) DEFAULT NULL,"+
			  "INSERCAO_ga varchar(3) DEFAULT NULL,"+
			  "INSERCAO_IP varchar(20) DEFAULT NULL,"+
			  "system_obs varchar(255) DEFAULT NULL,"+
			  "INSERCAO_Data_INV varchar(30) DEFAULT NULL,"+
			  "INSERCAO_DATA varchar(30) DEFAULT NULL,"+
			  "INSERCAO_NOME varchar(50) DEFAULT NULL,"+
			  "EXCLUSAO_email varchar(80) DEFAULT NULL,"+
			  "EXCLUSAO_ga varchar(3) DEFAULT NULL,"+
			  "EXCLUSAO_IP varchar(20) DEFAULT NULL,"+
			  "EXCLUSAO_Data_INV varchar(30) DEFAULT NULL,"+
			  "EXCLUSAO_DATA varchar(30) DEFAULT NULL,"+
			  "EXCLUSAO_NOME varchar(50) DEFAULT NULL);";

	GerenciadorDB gerDB = new GerenciadorDB();
	public long Insert(Context context, ControleUpload controleUpload) throws Exception{
		long resultadoInsercao = 0;
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(controleUpload);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR INSERT TAB: "+NomeTabela,context);
			throw ex;
		}
		return resultadoInsercao;
	}
	
	public ControleUpload listarByArqCarregado(Context context, String arqCarregado, String ovChamado) throws Exception{
		Cursor retorno;
		String dump;
		List<ControleUpload> lst = new ArrayList<ControleUpload>();
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select * " +
					"FROM "+NomeTabela+" WHERE Arquivo_Carregado = '"+arqCarregado+"' AND OV_CHAMADO_NUM = '"+ovChamado+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, ControleUpload.class);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR UPDATE TAB: "+NomeTabela,context);
			throw ex;
		}
		Log.d(TAG,"listarByArqCarregado->"+dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}

	public long update(Context context, ControleUpload controleUpload) throws Exception{
		long resultadoEdicao = 0;
		String[] argumentos = {controleUpload.getLinha()};
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(controleUpload);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoEdicao = GerenciadorDB.db.update(NomeTabela, contentValues, "LINHA=?", argumentos);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		return resultadoEdicao;
	}

	public List<ControleUpload> listarByOvChamado(Context context, String ovChamado) throws Exception{
		Cursor retorno;
		String dump;
		List<ControleUpload> lst = new ArrayList<ControleUpload>();
		try{
			//N�O ENVIA UPLOADS J� ENVIADOS
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * FROM "+NomeTabela+" " +
					"WHERE LINHA NOT IN (SELECT LINHA FROM status_controle_upload) AND " +
					"OV_CHAMADO_NUM = '"+ovChamado+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, ControleUpload.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(TAG,"listarByOvChamado->"+dump);
		return (lst.size() > 0 ? lst : null);
	}
	
	public List<ControleUpload> listarRealizadosByOvChamado(Context context, String ovChamado) throws Exception{
		Cursor retorno;
		String dump;
		List<ControleUpload> lst = new ArrayList<ControleUpload>();
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * FROM "+NomeTabela+" " +
					"WHERE OV_CHAMADO_NUM = '"+ovChamado+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, ControleUpload.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(TAG,"listarRealizadosByOvChamado->"+dump);
		return (lst.size() > 0 ? lst : null);
	}
	
	public int delete(Context context, String ovChamado) throws Exception{
		int retorno=0;
		try {
			gerDB.checkOpeningDataBase(context, "write");
			retorno = GerenciadorDB.db.delete(NomeTabela, "OV_CHAMADO_NUM='"+ovChamado+"'", null);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch (Exception ex) {
			LogErrorBLL.LogError(ex.getMessage(), "ERROR DELETE "+NomeTabela,context);
			throw ex;
		}
		return retorno;
	}
	
}
