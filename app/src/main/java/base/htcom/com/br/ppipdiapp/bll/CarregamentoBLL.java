package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.List;

import base.htcom.com.br.ppipdiapp.model.Carregamento;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class CarregamentoBLL {
	private final String TAG = getClass().getSimpleName();
	private final String NomeTabela = "carregamento";
	public static String createTable = "CREATE TABLE 'carregamento' ("+
			  "LINHA INTEGER PRIMARY KEY AUTOINCREMENT,"+
			  "CODIGO varchar(12) DEFAULT '',"+
			  "VALIDADE varchar(5) DEFAULT '',"+
			  "COD_ENTIDADE varchar(12) DEFAULT NULL,"+
			  "LOTE_CONTROLE varchar(40) DEFAULT NULL,"+
			  "UF varchar(2) DEFAULT NULL,"+
			  "CN varchar(3) DEFAULT NULL,"+
			  "OS_INSERCAO varchar(12) DEFAULT NULL,"+
			  "OS_RETIRADA varchar(12) DEFAULT NULL,"+
			  "FLAG_CONDICAO varchar(1) DEFAULT NULL,"+
			  "ESTRUT_ORDEM varchar(3) DEFAULT '',"+
			  "CAMPO_TIPO varchar(5) DEFAULT '',"+
			  "ORDENADOR varchar(30) DEFAULT '',"+
			  "INFO_01 varchar(20) DEFAULT '',"+
			  "INFO_02 varchar(10) DEFAULT '',"+
			  "INFO_03 varchar(50) DEFAULT '',"+
			  "INFO_04 varchar(80) DEFAULT '',"+
			  "INFO_05 varchar(40) DEFAULT '',"+
			  "INFO_06 varchar(10) DEFAULT '',"+
			  "INFO_07 varchar(10) DEFAULT '',"+
			  "INFO_08 varchar(100) DEFAULT '',"+
			  "INFO_09 varchar(100) DEFAULT '',"+
			  "INFO_10 varchar(100) DEFAULT '',"+
			  "INFO_11 varchar(100) DEFAULT '',"+
			  "INFO_12 varchar(100) DEFAULT '',"+
			  "INFO_13 varchar(100) DEFAULT '',"+
			  "INFO_14 varchar(100) DEFAULT '',"+
			  "INFO_15 varchar(100) DEFAULT '',"+
			  "INFO_16 varchar(100) DEFAULT '',"+
			  "INFO_17 varchar(100) DEFAULT '',"+
			  "INFO_18 varchar(100) DEFAULT '',"+
			  "INFO_19 varchar(100) DEFAULT '',"+
			  "INFO_20 varchar(100) DEFAULT '',"+
			  "INFO_21 varchar(100) DEFAULT '',"+
			  "INFO_22 varchar(255) DEFAULT '',"+
			  "INFO_23 varchar(550) DEFAULT NULL,"+
			  "INFO_24 varchar(50) DEFAULT NULL,"+
			  "INFO_25 varchar(50) DEFAULT NULL,"+
			  "INFO_26 varchar(10) DEFAULT NULL,"+
			  "INFO_27 varchar(100) DEFAULT NULL,"+
			  "INFO_28 varchar(10) DEFAULT NULL,"+
			  "INFO_29 varchar(50) DEFAULT '',"+
			  "INFO_30 varchar(50) DEFAULT '',"+
			  "INFO_31 varchar(50) DEFAULT '',"+
			  "INFO_32 varchar(50) DEFAULT '',"+
			  "INFO_33 varchar(50) DEFAULT NULL,"+
			  "INFO_34 varchar(50) DEFAULT NULL,"+
			  "INFO_35 varchar(50) DEFAULT NULL,"+
			  "INFO_36 varchar(50) DEFAULT NULL,"+
			  "INFO_37 varchar(50) DEFAULT NULL,"+
			  "INFO_38 varchar(50) DEFAULT NULL,"+
			  "INFO_39 varchar(50) DEFAULT '',"+
			  "INFO_40 varchar(50) DEFAULT '',"+
			  "INFO_41 varchar(50) DEFAULT '',"+
			  "INFO_42 varchar(50) DEFAULT '',"+
			  "INFO_43 varchar(50) DEFAULT NULL,"+
			  "INFO_44 varchar(255) DEFAULT NULL,"+
			  "INFO_45 varchar(50) DEFAULT NULL,"+
			  "INFO_46 varchar(50) DEFAULT '',"+
			  "INFO_47 varchar(50) DEFAULT '',"+
			  "INFO_48 varchar(50) DEFAULT NULL,"+
			  "INFO_49 varchar(255) DEFAULT NULL,"+
			  "INFO_50 varchar(255) DEFAULT NULL,"+
			  "INFO_51 varchar(50) DEFAULT '',"+
			  "INFO_52 varchar(50) DEFAULT '',"+
			  "INFO_53 varchar(50) DEFAULT NULL,"+
			  "INFO_54 varchar(50) DEFAULT NULL,"+
			  "INFO_55 varchar(50) DEFAULT NULL,"+
			  "INFO_56 varchar(255) DEFAULT '',"+
			  "INFO_57 varchar(50) DEFAULT '',"+
			  "INFO_58 varchar(50) DEFAULT NULL,"+
			  "INFO_59 varchar(255) DEFAULT NULL,"+
			  "INFO_60 varchar(255) DEFAULT NULL,"+
			  "CONTROLE_MSG varchar(100) DEFAULT NULL,"+
			  "ATUALIZACAO_NOME varchar(120) DEFAULT NULL,"+
			  "ATUALIZACAO_DATA varchar(30) DEFAULT NULL,"+
			  "ATUALIZACAO_DATA_INV varchar(30) DEFAULT NULL,"+
			  "INSERCAO_DATA varchar(30) DEFAULT NULL,"+
			  "INSERCAO_NOME varchar(120) DEFAULT NULL)";

	GerenciadorDB gerDB = new GerenciadorDB();
	public long Insert(Context context, Carregamento carregamento) throws Exception{
		long resultadoInsercao = 0;
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(carregamento);
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
	
	public long Insert(Context context, List<Carregamento> list) throws Exception{
		long resultadoInsercao = 0;
		try{
			if(list != null){
				for(int i=0; i < list.size();i++){
					Carregamento carregamento = list.get(i);
					carregamento.setINFO_58(carregamento.getLINHA()); //armazena id do registro vindo da web
					carregamento.setLINHA(null); //zera Linha para autoincrementar
					ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(carregamento);
					gerDB.checkOpeningDataBase(context, "write");		
					resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
				}
				GerenciadorDB.db = gerDB.closeDataBase(context);
			}
		}
		catch(Exception ex){
			LogErrorBLL.LogError("", "ERROR DE PERSISTENCIA NO CARREGAMENTO LIST",context);
			resultadoInsercao = -1;
			throw ex;
		}
		return resultadoInsercao;
	}

	public List<Carregamento> listar(Context context) throws Exception{
		Cursor retorno;
		String dump;
		List<Carregamento> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select *, LINHA as _id " +
					"FROM "+NomeTabela+" ORDER BY LINHA DESC", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Carregamento.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(TAG,"listar->"+dump);
		return lst;
	}
	
	public Carregamento listarById(Context context, String linha) throws Exception{
		Cursor retorno;
		String dump;
		List<Carregamento> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select *, LINHA as _id " +
					"FROM "+NomeTabela+" WHERE LINHA = '"+linha+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Carregamento.class);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR LISTBYID TAB: "+NomeTabela,context);
			throw ex;
		}
		Log.d(TAG,"listarById->"+dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}
	
	public Carregamento listarEv(Context context, String codigo, String campoTipo) throws Exception{
		Cursor retorno;
		String dump;
		List<Carregamento> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select * FROM "+NomeTabela+"" +
					" WHERE CODIGO = '"+codigo+"' AND CAMPO_TIPO = '"+campoTipo+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Carregamento.class);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR LISTBYID TAB: "+NomeTabela,context);
			throw ex;
		}
		Log.d(TAG,"listarEv->"+dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}

	public List<Carregamento> listarByCodigo(Context context, String codigo) throws Exception{
		Cursor retorno;
		String dump;
		List<Carregamento> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * " +
					"FROM "+NomeTabela+" WHERE CAMPO_TIPO != '1A000' AND CODIGO = '"+codigo+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Carregamento.class);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR LISTBYCODIGO TAB: "+NomeTabela,context);
			throw ex;
		}
		Log.d(TAG,"listarByCodigo->"+dump);
		return (lst.size() > 0 ? lst : null);
	}
	
	public List<Carregamento> listarByCodigoAll(Context context, String codigo) throws Exception{
		Cursor retorno;
		String dump;
		List<Carregamento> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * " +
					"FROM "+NomeTabela+" WHERE CODIGO = '"+codigo+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Carregamento.class);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR LISTBYCODIGO TAB: "+NomeTabela,context);
			throw ex;
		}
		Log.d(TAG,"listarByCodigoAll->"+dump);
		return (lst.size() > 0 ? lst : null);
	}
	
	public List<Carregamento> listarByCodigoAllNotEv(Context context, String codigo, String NCampoTipo) throws Exception{
		Cursor retorno;
		String dump;
		List<Carregamento> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * " +
					"FROM "+NomeTabela+" WHERE CODIGO = '"+codigo+"' AND CAMPO_TIPO != '"+NCampoTipo+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Carregamento.class);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR LISTBYCODIGO TAB: "+NomeTabela,context);
			throw ex;
		}
		Log.d(TAG,"listarByCodigoAllNotEv->"+dump);
		return (lst.size() > 0 ? lst : null);
	}
	
	public List<Carregamento> listarNaoEnviados(Context context, String codigo) throws Exception{
		Cursor retorno;
		String dump;
		List<Carregamento> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * FROM "+NomeTabela+" " +
					"WHERE LINHA NOT IN (SELECT LINHA FROM status_carregamento) AND " +
					"CODIGO = '"+codigo+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Carregamento.class);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "listarNaoEnviados TAB: "+NomeTabela,context);
			throw ex;
		}
		Log.d(TAG,"listarNaoEnviados->"+dump);
		return (lst.size() > 0 ? lst : null);
	}
	
	public long update(Context context, Carregamento carregamento) throws Exception{
		long resultadoEdicao = 0;
		String[] argumentos = {carregamento.getLINHA()};
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(carregamento);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoEdicao = GerenciadorDB.db.update(NomeTabela, contentValues, "LINHA=?", argumentos);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		return resultadoEdicao;
	}
	
	public int delete(Context context, String ovChamado) throws Exception{
		int retorno=0;
		try {
			gerDB.checkOpeningDataBase(context, "write");
			retorno = GerenciadorDB.db.delete(NomeTabela, "CODIGO='"+ovChamado+"'", null);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch (Exception ex) {
			LogErrorBLL.LogError(ex.getMessage(), "ERROR DELETE "+NomeTabela,context);
			throw ex;
		}
		return retorno;
	}
}
