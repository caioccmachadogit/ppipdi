package base.htcom.com.br.ppipdiapp.padrao.menu;

import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.TextView;

import base.htcom.com.br.ppipdiapp.R;

/**
 * Created by ccouto on 24/10/2017.
 */

public class NavDrawerUtil {
    public static void setHeaderValues(View navDrawerView, String usuario, String empresa) {

        TextView tUserName = ((NavigationView) navDrawerView).getHeaderView(0).findViewById(R.id.tvUsuario);
        TextView tEmpresa = ((NavigationView) navDrawerView).getHeaderView(0).findViewById(R.id.tvEmpresa);
        if (tUserName != null && tEmpresa != null) {
            tUserName.setText(usuario);
            tEmpresa.setText(empresa);
        }
    }
}
