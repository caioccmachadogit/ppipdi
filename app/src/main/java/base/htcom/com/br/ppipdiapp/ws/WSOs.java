package base.htcom.com.br.ppipdiapp.ws;

import org.ksoap2.serialization.SoapObject;

import base.htcom.com.br.ppipdiapp.padrao.utils.VarConfig;
import base.htcom.com.br.ppipdiapp.padrao.ws.RequestSoap;

public class WSOs {
	final String NAMESPACE = "http://tempuri.org/";
	
	public final String URL = VarConfig.URLWS;
	
	public String receberOS(String usuario, String empresa, String situacao) throws Exception {
		String retorno;
		try {
			RequestSoap rSoap = new RequestSoap();
			String METHOD_NAME = "ReceberOS";
			String SOAP_ACTION = NAMESPACE+METHOD_NAME;
			SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
			soapObject.addProperty("usuario", usuario);
			soapObject.addProperty("empresa", empresa);
			soapObject.addProperty("situacao", situacao);
			rSoap.URL = URL;
			retorno = rSoap.conectar(soapObject, SOAP_ACTION).toString();
		}
		catch (Exception e) {
			throw e;
		}
		return retorno; 		
	}
	
	
	public String ConfirmarRecOS(String ovChamado) throws Exception {
		String retorno;
		try {
			RequestSoap rSoap = new RequestSoap();
			String METHOD_NAME = "ConfirmarRecOS";
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
	
	public String ConfirmarEnvOS(String ovChamado,String user,String data, String notas, String anexos) throws Exception {
		String retorno;
		try {
			RequestSoap rSoap = new RequestSoap();
			String METHOD_NAME = "ConfirmarEnvOS";
			String SOAP_ACTION = NAMESPACE+METHOD_NAME;
			SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
			soapObject.addProperty("ov_chamado", ovChamado);
			soapObject.addProperty("user", user);
			soapObject.addProperty("data", data);
			soapObject.addProperty("notas", notas);
			soapObject.addProperty("anexos", anexos);
			rSoap.URL = URL;
			retorno = rSoap.conectar(soapObject, SOAP_ACTION).toString();
		}
		catch (Exception e) {
			throw e;
		}
		return retorno; 		
	}

}
