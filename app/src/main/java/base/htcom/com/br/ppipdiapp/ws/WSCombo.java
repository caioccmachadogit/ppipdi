package base.htcom.com.br.ppipdiapp.ws;

import org.ksoap2.serialization.SoapObject;

import base.htcom.com.br.ppipdiapp.padrao.utils.VarConfig;
import base.htcom.com.br.ppipdiapp.padrao.ws.RequestSoap;

public class WSCombo {
	public final String NAMESPACE = "http://tempuri.org/";
	
	public final String URL = VarConfig.URLWS;
	
	public String ReceberCombo(String filtro, String codConfig) throws Exception {
		String retorno;
		try {
			RequestSoap rSoap = new RequestSoap();
			String METHOD_NAME = "ReceberCombo";
			String SOAP_ACTION = NAMESPACE+METHOD_NAME;
			SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
			soapObject.addProperty("filtro", filtro);
			soapObject.addProperty("codConfig", codConfig);
			rSoap.URL = URL;
			retorno = rSoap.conectar(soapObject, SOAP_ACTION).toString();
		}
		catch (Exception e) {
			throw e;
		}
		return retorno; 		
	}
}
