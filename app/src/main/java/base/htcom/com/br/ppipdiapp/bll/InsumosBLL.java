package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.List;

import base.htcom.com.br.ppipdiapp.model.Insumos;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class InsumosBLL {
	private final String TAG = getClass().getSimpleName();
	private final String NomeTabela = "insumos";
	public static String createTable = "CREATE TABLE 'insumos' ("+
			  "LINHA INTEGER NOT NULL,"+
			  "VALIDADE varchar(5) DEFAULT '',"+
			  "MANDATORIO varchar(1) DEFAULT NULL,"+
			  "CLIENTE varchar(10) DEFAULT NULL,"+
			  "CONTRATO varchar(50) DEFAULT NULL,"+
			  "CONTRATO_FASE varchar(30) DEFAULT NULL,"+
			  "REFERENCIA varchar(50) DEFAULT NULL,"+
			  "ORDEM varchar(5) DEFAULT NULL,"+
			  "FILTRO varchar(50) DEFAULT NULL,"+
			  "SIGLA varchar(50) DEFAULT NULL,"+
			  "COD varchar(50) DEFAULT NULL,"+
			  "TIPO varchar(100) DEFAULT NULL,"+
			  "INFO_01 varchar(100) DEFAULT '',"+
			  "INFO_02 varchar(100) DEFAULT '',"+
			  "INFO_03 varchar(100) DEFAULT '',"+
			  "INFO_04 varchar(100) DEFAULT '',"+
			  "INFO_05 varchar(100) DEFAULT '',"+
			  "INFO_06 varchar(100) DEFAULT '',"+
			  "INFO_07 varchar(100) DEFAULT '',"+
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
			  "INFO_22 varchar(100) DEFAULT '',"+
			  "INFO_23 varchar(100) DEFAULT NULL,"+
			  "INFO_24 varchar(100) DEFAULT NULL,"+
			  "LISTA varchar(1) DEFAULT NULL,"+
			  "OBSERVACAO varchar(255) DEFAULT NULL,"+
			  "OBSERVACAO_MASTER varchar(255) DEFAULT NULL,"+
			  "CONTROLE_ROTINA varchar(50) DEFAULT NULL,"+
			  "CONTROLE_MSG varchar(100) DEFAULT NULL,"+
			  "ATUALIZACAO_NOME varchar(120) DEFAULT NULL,"+
			  "ATUALIZACAO_DATA varchar(30) DEFAULT NULL,"+
			  "ATUALIZACAO_DATA_INV varchar(30) DEFAULT NULL,"+
			  "INSERCAO_DATA varchar(30) DEFAULT NULL,"+
			  "INSERCAO_NOME varchar(120) DEFAULT NULL,"+
			  "PRIMARY KEY (LINHA))";

	
	GerenciadorDB gerDB = new GerenciadorDB();
	public long Insert(Context context, List<Insumos> list) throws Exception{
		long resultadoInsercao = 0;
		try{
			for(int i=0; i < list.size();i++){
				ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(list.get(i));
				if(listarById(context, list.get(i).getLINHA()) == null){
					//QUANDO N�O EXISTIR A LINHA, INSERE UM NOVO
					gerDB.checkOpeningDataBase(context, "write");
					resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
					if(resultadoInsercao == -1){
						LogErrorBLL.LogError("", "ERROR DE INSERT NA ANTENA",context);
					}
				}
				else{
					//JA EXISTE A LINHA, EDITA O EXISTE
					gerDB.checkOpeningDataBase(context, "write");
					resultadoInsercao = update(context, list.get(i));
					if(resultadoInsercao != 1){
						LogErrorBLL.LogError("", "ERROR DE UPDATE NA ANTENA",context);
					}
				}
			}
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		return resultadoInsercao;
	}
	
	public long update(Context context, Insumos insumos) throws Exception{
		long resultadoEdicao = 0;
		String[] argumentos = {insumos.getLINHA()};
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(insumos);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoEdicao = GerenciadorDB.db.update(NomeTabela, contentValues, "LINHA=?", argumentos);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		return resultadoEdicao;
	}
	
	public Insumos listarById(Context context, String linha) throws Exception{
		Cursor retorno;
		String dump;
		List<Insumos> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select *, LINHA as _id " +
					"FROM "+NomeTabela+" WHERE LINHA = '"+linha+"' ORDER BY ORDEM ASC", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Insumos.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(TAG,"listarById->"+dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}

	public List<Insumos> listarAntena(Context context) throws Exception{
		Cursor retorno;
		String dump;
		List<Insumos> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select *, LINHA as _id " +
					"FROM "+NomeTabela+"" +
							" WHERE FILTRO = 'antenas' ORDER BY LINHA DESC", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Insumos.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(TAG,"listarAntena->"+dump);
		return lst;
	}

	public Insumos listarByInfo02(Context context, String info) throws Exception{
		Cursor retorno;
		String dump;
		List<Insumos> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * " +
					"FROM "+NomeTabela+" WHERE INFO_02 = '"+info+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Insumos.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.d(TAG,"listarByInfo02->"+dump);
		return (lst.size() == 1 ? lst.get(0) : null);
		
	}
}
