package base.htcom.com.br.ppipdiapp.ws;

import org.ksoap2.serialization.SoapObject;

import base.htcom.com.br.ppipdiapp.padrao.utils.VarConfig;
import base.htcom.com.br.ppipdiapp.padrao.ws.RequestSoap;

public class WSCarregamento {
	public final String NAMESPACE = "http://tempuri.org/";
	
	public final String URL = VarConfig.URLWS;
	
	public String ReceberCarregamentoPlanta(String codEntidade) throws Exception {
		String retorno;
		try {
			RequestSoap rSoap = new RequestSoap();
			String METHOD_NAME = "ReceberCarregamentoPlanta";
			String SOAP_ACTION = NAMESPACE+METHOD_NAME;
			SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
			soapObject.addProperty("cod_entidade", codEntidade);
			rSoap.URL = URL;
			retorno = rSoap.conectar(soapObject, SOAP_ACTION).toString();
		}
		catch (Exception e) {
			throw e;
		}
		return retorno; 		
	}
	
	public String ReceberRevCarregamento(String ovChamado) throws Exception {
		String retorno;
		try {
			RequestSoap rSoap = new RequestSoap();
			String METHOD_NAME = "ReceberRevCarregamento";
			String SOAP_ACTION = NAMESPACE+METHOD_NAME;
			SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
			soapObject.addProperty("ov_chamado", ovChamado);
			rSoap.URL = URL;
			retorno = rSoap.conectar(soapObject, SOAP_ACTION).toString();
		}
		catch (Exception e) {
			throw e;
		}
		return retorno; 		
	}
	
	public String EnviarCarregamento(String json) throws Exception {
		String retorno;
		try {
			RequestSoap rSoap = new RequestSoap();
			String METHOD_NAME = "EnviarCarregamento";
			String SOAP_ACTION = NAMESPACE+METHOD_NAME;
			SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
			soapObject.addProperty("json_carregamento", json);
			rSoap.URL = URL;
			retorno = rSoap.conectar(soapObject, SOAP_ACTION).toString();
		}
		catch (Exception e) {
			throw e;
		}
		return retorno; 		
	}

}
