package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.model.StatusOs;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class StatusOsBLL {
	private final String NomeTabela = "status_os";
	public static String createTable = "CREATE TABLE 'status_os' ("+
			"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
			"LINHA INTEGER,"+
			"OV_CHAMADO_NUM varchar(30),"+
			"COD_ENTIDADE varchar(12),"+
			"ENVIADO varchar(1)," +
			"ENVIADO_FOTOS varchar(1)," +
			"DATA_FINALIZACAO varchar(30));";
	
	GerenciadorDB gerDB = new GerenciadorDB();
	
	public long Insert(Context context, Os os) throws Exception{
		long resultadoInsercao = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		try{
			StatusOs statusOs = new StatusOs();
			statusOs.setLINHA(os.getLINHA());
			statusOs.setOV_CHAMADO_NUM(os.getOV_CHAMADO_NUM());
			statusOs.setCOD_ENTIDADE(os.getCOD_ENTIDADE());
			statusOs.setENVIADO("0");
			statusOs.setENVIADO_FOTOS("0");
			statusOs.setDATA_FINALIZACAO(dateFormat.format(new Date()));
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(statusOs);
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

	public List<Os> listarFotos(Context context,String user, String empresa) throws Exception{
		Cursor retorno;
		String dump;
		List<Os> lstOs;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT os.* FROM status_os as sos inner JOIN cad_os_vs3b as os on sos.LINHA = os.LINHA " +
					"WHERE ENVIADO_FOTOS = '0' AND "+
					"SOLIC_OV_SERV_NOME = '"+user+"' AND SOLIC_VISTORIA_EMPRESA_COD = '"+empresa+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lstOs = DatabaseConverter.convertCursorToObject(retorno, Os.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores CursorCrud",  dump);
		return lstOs;
	}
	
	public List<Os> listarRegistros(Context context,String user, String empresa) throws Exception{
		Cursor retorno;
		String dump;
		List<Os> lstOs;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT os.* FROM status_os as sos inner JOIN cad_os_vs3b as os on sos.LINHA = os.LINHA " +
					"WHERE ENVIADO = '0' AND " +
					"SOLIC_OV_SERV_NOME = '"+user+"' AND SOLIC_VISTORIA_EMPRESA_COD = '"+empresa+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lstOs = DatabaseConverter.convertCursorToObject(retorno, Os.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores CursorCrud",  dump);
		return lstOs;
	}
	
	public StatusOs listarByLinha(Context context, String linhaOs) throws Exception{
		Cursor retorno;
		String dump;
		List<StatusOs> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * FROM status_os " +
					"WHERE LINHA = '"+linhaOs+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, StatusOs.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores "+NomeTabela,  dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}
	
	public long updateStatus(Context context, String linhaOs, String tipo) throws Exception{
		long resultadoEdicao = 0;
		StatusOs statusOs = listarByLinha(context, linhaOs);
		String[] argumentos = {statusOs.getLINHA()};
		if(tipo.equals("imagem")){
			statusOs.setENVIADO_FOTOS("1");
		}
		else{
			statusOs.setENVIADO("1");
		}
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(statusOs);
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
