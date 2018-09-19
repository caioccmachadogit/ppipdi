package base.htcom.com.br.ppipdiapp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;

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
}
