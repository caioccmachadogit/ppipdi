package base.htcom.com.br.ppipdiapp.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import base.htcom.com.br.ppipdiapp.R;
import base.htcom.com.br.ppipdiapp.arq_pref.ArqPrefListFragment;
import base.htcom.com.br.ppipdiapp.bateria.ListBateriaFragment;
import base.htcom.com.br.ppipdiapp.carregamento.ListCarregFragment;
import base.htcom.com.br.ppipdiapp.carregamento.ListCarregPlantaFragment;
import base.htcom.com.br.ppipdiapp.ev.EvFotosFragment;
import base.htcom.com.br.ppipdiapp.ev.EvFragment;
import base.htcom.com.br.ppipdiapp.login.LoginActivity;
import base.htcom.com.br.ppipdiapp.main.EnviarOSFragment;
import base.htcom.com.br.ppipdiapp.main.ListOSFinalizadaFragment;
import base.htcom.com.br.ppipdiapp.main.ListOSFragment;
import base.htcom.com.br.ppipdiapp.main.MainActivity;
import base.htcom.com.br.ppipdiapp.main.ReceberOSRevFragment;
import base.htcom.com.br.ppipdiapp.padrao.menu.MenuCreator;
import base.htcom.com.br.ppipdiapp.padrao.menu.MenuItemEnum;
import base.htcom.com.br.ppipdiapp.padrao.menu.NavDrawerUtil;
import base.htcom.com.br.ppipdiapp.padrao.menu.TipoMenu;
import base.htcom.com.br.ppipdiapp.notas.NotasFragment;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;
import base.htcom.com.br.ppipdiapp.padrao.funcoes.ControleConexao;
import base.htcom.com.br.ppipdiapp.padrao.utils.SharedPreferencesUtills;

public class BaseActivity extends AppCompatActivity {

    protected String TAG;

    protected DrawerLayout drawerLayout;

    protected View view;

    protected TextView tvTitle, tvVersao;

    protected Activity mActivity;

    protected void setmActivity(Activity activity){
        this.mActivity = activity;
    }

    protected Context getContext() {
        return this;
    }

    protected View getLayouView(){
        return  view;
    }

    public String USER;

    public String EMPRESA;

    protected void replaceLinear(int view) {
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout container =  findViewById(R.id.content_view);
        this.view = inflater.inflate(view, container);
    }

    // Adiciona o fragment no centro da tela
    protected void replaceFragment(Fragment frag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag, "TAG").commit();

        USER = SharedPreferencesUtills.loadSavedPreferencesString("USER", this);
        EMPRESA = SharedPreferencesUtills.loadSavedPreferencesString("EMPRESA", this);
    }

    //=========================MENU LATERAL E TOOLBAR==========================

    protected void setTitleTela(String title){
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(title);

        tvVersao = findViewById(R.id.tvVersao);
        tvVersao.setText(getContext().getResources().getString(R.string.action_settings));
    }

    // Configura a Toolbar
    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    // Configura o Nav Drawer
    protected void setupNavDrawer(MenuItemEnum menuItemChecked, TipoMenu tipoMenu) {
        menuItemChecked.itemIsChecked(menuItemChecked);
        // Drawer Layout
        final ActionBar actionBar = getSupportActionBar();
        // Ícone do menu do nav drawer
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null && drawerLayout != null) {
            // Atualiza a imagem e textos do header
             NavDrawerUtil.setHeaderValues(navigationView, USER, EMPRESA);
            // Trata o evento de clique no menu.
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // Seleciona a linha
                            menuItem.setChecked(true);
                            // Fecha o menu
                            drawerLayout.closeDrawers();
                            // Trata o evento do menu
                            onNavDrawerItemSelected(menuItem);
                            return true;
                        }
                    });
        }

        Menu menu = navigationView.getMenu();
        MenuCreator.create(menu, tipoMenu);
        navigationView.invalidate();
    }

    // Trata o evento do menu lateral
    private void onNavDrawerItemSelected(MenuItem menuItem) {
        setTitleTela(menuItem.toString());
        switch (menuItem.getItemId()) {
            case R.string.menu_etp:
                replaceFragment(new ListOSFragment());
                break;
            case R.string.menu_etp_finalizada:
                replaceFragment(new ListOSFinalizadaFragment());
                break;
            case R.string.menu_verifica_etp:
                if(verificaConexao())
                    ((MainActivity)mActivity).verificarOS();
                break;
            case R.string.menu_verificar_rev:
                if(verificaConexao()){
                    replaceFragment(new ReceberOSRevFragment());
                }
                break;
            case R.string.menu_enviar_etp:
                if(verificaConexao()){
                    replaceFragment(new EnviarOSFragment());
                }
                break;
            case R.string.menu_enviar_fotos:
                if(verificaConexao())
                    ((MainActivity)mActivity).enviarUpload();
                break;
            case R.string.menu_sair:
                abrirAlertLogout();
                break;

            case R.string.menu_est_vert:
                replaceFragment(new EvFragment());
                break;
            case R.string.menu_est_vert_fotos:
                replaceFragment(new EvFotosFragment());
                break;
            case R.string.menu_carregamento:
                replaceFragment(new ListCarregFragment());
                break;
            case R.string.menu_carreg_exist:
                if(((OsMenuActitivity)mActivity).verificaOsAberta())
                    replaceFragment(new ListCarregPlantaFragment());
                break;
            case R.string.menu_arq_pref:
                replaceFragment(new ArqPrefListFragment());
                break;
            case R.string.menu_baterias:
                if(((OsMenuActitivity)mActivity).verificaOsAberta())
                    replaceFragment(new ListBateriaFragment());
                break;
            case R.string.menu_notas:
                replaceFragment(new NotasFragment());
                break;
            case R.string.menu_finalizar:
                ((OsMenuActitivity)mActivity).finalizarOs();
                break;
            case R.string.menu_voltar:
                finish();
                break;
        }
    }

    protected boolean verificaConexao(){
        if(!ControleConexao.checkNetworkInterface(this).equals("none")){
            return true;
        }
        else {
            Toast.makeText(this, "Verifique sua Conexão com a Internet e tente novamente!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void abrirAlertLogout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Sair");
        alertDialogBuilder.setMessage("Deseja realmente Sair da aplicação?");
        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferencesUtills.deletePreferences("USER", getContext());
                        SharedPreferencesUtills.deletePreferences("EMPRESA", getContext());
                        startActivity(new Intent(mActivity, LoginActivity.class));
                    }
                });
        // set negative button: No message
        alertDialogBuilder.setNegativeButton("Não",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // cancel the alert box and put a Toast to the user
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Trata o clique no botão que abre o menu.
                if (drawerLayout != null) {
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    // Abre o menu lateral
    protected void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    // Fecha o menu lateral
    protected void closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //=========================MENU LATERAL E TOOLBAR==========================

    protected void log(String msg) {
        Log.d(TAG, msg);
    }

    protected void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void toast(int msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void snack(View view, int msg, Runnable runnable) {
        this.snack(view, this.getString(msg), runnable);
    }

    protected void snack(View view, int msg) {
        this.snack(view, this.getString(msg), (Runnable) null);
    }

    protected void snack(View view, String msg) {
        this.snack(view, msg, (Runnable) null);
    }

    protected void snack(View view, String msg, final Runnable runnable) {
        Snackbar.make(view, msg, 0).setAction("Ok", new View.OnClickListener() {
            public void onClick(View v) {
                if (runnable != null) {
                    runnable.run();
                }

            }
        }).show();
    }
}
