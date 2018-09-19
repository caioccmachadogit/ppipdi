package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.List;

import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class OsBLL {
	private final String TAG = getClass().getSimpleName();
	private final String NomeTabela = "cad_os_vs3b";
	public static String createTable = "CREATE TABLE 'cad_os_vs3b' ("+
			"LINHA INTEGER NOT NULL,"+
			"CODIGO varchar(30) DEFAULT NULL,"+
			"VALIDADE varchar(1) DEFAULT NULL,"+
			"CLIENTE varchar(10) DEFAULT NULL,"+
			"CONTRATO varchar(20) DEFAULT NULL,"+
			"CONTRATO_FASE varchar(30) DEFAULT NULL,"+
			"REF_ROTA varchar(30) DEFAULT NULL,"+
			"PRE varchar(30) DEFAULT NULL,"+
			"ORDEM_COMPRA varchar(30) DEFAULT NULL,"+
			"LOTE_OV_NUM varchar(10) DEFAULT NULL,"+
			"LOTE_OV_LIN varchar(10) DEFAULT NULL,"+
			"LOTE_OV_ATV varchar(10) DEFAULT NULL,"+
			"OV_CHAMADO_NUM varchar(30) DEFAULT NULL,"+
			"OS_MOBILE_REC varchar(1) DEFAULT NULL,"+
			"CONTROLE_NUM varchar(30) DEFAULT NULL,"+
			"CONTROLE_CLI varchar(30) DEFAULT NULL,"+
			"OS_SITUACAO varchar(50) DEFAULT NULL,"+
			"OS_CONTROLE varchar(20) DEFAULT NULL,"+
			"SEQUENCIAL varchar(30) DEFAULT NULL,"+
			"TECNOLOGIA varchar(30) DEFAULT NULL,"+
			"COD_ENTIDADE varchar(12) DEFAULT NULL,"+
			"PONTA_DISTANTE varchar(120) DEFAULT NULL,"+
			"ENLACE_DIAGRAMA varchar(30) DEFAULT NULL,"+
			"BOX_DE varchar(2) DEFAULT NULL,"+
			"BOX_PARA varchar(30) DEFAULT NULL,"+
			"ALOCADO_EQUIP varchar(30) DEFAULT NULL,"+
			"ALOCADO_ANTENA_1 varchar(30) DEFAULT NULL,"+
			"ALOCADO_ANTENA_2 varchar(30) DEFAULT NULL,"+
			"ALOCADO_ANTENA_3 varchar(30) DEFAULT NULL,"+
			"ALOCADO_CABO varchar(30) DEFAULT NULL,"+
			"ESTRUT_ORDEM varchar(3) DEFAULT NULL,"+
			"LOTE_CONTROLE varchar(40) DEFAULT NULL,"+
			"CONFERENCIA_SITE varchar(255) DEFAULT NULL,"+
			"CONFERENCIA_EV01 varchar(255) DEFAULT NULL,"+
			"CONFERENCIA_EV02 varchar(255) DEFAULT NULL,"+
			"CONFERENCIA_EQUP varchar(255) DEFAULT NULL,"+
			"REGIAO varchar(100) DEFAULT NULL,"+
			"UF varchar(2) DEFAULT NULL,"+
			"PROJETO varchar(80) DEFAULT NULL,"+
			"OS_TIPO varchar(40) DEFAULT NULL,"+
			"ANEXOS varchar(4) DEFAULT NULL,"+
			"SOLIC_VISTORIA_NOME varchar(120) DEFAULT NULL,"+
			"SOLIC_VISTORIA_DATA varchar(30) DEFAULT NULL,"+
			"SOLIC_VISTORIA_MATR varchar(250) DEFAULT NULL,"+
			"PREV_OV_ATEND_INI varchar(30) DEFAULT NULL,"+
			"PREV_OV_ATEND_FIM varchar(30) DEFAULT NULL,"+
			"SOLIC_VISTORIA_EMPRESA varchar(80) DEFAULT NULL,"+
			"SOLIC_VISTORIA_EMPRESA_COD varchar(10) DEFAULT NULL,"+
			"SOLIC_VISTORIA_EMPRESA_KM varchar(5) DEFAULT NULL,"+
			"SOLIC_OV_SERV_NOME varchar(120) DEFAULT NULL,"+
			"SOLIC_OV_SERV_DATA varchar(30) DEFAULT NULL,"+
			"SOLIC_OV_SERV_INFO varchar(250) DEFAULT NULL,"+
			"CONC_OV_SERV_NOME varchar(120) DEFAULT NULL,"+
			"CONC_OV_SERV_DATA varchar(30) DEFAULT NULL,"+
			"CONC_OV_SERV_INFO varchar(250) DEFAULT NULL,"+
			"SITUACAO_ATENDIMENTO varchar(50) DEFAULT NULL,"+
			"DIVERGE varchar(1) DEFAULT NULL,"+
			"DIVERGE_OBS varchar(150) DEFAULT NULL,"+
			"OBSERVACAO_OS varchar(999) DEFAULT NULL,"+
			"ALERTA_CAPA varchar(100) DEFAULT NULL,"+
			"AREA_CHAO varchar(10) DEFAULT NULL,"+
			"OBSERVACAO_MASTER varchar(255) DEFAULT NULL,"+
			"CONTROLE_ROTINA varchar(50) DEFAULT NULL,"+
			"CONTROLE_MSG varchar(100) DEFAULT NULL,"+
			"ATUALIZACAO_NOME varchar(120) DEFAULT NULL,"+
			"ATUALIZACAO_DATA varchar(30) DEFAULT NULL,"+
			"ATUALIZACAO_DATA_INV varchar(30) DEFAULT NULL,"+
			"INSERCAO_DATA varchar(30) DEFAULT NULL,"+
			"INSERCAO_NOME varchar(120) DEFAULT NULL,"+
			"PRIMARY KEY (LINHA));";

	GerenciadorDB gerDB = new GerenciadorDB();
	public long Insert(Context context, List<Os> list) throws Exception{
		long resultadoInsercao = 0;
		try{
			for(int i=0; i < list.size();i++){
				ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(list.get(i));
				gerDB.checkOpeningDataBase(context, "write");		
				resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
				if(resultadoInsercao == -1){
					LogErrorBLL.LogError("", "ERROR DE PERSISTENCIA NO INSERT OS",context);
				}
			}
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		return resultadoInsercao;
	}
	
	public long Update(Context context, Os os) throws Exception{
		long resultadoEdicao = 0;
		String[] argumentos = {os.getLINHA()};
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(os);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoEdicao = GerenciadorDB.db.update(NomeTabela, contentValues, "LINHA=?", argumentos);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		return resultadoEdicao;
	}
	
	public Cursor listar(Context context, String user, String empresa) throws Exception{
		Cursor retorno;
		String dump;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select *, LINHA as _id " +
					"FROM "+NomeTabela+" WHERE SOLIC_OV_SERV_NOME = '"+user+"' AND SOLIC_VISTORIA_EMPRESA_COD = '"+empresa+"'"+
							" AND LINHA NOT IN (SELECT LINHA FROM status_os) " +
							"ORDER BY COD_ENTIDADE DESC", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(TAG,"listar->"+dump);
		return retorno;
	}
	
	public List<Os> listarFinalizada(Context context, String user, String empresa) throws Exception{
		Cursor retorno;
		String dump;
		List<Os> lstOs;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select *, LINHA as _id " +
					"FROM "+NomeTabela+" WHERE SOLIC_OV_SERV_NOME = '"+user+"' AND SOLIC_VISTORIA_EMPRESA_COD = '"+empresa+"'"+
							" AND LINHA IN (SELECT LINHA FROM status_os) " +
							"ORDER BY _id DESC", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lstOs = DatabaseConverter.convertCursorToObject(retorno, Os.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(TAG,"listarFinalizada->"+dump);
		return lstOs;
	}
	
	public List<Os> listarById(Context context, String linha) throws Exception{
		Cursor retorno;
		String dump;
		List<Os> lstOs;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select * " +
					"FROM "+NomeTabela+" WHERE LINHA = '"+linha+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lstOs = DatabaseConverter.convertCursorToObject(retorno, Os.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(TAG,"listarById->"+dump);
		return lstOs;
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
