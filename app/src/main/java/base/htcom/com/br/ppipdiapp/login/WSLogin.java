package base.htcom.com.br.ppipdiapp.login;


import android.util.Log;

import org.ksoap2.serialization.SoapObject;

import base.htcom.com.br.ppipdiapp.padrao.utils.VarConfig;
import base.htcom.com.br.ppipdiapp.padrao.ws.RequestSoap;

public class WSLogin {
	
	public final String NAMESPACE = "http://tempuri.org/";

	public final String URL = VarConfig.URLWS;

	private String TAG = getClass().getSimpleName();
	
	public String validarColaborador( String email, String senha) throws Exception {
		String retorno;
		try {
			RequestSoap rSoap = new RequestSoap();
			String METHOD_NAME = "ValidarAcesso";
			String SOAP_ACTION = NAMESPACE+METHOD_NAME;
			SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);
			soapObject.addProperty("email", email);
			soapObject.addProperty("senha", senha);
			rSoap.URL = URL;
			retorno = rSoap.conectar(soapObject, SOAP_ACTION).toString();
		}
		catch (Exception e) {
			Log.e(TAG,"validarColaborador->",e);
			throw e;
		}
		return retorno; 		
	}
}
