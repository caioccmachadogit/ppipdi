package base.htcom.com.br.ppipdiapp.base;

import android.support.v4.app.Fragment;

import base.htcom.com.br.ppipdiapp.padrao.menu.MenuItemEnum;
import base.htcom.com.br.ppipdiapp.padrao.menu.TipoMenu;

/**
 * BaseFragment for All application fragments
 */

public class BaseFragment extends Fragment {

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
