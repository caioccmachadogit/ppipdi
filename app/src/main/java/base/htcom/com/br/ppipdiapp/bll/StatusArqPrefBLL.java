package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import base.htcom.com.br.ppipdiapp.model.StatusArqPref;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class StatusArqPrefBLL {
	private final String NomeTabela = "status_arq_pref";
	public static String createTable = "CREATE TABLE 'status_arq_pref' ("+
			"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"LINHA INTEGER);";
	
GerenciadorDB gerDB = new GerenciadorDB();
	
	public long Insert(Context context, String Linha) throws Exception{
		long resultadoInsercao = 0;
		try{
			StatusArqPref statusArqPref = new StatusArqPref();
			statusArqPref.setLINHA(Linha);
			
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(statusArqPref);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR INSERT "+NomeTabela,context);
			throw ex;
		}
		return resultadoInsercao;
	}

	public StatusArqPref listarByLinha(Context context, String linhaArqPref) throws Exception{
		Cursor retorno;
		String dump;
		List<StatusArqPref> lst = new ArrayList<StatusArqPref>();
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select * " +
					"FROM "+NomeTabela+" WHERE LINHA = '"+linhaArqPref+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, StatusArqPref.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d("Valores:"+NomeTabela,  dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}
	

}
