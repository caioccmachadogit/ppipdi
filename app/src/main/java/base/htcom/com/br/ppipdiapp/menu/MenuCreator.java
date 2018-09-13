package base.htcom.com.br.ppipdiapp.menu;

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
