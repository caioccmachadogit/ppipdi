package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import base.htcom.com.br.ppipdiapp.model.ArqPref;
import base.htcom.com.br.ppipdiapp.model.Combo;
import base.htcom.com.br.ppipdiapp.model.Os;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class ArqPrefBLL {
	private final String NomeTabela = "cad_os_arq_pref";
	public static String createTable = "CREATE TABLE 'cad_os_arq_pref' ("+
			  "LINHA INTEGER PRIMARY KEY AUTOINCREMENT,"+
			  "CODIGO varchar(50) DEFAULT NULL,"+
			  "VALIDADE varchar(1) DEFAULT NULL,"+
			  "CLIENTE varchar(10) DEFAULT NULL,"+
			  "CONTRATO varchar(20) DEFAULT NULL,"+
			  "CONTRATO_FASE varchar(30) DEFAULT NULL,"+
			  "CAMPO_REVISAO varchar(10) DEFAULT NULL,"+
			  "COD_ENTIDADE varchar(12) DEFAULT NULL,"+
			  "OV_CHAMADO_NUM varchar(30) DEFAULT NULL,"+
			  "ARQ_STATUS varchar(10) DEFAULT NULL,"+
			  "Arquivo_Carregado varchar(120) DEFAULT NULL,"+
			  "COMPARTILHAMENTO varchar(1) DEFAULT NULL,"+
			  "ARQ_NOME_REF varchar(100) DEFAULT NULL,"+
			  "OS_VERIF_20XXX varchar(255) DEFAULT NULL,"+
			  "OBSERVACAO_MASTER varchar(255) DEFAULT NULL,"+
			  "CONTROLE_ROTINA varchar(50) DEFAULT NULL,"+
			  "CONTROLE_MSG varchar(100) DEFAULT NULL,"+
			  "ATUALIZACAO_NOME varchar(120) DEFAULT NULL,"+
			  "ATUALIZACAO_DATA varchar(30) DEFAULT NULL,"+
			  "ATUALIZACAO_DATA_INV varchar(30) DEFAULT NULL,"+
			  "INSERCAO_DATA varchar(30) DEFAULT NULL,"+
			  "INSERCAO_NOME varchar(120) DEFAULT NULL);";
	
	public static String alterTable = "ALTER TABLE cad_os_arq_pref ADD CAMPO_REVISAO VARCHAR(10);";

	
	GerenciadorDB gerDB = new GerenciadorDB();
	public long Insert(Context context, String resp, String linhaCombo, String linhaOs) throws Exception{
		long resultadoInsercao = 0;
		try{
			OsBLL osBLL = new OsBLL();
			List<Os> lstOs = osBLL.listarById(context, linhaOs);
			ComboBLL comboBLL = new ComboBLL();
			Combo combo = comboBLL.listarById(context, linhaCombo);
			ArqPref arqPref = new ArqPref();
			arqPref.setCODIGO("OV_"+lstOs.get(0).getOV_CHAMADO_NUM()+"_"+lstOs.get(0).getCOD_ENTIDADE());
			arqPref.setVALIDADE("T");
			arqPref.setCLIENTE(lstOs.get(0).getCLIENTE());
			arqPref.setCONTRATO(lstOs.get(0).getCONTRATO());
			arqPref.setCONTRATO_FASE(lstOs.get(0).getCONTRATO_FASE());
			arqPref.setCOD_ENTIDADE(lstOs.get(0).getCOD_ENTIDADE());
			arqPref.setOV_CHAMADO_NUM(lstOs.get(0).getOV_CHAMADO_NUM());
			arqPref.setARQ_STATUS("N");
			arqPref.setArquivo_Carregado("20"+combo.getORDEM());
			arqPref.setOS_VERIF_20XXX(resp);
			arqPref.setATUALIZACAO_NOME("MOBILE");
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			arqPref.setATUALIZACAO_DATA(formatador.format(new Date()));
			
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(arqPref);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR INSERT_ARQ_PREF",context);
			throw ex;
		}
		return resultadoInsercao;
	}
	
	public long Insert(Context context, List<ArqPref> list) throws Exception{
		long resultadoInsercao = 0;
		try{
			if(list != null){
				for(int i=0; i < list.size();i++){
					ArqPref arqPref = list.get(i);
					arqPref.setCAMPO_REVISAO(arqPref.getLINHA());//armazena id do registro vindo da web
					arqPref.setLINHA(null); //zera Linha para autoincrementar
					ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(arqPref);
					gerDB.checkOpeningDataBase(context, "write");		
					resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
				}
				GerenciadorDB.db = gerDB.closeDataBase(context);
			}
			
		}
		catch(Exception ex){
			LogErrorBLL.LogError("", "ERROR DE PERSISTENCIA NO LIST "+NomeTabela ,context);
			resultadoInsercao = -1;
			throw ex;
		}
		return resultadoInsercao;
	}
	
	public ArqPref listarByArqCarregado(Context context, String arqCarregado, String ovChamado) throws Exception{
		Cursor retorno;
		String dump;
		List<ArqPref> lst = new ArrayList<ArqPref>();
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select * " +
					"FROM "+NomeTabela+" WHERE Arquivo_Carregado = '"+arqCarregado+"' AND OV_CHAMADO_NUM = '"+ovChamado+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, ArqPref.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}

	public long update(Context context, ArqPref arqPref) throws Exception{
		long resultadoEdicao = 0;
		String[] argumentos = {arqPref.getLINHA()};
		try{
			ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(arqPref);
			gerDB.checkOpeningDataBase(context, "write");		
			resultadoEdicao = GerenciadorDB.db.update(NomeTabela, contentValues, "LINHA=?", argumentos);
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		return resultadoEdicao;
	}

	public List<ArqPref> listarByOvChamado(Context context, String ovChamado) throws Exception{
		Cursor retorno;
		String dump;
		List<ArqPref> lst = new ArrayList<ArqPref>();
		try{
			//N�O ENVIA ARQ PREFS J� ENVIADOS
			gerDB.checkOpeningDataBase(context, "read");
			retorno = GerenciadorDB.db.rawQuery("SELECT * FROM "+NomeTabela+" " +
					"WHERE LINHA NOT IN (SELECT LINHA FROM status_arq_pref) AND " +
					"OV_CHAMADO_NUM = '"+ovChamado+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, ArqPref.class);
		}
		catch(Exception ex){
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return (lst.size() > 0 ? lst : null);
	}
	
	public List<ArqPref> listarRealizadosByOvChamado(Context context, String ovChamado) throws Exception{
		Cursor retorno;
		String dump;
		List<ArqPref> lst = new ArrayList<ArqPref>();
		try{
			//N�O ENVIA ARQ PREFS J� ENVIADOS
			gerDB.checkOpeningDataBase(context, "read");
			retorno = GerenciadorDB.db.rawQuery("SELECT * FROM "+NomeTabela+" " +
					"WHERE OV_CHAMADO_NUM = '"+ovChamado+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, ArqPref.class);
		}
		catch(Exception ex){
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
			LogErrorBLL.LogError(ex.getMessage(), "ERROR DELETE_ARQ_PREF",context);
			throw ex;
		}
		return retorno;
	}
}
