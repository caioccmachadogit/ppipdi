package base.htcom.com.br.ppipdiapp.base;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
import base.htcom.com.br.ppipdiapp.notas.NotasFragment;
import base.htcom.com.br.ppipdiapp.os.OsMenuActitivity;
import base.htcom.com.br.ppipdiapp.padrao.funcoes.ControleConexao;
import base.htcom.com.br.ppipdiapp.padrao.menu.MenuCreator;
import base.htcom.com.br.ppipdiapp.padrao.menu.MenuItemEnum;
import base.htcom.com.br.ppipdiapp.padrao.menu.NavDrawerUtil;
import base.htcom.com.br.ppipdiapp.padrao.menu.TipoMenu;
import base.htcom.com.br.ppipdiapp.padrao.utils.BitmapUtills;
import base.htcom.com.br.ppipdiapp.padrao.utils.FileUtills;
import base.htcom.com.br.ppipdiapp.padrao.utils.SharedPreferencesUtills;

public class BaseActivity extends AppCompatActivity {

    protected String TAG;

    protected DrawerLayout drawerLayout;

    protected View view;

    protected TextView tvTitle, tvVersao;

    protected Activity mActivity;

    private String mTagName;

    private NavigationView navigationView;

    private Toolbar toolbar;

    private ActionBar actionBar;

    protected final int PERMISSIONS_APP = 2;

    protected File externalFilesDir;

    protected File tempImageFile;

    protected final int IMAGE_CAPTURE = 4;

    protected ActivityResult mActivityResult;

    protected void setmActivity(Activity activity){
        this.mActivity = activity;

        USER = SharedPreferencesUtills.loadSavedPreferencesString("USER", this);
        EMPRESA = SharedPreferencesUtills.loadSavedPreferencesString("EMPRESA", this);

        externalFilesDir = getExternalFilesDir("images");
    }

    protected void setTAG(String tag){
        this.TAG = tag;
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
        findViewById(R.id.container).setVisibility(View.GONE);
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout container =  findViewById(R.id.content_view);
        this.view = inflater.inflate(view, container);
    }

