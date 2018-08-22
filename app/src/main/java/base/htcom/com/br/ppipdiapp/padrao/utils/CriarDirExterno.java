package base.htcom.com.br.ppipdiapp.padrao.utils;

import android.os.Environment;

import java.io.File;

public class CriarDirExterno {

	public String PATHDIR;
	public boolean CriarDirDB(String dir){
		boolean retorno = false;
		try{
			PATHDIR = Environment.getExternalStorageDirectory().getAbsolutePath()+dir;
			String state = Environment.getExternalStorageState();
			boolean mExternalStorageAvailable = false; 
			boolean mExternalStorageWriteable = false;
			if (Environment.MEDIA_MOUNTED.equals(state)) {
				// Podemos ler e escrever os meios de comunica??o 
				mExternalStorageAvailable = mExternalStorageWriteable = true;
				retorno = true;
					File file = new File (PATHDIR);
					if ( !file.exists() ){
						file.mkdirs();
					}
				}
			else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
				// S? podemos ler a m?dia 
				mExternalStorageAvailable = true; 
				mExternalStorageWriteable = false; 
				}
			else { // Outra coisa que est? errada. Pode ser um de muitos outros estados, mas tudo o que precisamos 
					// N?o podemos ler e escrever 
					mExternalStorageAvailable = mExternalStorageWriteable = false;
				}
		}
		catch(Exception ex){
			ex.getMessage();
		}
		return retorno;
	}
}
