package base.htcom.com.br.ppipdiapp.base;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;

import java.io.File;

/**
 * BaseFragment for All application fragments
 */

public class BaseListFragment extends ListFragment {

    public void fragmentTransaction(String tagName, Fragment fragment, boolean addToBackStack, int style) {
        ((BaseActivity) getActivity()).fragmentTransaction(tagName, fragment, addToBackStack, style);
    }

    public void setupBackButton(){
        ((BaseActivity) getActivity()).setupBackButton();
    }

    public void setupNavDrawer(){
        ((BaseActivity) getActivity()).setupNavDrawer();
    }

    public void startCamera(ActivityResult activityResult){
        ((BaseActivity) getActivity()).startCamera(activityResult);
    }

    public Bitmap savePicture(String nameImage){
        return ((BaseActivity) getActivity()).savePicture(nameImage);
    }

    public int getImageCapture(){
        return ((BaseActivity) getActivity()).IMAGE_CAPTURE;
    }

    public File getExternalFilesDir(){
        return ((BaseActivity) getActivity()).externalFilesDir;
    }
}