    /**
     * Fragement  navigation method
     *
     * @param tagName        : identifying the fragment with tagname
     * @param fragment       ; navigate to screen
     * @param addToBackStack : adding the the backstack
     * @param style          : animation style
     */
    public void fragmentTransaction(String tagName, Fragment fragment, boolean addToBackStack, int style) {
        log("tagName->"+tagName+"|addToBackStack->"+addToBackStack);
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mTagName = tagName;
            switch (style) {
                case 1:
                    transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                    transaction.replace(R.id.container, fragment, tagName);
                    break;

                case 2:
                    transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    transaction.replace(R.id.container, fragment, tagName);
                    break;
                default:
                    transaction.replace(R.id.container, fragment, tagName);
                    break;
            }
            if (addToBackStack) {
                transaction.addToBackStack(tagName);
            }
            transaction.commit();
        }
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        log("onSupportNavigateUp");
        onBackPressed();
        return true;
    }

    protected void setupBackButton(){
        drawerLayout.setEnabled(false);

        if (toolbar != null && actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_voltar);
        }
    }

    protected void setupNavDrawer(){
        drawerLayout.setEnabled(true);

        if (toolbar != null && actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
    }

    // Configura o Nav Drawer
    protected void setupNavDrawer(MenuItemEnum menuItemChecked, TipoMenu tipoMenu) {
        menuItemChecked.itemIsChecked(menuItemChecked);
        // Drawer Layout
        actionBar = getSupportActionBar();
        // Ícone do menu do nav drawer
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        log("onNavDrawerItemSelected->"+menuItem.toString());
        switch (menuItem.getItemId()) {
            case R.string.menu_etp:
                fragmentTransaction(ListOSFragment.class.getSimpleName(), new ListOSFragment(), false, 1);
                break;
            case R.string.menu_etp_finalizada:
                fragmentTransaction(ListOSFinalizadaFragment.class.getSimpleName(), new ListOSFinalizadaFragment(), false, 1);
                break;
            case R.string.menu_verifica_etp:
                if(verificaConexao())
                    ((MainActivity)mActivity).verificarOS();
                break;
            case R.string.menu_verificar_rev:
                if(verificaConexao()){
                    fragmentTransaction(ReceberOSRevFragment.class.getSimpleName(), new ReceberOSRevFragment(), false, 1);
                }
                break;
            case R.string.menu_enviar_etp:
                if(verificaConexao()){
                    fragmentTransaction(EnviarOSFragment.class.getSimpleName(), new EnviarOSFragment(), false, 1);
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
                fragmentTransaction(EvFragment.class.getSimpleName(), new EvFragment(), false, 1);
                break;
            case R.string.menu_est_vert_fotos:
                fragmentTransaction(EvFotosFragment.class.getSimpleName(), new EvFotosFragment(), false, 1);
                break;
            case R.string.menu_carregamento:
                fragmentTransaction(ListCarregFragment.class.getSimpleName(), new ListCarregFragment(), false, 1);
                break;
            case R.string.menu_carreg_exist:
                if(((OsMenuActitivity)mActivity).verificaOsAberta())
                    fragmentTransaction(ListCarregPlantaFragment.class.getSimpleName(), new ListCarregPlantaFragment(), false, 1);
                break;
            case R.string.menu_arq_pref:
                fragmentTransaction(ArqPrefListFragment.class.getSimpleName(), new ArqPrefListFragment(), false, 1);
                break;
            case R.string.menu_baterias:
                if(((OsMenuActitivity)mActivity).verificaOsAberta())
                    fragmentTransaction(ListBateriaFragment.class.getSimpleName(), new ListBateriaFragment(), false, 1);
                break;
            case R.string.menu_notas:
                fragmentTransaction(NotasFragment.class.getSimpleName(), new NotasFragment(), false, 1);
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

    protected void abrirAlertLogout() {
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
                if (drawerLayout != null && drawerLayout.isEnabled()) {
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
        if(TAG == null)
            TAG = "BaseActivity";
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

    protected boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            return false;
        }
    }

    protected void requestPermission() {
        String permissions[] = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE};
        ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_APP);
        Log.d("requestPermission", "REQUERIR PERMISSAO");
    }

    protected boolean verifyGrantResults(int[] grantResults){
        boolean resultOk = true;
        for (int grant:grantResults) {
            if(grant != PackageManager.PERMISSION_GRANTED){
                resultOk = false;
                break;
            }
        }
        Log.d("verifyGrantResults", String.valueOf(resultOk));
        return resultOk;
    }

    protected void startCamera(ActivityResult activityResult) {
        mActivityResult = activityResult;
        File tempFile = FileUtills.createTempImageFile(externalFilesDir);
        if (tempFile != null) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if ((intent.resolveActivity(getContext().getPackageManager()) != null)) {
                tempImageFile = tempFile;
                Uri photoURI = FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".ppipdiimagem.fileprovider", tempFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, IMAGE_CAPTURE);
            }
        }
    }

    protected Bitmap savePicture(String nameImage){
        final File imageFile = new File(externalFilesDir, nameImage+".jpg");
        if (imageFile.exists()) {
            imageFile.delete();
        }

        FileOutputStream out = null;
        Bitmap finalBitmap = null;
        String tempPath = tempImageFile.getAbsolutePath();
        Bitmap bitmapOrg = BitmapUtills.createOriginalBitmap(tempPath);
        try {
            bitmapOrg = BitmapUtills.rotateImage(tempPath, bitmapOrg);
            finalBitmap = BitmapUtills.resizeBitmap(bitmapOrg);
            out = new FileOutputStream(imageFile);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (final IOException e) {
            }
        }
        try {
            tempImageFile.delete();
        }
        catch (final Exception e) {
        }
        return finalBitmap;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mActivityResult.onActivityResult(new Result(requestCode,resultCode,data));
    }
}
