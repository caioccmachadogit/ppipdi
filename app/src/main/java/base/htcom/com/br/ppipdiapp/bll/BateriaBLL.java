package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import base.htcom.com.br.ppipdiapp.model.Bateria;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class BateriaBLL {
	private final String NomeTabela = "baterias";
	public static String createTable = "CREATE TABLE 'baterias' ("+
			  "Id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			  "OV_CHAMADO_NUM varchar(30) DEFAULT NULL,"+
			  "LOCAL varchar(150) DEFAULT NULL,"+
			  "MARCA varchar(150) DEFAULT NULL,"+
			  "MODELO varchar(150) DEFAULT NULL,"+
			  "CAPACIDADE varchar(150) DEFAULT NULL,"+
			  "ORDEM varchar(4) DEFAULT NULL);";
	
		
	GerenciadorDB gerDB = new GerenciadorDB();
	public long Insert(Context context, Bateria bateria) throws Exception{
		long resultadoInsercao = 0;
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(bateria);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR INSERT_BATERIA",context);
			throw ex;
		}
		return resultadoInsercao;
	}
	
	public Bateria listarById(Context context, int id) throws Exception{
		Cursor retorno;
		String dump;
		List<Bateria> lst = new ArrayList<Bateria>();
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * " +
					"FROM "+NomeTabela+" WHERE Id = '"+id+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Bateria.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}

	public long Update(Context context, Bateria bateria) throws Exception{
		long resultadoEdicao = 0;
		String[] argumentos = {bateria.getId()};
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(bateria);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoEdicao = GerenciadorDB.db.update(NomeTabela, contentValues, "Id=?", argumentos);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		return resultadoEdicao;
	}

	public List<Bateria> listarByOvChamado(Context context, String ovChamado) throws Exception{
		Cursor retorno;
		String dump;
		List<Bateria> lst = new ArrayList<Bateria>();
		try{
			gerDB.checkOpeningDataBase(context, "read");
			retorno = GerenciadorDB.db.rawQuery("SELECT * FROM "+NomeTabela+" " +
					"WHERE OV_CHAMADO_NUM = '"+ovChamado+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Bateria.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return (lst.size() > 0 ? lst : null);
	}
	
	public List<Bateria> listarNaoEnviados(Context context, String ovChamado) throws Exception{
		Cursor retorno;
		String dump;
		List<Bateria> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * FROM "+NomeTabela+" " +
					"WHERE Id NOT IN (SELECT LINHA FROM status_bateria) AND " +
					"OV_CHAMADO_NUM = '"+ovChamado+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Bateria.class);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "listarNaoEnviados TAB: "+NomeTabela,context);
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
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
			LogErrorBLL.LogError(ex.getMessage(), "ERROR DELETE_Bateria",context);
			throw ex;
		}
		return retorno;
	}
}
