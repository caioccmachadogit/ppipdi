package base.htcom.com.br.ppipdiapp.padrao.menu;

import android.view.Menu;

/**
 * Created by ccouto on 13/12/2017.
 */

public class MenuCreator {

    public static void create(Menu menu, TipoMenu tipoMenu){
        switch (tipoMenu){
            case PRINCIPAL:
                addItemPrincipal(menu);
                break;
            case OS:
                addItemOs(menu);
                break;
        }
    }

    private static void addItemOs(Menu menu) {
        menu.add(1,MenuItemEnum.Est_Vert.getMenuId(),1, MenuItemEnum.Est_Vert.getItem()).setCheckable(true).setChecked(MenuItemEnum.Est_Vert.isChecked());
        menu.add(1,MenuItemEnum.Est_Vert_Fotos.getMenuId(),1, MenuItemEnum.Est_Vert_Fotos.getItem()).setCheckable(true).setChecked(MenuItemEnum.Est_Vert_Fotos.isChecked());
        menu.add(1,MenuItemEnum.Carregamento.getMenuId(),1, MenuItemEnum.Carregamento.getItem()).setCheckable(true).setChecked(MenuItemEnum.Carregamento.isChecked());
        menu.add(1,MenuItemEnum.Carregamento_Existente.getMenuId(),1, MenuItemEnum.Carregamento_Existente.getItem()).setCheckable(true).setChecked(MenuItemEnum.Carregamento_Existente.isChecked());
        menu.add(1,MenuItemEnum.Arq_Pref.getMenuId(),1, MenuItemEnum.Arq_Pref.getItem()).setCheckable(true).setChecked(MenuItemEnum.Arq_Pref.isChecked());
        menu.add(1,MenuItemEnum.Baterias.getMenuId(),1, MenuItemEnum.Baterias.getItem()).setCheckable(true).setChecked(MenuItemEnum.Baterias.isChecked());
        menu.add(1,MenuItemEnum.Notas.getMenuId(),1, MenuItemEnum.Notas.getItem()).setCheckable(true).setChecked(MenuItemEnum.Notas.isChecked());
        menu.add(1,MenuItemEnum.Finalizar_ETP.getMenuId(),1, MenuItemEnum.Finalizar_ETP.getItem()).setCheckable(true).setChecked(MenuItemEnum.Finalizar_ETP.isChecked());
        menu.add(1,MenuItemEnum.Voltar.getMenuId(),1, MenuItemEnum.Voltar.getItem()).setCheckable(true).setChecked(MenuItemEnum.Voltar.isChecked());
    }

    private static void addItemPrincipal(Menu menu) {
        menu.add(1,MenuItemEnum.ETP.getMenuId(),1, MenuItemEnum.ETP.getItem()).setCheckable(true).setChecked(MenuItemEnum.ETP.isChecked());
        menu.add(1,MenuItemEnum.ETP_Finalizada.getMenuId(),1, MenuItemEnum.ETP_Finalizada.getItem()).setCheckable(true).setChecked(MenuItemEnum.ETP_Finalizada.isChecked());
        menu.add(1,MenuItemEnum.Verificar_ETP.getMenuId(),1, MenuItemEnum.Verificar_ETP.getItem()).setCheckable(true).setChecked(MenuItemEnum.Verificar_ETP.isChecked());
        menu.add(1,MenuItemEnum.Verificar_Rev_ETP.getMenuId(),1, MenuItemEnum.Verificar_Rev_ETP.getItem()).setCheckable(true).setChecked(MenuItemEnum.Verificar_Rev_ETP.isChecked());
        menu.add(1,MenuItemEnum.Enviar_ETP.getMenuId(),1, MenuItemEnum.Enviar_ETP.getItem()).setCheckable(true).setChecked(MenuItemEnum.Enviar_ETP.isChecked());
        menu.add(1,MenuItemEnum.Enviar_Fotos.getMenuId(),1, MenuItemEnum.Enviar_Fotos.getItem()).setCheckable(true).setChecked(MenuItemEnum.Enviar_Fotos.isChecked());
        menu.add(1,MenuItemEnum.Sair.getMenuId(),1, MenuItemEnum.Sair.getItem()).setCheckable(true).setChecked(MenuItemEnum.Sair.isChecked());}
}
