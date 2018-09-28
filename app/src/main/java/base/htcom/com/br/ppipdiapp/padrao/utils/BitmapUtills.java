package base.htcom.com.br.ppipdiapp.padrao.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Build;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class BitmapUtills {
	
	public static boolean copiarBitmap_220(File orig, File file_220) throws FileNotFoundException, IOException{
		boolean retorno = false;
		if(!file_220.exists()){
			FileUtills.copyFile(orig, file_220);
		}
		if(file_220.exists()){
			new BitmapFactory();
			Bitmap bitmap_220 = BitmapFactory.decodeFile(file_220.getAbsolutePath());
			FileOutputStream out = new FileOutputStream(file_220);
			BitmapUtills.redimensionarBitmap(bitmap_220, 280, 210, file_220.getName()).compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
   	     	out.close();
   	     	retorno = true;
		}
		return retorno;
	}

	public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) 
	{ // BEST QUALITY MATCH
		final BitmapFactory.Options options = new BitmapFactory.Options(); 
		try {
	    	//First decode with inJustDecodeBounds=true to check dimensions
	 	    options.inJustDecodeBounds = true;
	 	    options.inSampleSize = 8;
	 	    BitmapFactory.decodeFile(path, options);
	 	 
	 	    // Calculate inSampleSize, Raw height and width of image
	 	    final int height = options.outHeight;
	 	    final int width = options.outWidth;
	 	    options.inPreferredConfig = Bitmap.Config.ALPHA_8;//RGB_565;
	 	    int inSampleSize = 1;
	 	 
	 	    if (height > reqHeight) 
	 	    {
	 	        inSampleSize = Math.round((float)height / (float)reqHeight);
	 	    }
	 	    int expectedWidth = width / inSampleSize;
	 	 
	 	    if (expectedWidth > reqWidth) 
	 	    {
	 	        //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
	 	        inSampleSize = Math.round((float)width / (float)reqWidth);
	 	    }
	 	 
	 	    options.inSampleSize = inSampleSize;
	 	 
	 	    // Decode bitmap with inSampleSize set
	 	    options.inJustDecodeBounds = false;
		}
	     catch (Exception e) {
	    	 Log.e("ERROR DECODE BITMAP", e.getMessage());
		}
	    return BitmapFactory.decodeFile(path, options);
	}

	public static void compactarBitmap(Bitmap bitmap, File dest) {
		try {
			FileOutputStream out = new FileOutputStream(dest.getAbsolutePath());
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			Log.e("ERROR COMPACTAR BITMAP", e.getMessage());
			e.printStackTrace();
		}

	}
	
	@SuppressLint("NewApi") public static String SemAcento(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
	
	public static Bitmap redimensionarBitmap(Bitmap bitmap, float largura, float altura, String nomeFoto){
		int width  = bitmap.getWidth();
		int height = bitmap.getHeight();
		
		float scaleWidth;
		float scaleHeight;
		// calcula a escala
		if ( width > 1000 ){
			int widthResize = 1000;
			float newHeight = height / ((float) width / (float) widthResize );
			float newWidth = widthResize;
			scaleWidth  = ((float) newWidth) / width;
			scaleHeight = ((float) newHeight) / height; 
		} else if ( (!nomeFoto.contains("_thumb")) && (!nomeFoto.contains("_220")) ){
			largura = width;
			altura  = height;
			scaleWidth  = ((float) largura) / width;
			scaleHeight = ((float) altura) / height; 
		} else {
			scaleWidth  = ((float) largura) / width;
			scaleHeight = ((float) altura) / height; 
		}
		// cria matrix para manipula��o
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		int x=0;
		int y=0;
		//realiza o resize
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, x, y, width, height, matrix, true);
		Bitmap mutableResizeBitmap = resizedBitmap.copy(resizedBitmap.getConfig(), true);
		        
		return mutableResizeBitmap;		
	}
	
	public static Bitmap redimensionarBitmap_captura(File file, int width, int height, Bitmap bitmap){
		Bitmap auxBitmap = null;
		try {
			int w  = bitmap.getWidth();
			int h = bitmap.getHeight();
			float scaleX = 0;
			float scaleY = 0;
			Matrix matrix = new Matrix();
			
			ExifInterface exif = new ExifInterface(file.getPath()); // Classe para ler tags escritas no JPEG
			if(exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL) != 0){
				// FLUXO PARA TABLET QUE N�O RECONHECE A ORIENTA��O DA FOTO, NECESS�RIO CORRIGIR A ORIENTACAO
				
				scaleX = (float) width / bitmap.getWidth();
				scaleY = (float) height / bitmap.getHeight();
				
				matrix.setScale(scaleX, scaleY);
				
				auxBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
				
				auxBitmap = fixMatrix(file, auxBitmap);
			}
			else{
				if(bitmap.getWidth() < bitmap.getHeight()){
					//VERTICAL
					scaleX = (float) height / bitmap.getWidth();
					scaleY = (float) width / bitmap.getHeight();
				}
				else {
					//HORIZONTAL
					scaleX = (float) width / bitmap.getWidth();
					scaleY = (float) height / bitmap.getHeight();
				}
				matrix.setScale(scaleX, scaleY);
				auxBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
			}
			gravarBitmap(auxBitmap, file.getPath());
		}
		catch (OutOfMemoryError e) { e.printStackTrace(); }
		catch (RuntimeException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }
		return auxBitmap;
	}
	
	public static void gravarBitmap(Bitmap bmp, String pathFile){
	    try {                
	        ByteArrayOutputStream stream = new ByteArrayOutputStream();
	        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);

	        byte[] bytes = stream.toByteArray();;
	        String nomeArquivo = pathFile;

	        FileOutputStream fos = new FileOutputStream(nomeArquivo);
	        fos.write(bytes);
	        fos.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static Bitmap salvarFotoPasta(File foto) throws IOException {
		Bitmap bitmap = BitmapFactory.decodeFile(foto.getPath());
		bitmap = redimensionarBitmap_captura(foto, 800, 600, bitmap);
		
		return bitmap;
	}
	
	private static Bitmap fixMatrix(File file, Bitmap bitmap) throws IOException {
		Matrix matrix = new Matrix();
		boolean fixed = false;
		ExifInterface exif = new ExifInterface(file.getPath()); // Classe para ler tags escritas no JPEG
		int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL); // Orienta��o que foi salva a foto

		// Rotate bitmap
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_180: //3
					matrix.postRotate(180);
					fixed = true;
					break;
				case ExifInterface.ORIENTATION_ROTATE_90:  //6
					matrix.postRotate(90);
					fixed = true;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:  //8
					matrix.postRotate(270);
					fixed = true;
					break;
				default:
					fixed = false;
					break;
			}

		if(!fixed) {
			return bitmap;
		}

		// Corre��o da orienta��o da foto (passa a matrix)
			Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,true);
		
		bitmap.recycle();
		bitmap = null;

		return newBitmap;
	}

	public static Bitmap createOriginalBitmap(final String imagePath) {
		final Bitmap bitmapOrg;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			bitmapOrg = BitmapFactory.decodeFile(imagePath);
		} else {
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = false;
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			options.inDither = true;
			bitmapOrg = BitmapFactory.decodeFile(imagePath, options);
		}
		return bitmapOrg;
	}

	public static Bitmap resizeBitmap(Bitmap source) {
		final int heightOrg = source.getHeight();
		final int heightNew = 800;
		if (heightNew < heightOrg) {
			final int widthOrg = source.getWidth();
			final int widthNew = (heightNew * widthOrg) / heightOrg;

			final Matrix matrix = new Matrix();
			matrix.postScale(((float) widthNew) / widthOrg, ((float) heightNew) / heightOrg);
			source = Bitmap.createBitmap(source, 0, 0, widthOrg, heightOrg, matrix, false);
		}
		return source;
	}

	public static Bitmap rotateImage(final String imagePath, Bitmap source) throws IOException {
		final ExifInterface ei = new ExifInterface(imagePath);
		final int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

		switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				source = rotateImageByAngle(source, 90);
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				source = rotateImageByAngle(source, 180);
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				source = rotateImageByAngle(source, 270);
				break;
		}
		return source;
	}

	public static Bitmap rotateImageByAngle(final Bitmap source, final float angle) {
		final Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	}
	
}
