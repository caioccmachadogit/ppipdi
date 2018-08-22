package base.htcom.com.br.ppipdiapp.bll;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;

import java.util.List;

import base.htcom.com.br.ppipdiapp.model.Site;
import base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object.DatabaseConverter;
import base.htcom.com.br.ppipdiapp.padrao.utils.GerenciadorDB;

public class SiteBLL {
	private final String NomeTabela = "cad_pontos";
	public static String createTable = "CREATE TABLE 'cad_pontos' ("+
			  "LINHA INTEGER NOT NULL,"+  
			  "CODIGO varchar(12) DEFAULT NULL,"+
			  "VALIDADE varchar(1) DEFAULT NULL,"+
			  "SITUACAO varchar(30) DEFAULT NULL,"+
			  "CLIENTE varchar(10) DEFAULT NULL,"+
			  "CONTRATO varchar(20) DEFAULT NULL,"+
			  "CONTRATO_FASE varchar(30) DEFAULT NULL,"+
			  "LOTE_CONTROLE varchar(40) DEFAULT NULL,"+
			  "ID_NATIVO_GTS varchar(20) DEFAULT NULL,"+
			  "OPERADORA_CEDENTE varchar(80) DEFAULT NULL,"+
			  "COD_PATRIM_CEDENTE varchar(50) DEFAULT NULL,"+
			  "TORRE_FABRICANTE varchar(60) DEFAULT NULL,"+
			  "DATA_ATIVACAO varchar(20) DEFAULT NULL,"+
			  "DATA_INSTALACAO varchar(30) DEFAULT NULL,"+
			  "DATA_RECEBIMENTO varchar(20) DEFAULT NULL,"+
			  "OS_RECEBIMENTO varchar(12) DEFAULT NULL,"+
			  "DATA_PREVENTIVA varchar(20) DEFAULT NULL,"+
			  "OS_PREVENTIVA varchar(12) DEFAULT NULL,"+
			  "DATA_MANUTENCAO varchar(20) DEFAULT NULL,"+
			  "OS_MANUTENCAO varchar(12) DEFAULT NULL,"+
			  "IMOVEL_TIPO varchar(2) DEFAULT NULL,"+
			  "IMOVEL_LICENCIAVEL varchar(2) DEFAULT NULL,"+
			  "IMOVEL_LICENCIADO varchar(2) DEFAULT NULL,"+
			  "TIPO varchar(30) DEFAULT NULL,"+
			  "NOME varchar(150) DEFAULT NULL,"+
			  "SIGLA varchar(20) DEFAULT NULL,"+
			  "TIPO_CLI varchar(50) DEFAULT NULL,"+
			  "COD_CLI varchar(12) DEFAULT NULL,"+
			  "ID_PRIMARIO varchar(30) DEFAULT NULL,"+
			  "ID_DETENTOR_01 varchar(30) DEFAULT NULL,"+
			  "ID_DETENTOR_02 varchar(30) DEFAULT NULL,"+
			  "ID_DETENTOR_03 varchar(30) DEFAULT NULL,"+
			  "ID_DETENTOR_04 varchar(30) DEFAULT NULL,"+
			  "ID_DETENTOR_05 varchar(30) DEFAULT NULL,"+
			  "ID_DETENTOR_06 varchar(30) DEFAULT NULL,"+
			  "ID_DETENTOR_07 varchar(30) DEFAULT NULL,"+
			  "ID_DETENTOR_08 varchar(30) DEFAULT NULL,"+
			  "DETENTOR varchar(50) DEFAULT NULL,"+
			  "DETENTOR_OBSERVACAO varchar(120) DEFAULT NULL,"+
			  "REGIAO varchar(100) DEFAULT NULL,"+
			  "LOGRADOURO_NOME varchar(250) DEFAULT NULL,"+
			  "LOGRADOURO_NUMERO varchar(30) DEFAULT NULL,"+
			  "LOGRADOURO_COMPLEM varchar(30) DEFAULT NULL,"+
			  "LOGRADOURO_BAIRRO varchar(150) DEFAULT NULL,"+
			  "LOGRADOURO_CIDADE varchar(150) DEFAULT NULL,"+
			  "LOGRADOURO_UF varchar(10) DEFAULT NULL,"+
			  "LOGRADOURO_PAIS varchar(150) DEFAULT NULL,"+
			  "LOGRADOURO_REFERENCIA varchar(500) DEFAULT NULL,"+
			  "LOGRADOURO_CEP_PRI varchar(10) DEFAULT NULL,"+
			  "LOGRADOURO_CEP_COMP varchar(10) DEFAULT NULL,"+
			  "LOGRADOURO_NOME_2 varchar(250) DEFAULT NULL,"+
			  "LOGRADOURO_NUMERO_2 varchar(30) DEFAULT NULL,"+
			  "LOGRADOURO_COMPLEM_2 varchar(30) DEFAULT NULL,"+
			  "LOGRADOURO_BAIRRO_2 varchar(150) DEFAULT NULL,"+
			  "LOGRADOURO_CIDADE_2 varchar(150) DEFAULT NULL,"+
			  "LOGRADOURO_UF_2 varchar(10) DEFAULT NULL,"+
			  "LOGRADOURO_PAIS_2 varchar(150) DEFAULT NULL,"+
			  "LOGRADOURO_REFERENCIA_2 varchar(500) DEFAULT NULL,"+
			  "LOGRADOURO_CEP_PRI_2 varchar(10) DEFAULT NULL,"+
			  "LOGRADOURO_CEP_COMP_2 varchar(10) DEFAULT NULL,"+
			  "TIPO_IMOVEL varchar(100) DEFAULT NULL,"+
			  "ZONA varchar(120) DEFAULT NULL,"+
			  "CLASSIFICACAO varchar(30) DEFAULT NULL,"+
			  "CN varchar(10) DEFAULT NULL,"+
			  "TEL_1 varchar(30) DEFAULT NULL,"+
			  "TEL_2 varchar(30) DEFAULT NULL,"+
			  "FAX varchar(30) DEFAULT NULL,"+
			  "LATITUDE varchar(30) DEFAULT NULL,"+
			  "LONGITUDE varchar(30) DEFAULT NULL,"+
			  "ALTITUDE varchar(10) DEFAULT NULL,"+
			  "EDIFICACAO_ALTURA varchar(5) DEFAULT NULL,"+
			  "ESTRUTURA_1_TIPO varchar(100) DEFAULT NULL,"+
			  "ESTRUTURA_1_ALTURA varchar(5) DEFAULT NULL,"+
			  "ESTRUTURA_2_TIPO varchar(100) DEFAULT NULL,"+
			  "ESTRUTURA_2_ALTURA varchar(5) DEFAULT NULL,"+
			  "PREF_ESTRUTURA varchar(5) DEFAULT NULL,"+
			  "ESTRUTURA_DATA varchar(20) DEFAULT NULL,"+
			  "ESTRUTURA_FABR varchar(120) DEFAULT NULL,"+
			  "SITE_TIPO varchar(50) DEFAULT NULL,"+
			  "SITE_ESTRUT varchar(50) DEFAULT NULL,"+
			  "SITE_CLASSIF varchar(30) DEFAULT NULL,"+
			  "EV_AREA_AMPLIACAO varchar(6) DEFAULT NULL,"+
			  "AREA_CHAO varchar(10) DEFAULT NULL,"+
			  "ABRIGO_TIPO varchar(50) DEFAULT NULL,"+
			  "ABRIGO_TIPO_1 varchar(1) DEFAULT NULL,"+
			  "ABRIGO_TIPO_2 varchar(1) DEFAULT NULL,"+
			  "ABRIGO_TIPO_3 varchar(1) DEFAULT NULL,"+
			  "ABRIGO_TIPO_4 varchar(1) DEFAULT NULL,"+
			  "ABRIGO_TIPO_5 varchar(1) DEFAULT NULL,"+
			  "ABRIGO_TIPO_6 varchar(1) DEFAULT NULL,"+
			  "TORRE_OBST varchar(5) DEFAULT NULL,"+
			  "RESPONSAVEL_1_NOME varchar(150) DEFAULT NULL,"+
			  "RESPONSAVEL_1_CARGO varchar(250) DEFAULT NULL,"+
			  "RESPONSAVEL_1_EMAIL varchar(120) DEFAULT NULL,"+
			  "RESPONSAVEL_1_CN_TEL varchar(10) DEFAULT NULL,"+
			  "RESPONSAVEL_1_TELEFONE varchar(16) DEFAULT NULL,"+
			  "RESPONSAVEL_1_RAMAL varchar(10) DEFAULT NULL,"+
			  "RESPONSAVEL_1_CN_CEL varchar(10) DEFAULT NULL,"+
			  "RESPONSAVEL_1_CELULAR varchar(16) DEFAULT NULL,"+
			  "RESPONSAVEL_2_NOME varchar(150) DEFAULT NULL,"+
			  "RESPONSAVEL_2_CARGO varchar(50) DEFAULT NULL,"+
			  "RESPONSAVEL_2_EMAIL varchar(120) DEFAULT NULL,"+
			  "RESPONSAVEL_2_CN_TEL varchar(10) DEFAULT NULL,"+
			  "RESPONSAVEL_2_TELEFONE varchar(16) DEFAULT NULL,"+
			  "RESPONSAVEL_2_RAMAL varchar(10) DEFAULT NULL,"+
			  "RESPONSAVEL_2_CN_CEL varchar(10) DEFAULT NULL,"+
			  "RESPONSAVEL_2_CELULAR varchar(16) DEFAULT NULL,"+
			  "KM_SEDE varchar(10) DEFAULT NULL,"+
			  "OBSERVACAO varchar(255) DEFAULT NULL,"+
			  "DIST_BUSCA decimal(10,0) DEFAULT NULL,"+
			  "OBSERVACAO_MASTER varchar(255) DEFAULT NULL,"+
			  "CONTROLE_ROTINA varchar(50) DEFAULT NULL,"+
			  "CONTROLE_MSG varchar(100) DEFAULT NULL,"+
			  "ATUALIZACAO_NOME varchar(120) DEFAULT NULL,"+
			  "ATUALIZACAO_DATA varchar(30) DEFAULT NULL,"+
			  "ATUALIZACAO_DATA_INV varchar(30) DEFAULT NULL,"+
			  "INSERCAO_DATA varchar(30) DEFAULT NULL,"+
			  "INSERCAO_NOME varchar(120) DEFAULT NULL,"+
			  "PRIMARY KEY (LINHA));";

