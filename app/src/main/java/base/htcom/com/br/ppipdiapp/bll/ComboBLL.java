package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.List;

import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class ComboBLL {
	private final String NomeTabela = "combo";
	public static String createTable = "CREATE TABLE 'combo' ("+
			  "LINHA INTEGER NOT NULL,"+
			  "VALIDADE varchar(1) DEFAULT NULL,"+
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
			  "COD_REL varchar(50) DEFAULT NULL,"+
			  "TIPO_REL varchar(100) DEFAULT NULL,"+
			  "TITULO varchar(100) DEFAULT NULL,"+
			  "VALOR varchar(100) DEFAULT NULL,"+
			  "CLASSIFICACAO varchar(50) DEFAULT NULL,"+
			  "AEV varchar(5) DEFAULT NULL,"+
			  "CA varchar(5) DEFAULT NULL,"+
			  "OBSERVACAO varchar(255) DEFAULT NULL,"+
			  "OBSERVACAO_MASTER varchar(255) DEFAULT NULL,"+
			  "CONTROLE_ROTINA varchar(50) DEFAULT NULL,"+
			  "CONTROLE_MSG varchar(100) DEFAULT NULL,"+
			  "ATUALIZACAO_NOME varchar(120) DEFAULT NULL,"+
			  "ATUALIZACAO_DATA varchar(30) DEFAULT NULL,"+
			  "INSERCAO_DATA varchar(30) DEFAULT NULL,"+
			  "INSERCAO_NOME varchar(120) DEFAULT NULL,"+
			  "PRIMARY KEY (LINHA));";

	GerenciadorDB gerDB = new GerenciadorDB();
	public long Insert(Context context, List<Combo> list) throws Exception{
		long resultadoInsercao = 0;
		try{
			for(int i=0; i < list.size();i++){
				ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(list.get(i));
				if(listarById(context, list.get(i).getLINHA()) == null){
					//QUANDO N�O EXISTIR A LINHA, INSERE UM NOVO
					gerDB.checkOpeningDataBase(context, "write");
					resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
					if(resultadoInsercao == -1){
						LogErrorBLL.LogError("", "ERROR DE INSERT NO COMBO",context);
					}
				}
				else{
					//JA EXISTE A LINHA, EDITA O EXISTE
					gerDB.checkOpeningDataBase(context, "write");
					resultadoInsercao = update(context, list.get(i));
					if(resultadoInsercao != 1){
						LogErrorBLL.LogError("", "ERROR DE UPDATE NO COMBO",context);
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
	
	public long update(Context context, Combo combo) throws Exception{
		long resultadoEdicao = 0;
		String[] argumentos = {combo.getLINHA()};
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(combo);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoEdicao = GerenciadorDB.db.update(NomeTabela, contentValues, "LINHA=?", argumentos);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		return resultadoEdicao;
	}

	public List<Combo> listarByFiltro(Context context, String filtro, String tipoRel) throws Exception{
		Cursor retorno;
		String dump;
		List<Combo> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");
			if(tipoRel == null){
				retorno = GerenciadorDB.db.rawQuery("SELECT *, LINHA as _id " +
						"FROM "+NomeTabela+" WHERE FILTRO = '"+filtro+"' ORDER BY ORDEM ASC", null);
			}
			else {
				retorno = GerenciadorDB.db.rawQuery("SELECT *, LINHA as _id " +
						"FROM "+NomeTabela+" WHERE FILTRO = '"+filtro+"' AND TIPO_REL = '"+tipoRel+"' ORDER BY ORDEM ASC", null);
			}
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Combo.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return lst;
	}
	
	public List<Combo> listarByFiltro_tipoRel(Context context, String filtro) throws Exception{
		Cursor retorno;
		String dump;
		List<Combo> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("SELECT * FROM "+NomeTabela+" " +
					"WHERE FILTRO = '"+filtro+"' GROUP BY tipo_rel", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Combo.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return lst;
	}
	
	public Combo listarById(Context context, String linha) throws Exception{
		Cursor retorno;
		String dump;
		List<Combo> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select *, LINHA as _id " +
					"FROM "+NomeTabela+" WHERE LINHA = '"+linha+"' ORDER BY ORDEM ASC", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Combo.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return (lst.size() == 1 ? lst.get(0) : null);
		
	}
	
	public Combo listarBySigla(Context context, String sigla) throws Exception{
		Cursor retorno;
		String dump;
		List<Combo> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select *, LINHA as _id " +
					"FROM "+NomeTabela+" WHERE SIGLA = '"+sigla+"' ORDER BY ORDEM ASC", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Combo.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return (lst.size() == 1 ? lst.get(0) : null);
		
	}
	
	public Combo listarByTipo(Context context, String tipo) throws Exception{
		Cursor retorno;
		String dump;
		List<Combo> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select *, LINHA as _id " +
					"FROM "+NomeTabela+" WHERE TIPO = '"+tipo+"' ORDER BY ORDEM ASC", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Combo.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return (lst.size() == 1 ? lst.get(0) : null);
		
	}
	
	public Combo listarByOrdem(Context context, String ordem, String filtro, String tipoRel) throws Exception{
		Cursor retorno;
		String dump;
		List<Combo> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select * FROM "+NomeTabela+" " +
					"WHERE FILTRO = '"+filtro+"' AND ORDEM = '"+ordem+"' AND TIPO_REL = '"+tipoRel+"' ORDER BY ORDEM ASC", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Combo.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}
}
