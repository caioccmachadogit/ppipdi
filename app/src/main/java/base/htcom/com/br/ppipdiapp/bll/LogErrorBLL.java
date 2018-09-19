package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class LogErrorBLL {
	private static final String TAG = "LogErrorBLL";
	private final String NomeTabela = "log_error";
	public static String createTable = "CREATE TABLE 'log_error' ("+
			"TAG varchar(255) DEFAULT NULL,"+
			"ERROR text DEFAULT NULL,"+
			"DATA varchar(40) DEFAULT NULL)";
	
	public long Insert(Context context, ContentValues contentValues){
		long resultadoInsercao = 0;
		try{
			GerenciadorDB gerDB = new GerenciadorDB();
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
		}
		return resultadoInsercao;
	}
	
	public static void LogError(String error, String tag, Context context){
		SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );  
		ContentValues valores = new ContentValues();
		valores.put("TAG", tag);
		valores.put("ERROR", error);
		valores.put("DATA", format.format(new Date()));
		new LogErrorBLL().Insert(context, valores);
		Log.e(TAG,tag+"->"+error);
	}
}
