package base.htcom.com.br.ppipdiapp.padrao.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtills {
	public boolean Deletar(String path, Context context){
		boolean resp = false;
			File file = new File(path);
			if(file.exists()){
				resp = file.delete();
				Toast.makeText(context, "Registro Exclu?do!", Toast.LENGTH_LONG).show();
			}
		return resp;
	}
	
	@SuppressWarnings("unused")
	public static void copyFile(File sourceLocation, File targetLocation)
			throws FileNotFoundException, IOException {
		InputStream in = new FileInputStream(sourceLocation);
		OutputStream out = new FileOutputStream(targetLocation);

		// Copy the bits from instream to outstream
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

}
