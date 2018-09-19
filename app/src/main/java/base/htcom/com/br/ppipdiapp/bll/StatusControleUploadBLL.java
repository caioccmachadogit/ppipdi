package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import base.htcom.com.br.ppipdiapp.model.StatusControleUpload;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class StatusControleUploadBLL {
	private final String TAG = getClass().getSimpleName();
	private final String NomeTabela = "status_controle_upload";
	public static String createTable = "CREATE TABLE 'status_controle_upload' ("+
			"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"LINHA INTEGER," +
			"DATA_ENVIO VARCHAR(40));";
	
	GerenciadorDB gerDB = new GerenciadorDB();
	
	public long Insert(Context context, StatusControleUpload statusControleUpload) throws Exception{
		long resultadoInsercao = 0;
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(statusControleUpload);
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

	public StatusControleUpload listarByLinha(Context context, String linhaControleUp) throws Exception{
		Cursor retorno;
		String dump;
		List<StatusControleUpload> lst = new ArrayList<StatusControleUpload>();
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select * " +
					"FROM "+NomeTabela+" WHERE LINHA = '"+linhaControleUp+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, StatusControleUpload.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(TAG,"listarByLinha->"+dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}
}
