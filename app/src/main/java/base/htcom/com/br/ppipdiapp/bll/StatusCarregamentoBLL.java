package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import base.htcom.com.br.ppipdiapp.model.StatusCarregamento;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class StatusCarregamentoBLL {
	private final String NomeTabela = "status_carregamento";
	public static String createTable = "CREATE TABLE 'status_carregamento' ("+
			"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"LINHA INTEGER);";
	
GerenciadorDB gerDB = new GerenciadorDB();
	
	public long Insert(Context context, String Linha) throws Exception{
		long resultadoInsercao = 0;
		try{
			StatusCarregamento statusCarregamento = new StatusCarregamento();
			statusCarregamento.setLINHA(Linha);
			
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(statusCarregamento);
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

	public StatusCarregamento listarByLinha(Context context, String linhaCar) throws Exception{
		Cursor retorno;
		String dump;
		List<StatusCarregamento> lst = new ArrayList<StatusCarregamento>();
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select * " +
					"FROM "+NomeTabela+" WHERE LINHA = '"+linhaCar+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, StatusCarregamento.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(NomeTabela,  dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}
	

}