	GerenciadorDB gerDB = new GerenciadorDB();
	public long Insert(Context context, List<Site> list) throws Exception{
		long resultadoInsercao = 0;
		try{
			for(int i=0; i < list.size();i++){
				ContentValues contentValues = DatabaseConverter.convertObjectToContentValue(list.get(i));
				gerDB.checkOpeningDataBase(context, "write");		
				resultadoInsercao = GerenciadorDB.db.insert(NomeTabela, null, contentValues);
			}
			GerenciadorDB.db = gerDB.closeDataBase(context);
		}
		catch(Exception ex){
			throw ex;
		}
		return resultadoInsercao;
	}
	
	public Site listarByCodigo(Context context, String cod) throws Exception{
		Cursor retorno;
		String dump;
		List<Site> lst;
		try{
			gerDB.checkOpeningDataBase(context, "read");		
			retorno = GerenciadorDB.db.rawQuery("select * " +
					"FROM "+NomeTabela+" WHERE CODIGO = '"+cod+"'", null);
			dump = DatabaseUtils.dumpCursorToString(retorno);
			GerenciadorDB.db = gerDB.closeDataBase(context);
			lst = DatabaseConverter.convertCursorToObject(retorno, Site.class);
		}
		catch(Exception ex){
			LogErrorBLL.LogError(ex.getMessage(), "ERROR LISTBYID TAB: "+NomeTabela,context);
			throw ex;
		}
		Log.v("Valores Cursor",  dump);
		return (lst.size() == 1 ? lst.get(0) : null);
	}

}
