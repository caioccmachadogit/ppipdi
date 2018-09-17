package base.htcom.com.br.ppipdiapp.base;

import android.support.v4.app.Fragment;

/**
 * BaseFragment for All application fragments
 */

public class BaseFragment extends Fragment {

    public void fragmentTransaction(String tagName, Fragment fragment, boolean addToBackStack, int style) {
        ((BaseActivity) getActivity()).fragmentTransaction(tagName, fragment, addToBackStack, style);
    }
}
