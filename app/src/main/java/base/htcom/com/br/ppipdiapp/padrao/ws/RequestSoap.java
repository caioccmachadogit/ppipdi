package base.htcom.com.br.ppipdiapp.padrao.ws;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class RequestSoap {
	
	public String URL;

	private String TAG = getClass().getSimpleName();
	
	public Object conectar(SoapObject soapObject, String soapAction){
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		Log.d(TAG, soapAction+"-REQUEST->"+soapObject.toString());

		envelope.setOutputSoapObject(soapObject);
		MarshalDouble md = new MarshalDouble();
		md.register(envelope);

		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		Object response;
		try {
			httpTransport.call(soapAction, envelope);
			response = envelope.getResponse();
		} catch (Exception exception) {
			Log.e(TAG,"ERROR->"+soapAction, exception);
			response = "x";
		}
		Log.d(TAG, soapAction+"-RESPONSE->"+response);
		return response;
	}
	
	public class MarshalDouble implements Marshal 
	{


	    public Object readInstance(XmlPullParser parser, String namespace, String name, 
	            PropertyInfo expected) throws IOException, XmlPullParserException {
	        
	        return Double.parseDouble(parser.nextText());
	    }


	    public void register(SoapSerializationEnvelope cm) {
	         cm.addMapping(cm.xsd, "double", Double.class, this);
	        
	    }


	    public void writeInstance(XmlSerializer writer, Object obj) throws IOException {
	           writer.text(obj.toString());
	        }
	    
	}

}
