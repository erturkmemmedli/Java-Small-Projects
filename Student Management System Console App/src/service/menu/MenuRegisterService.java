package service.menu;

import service.inter.menu.MenuRegisterServiceInter;

public class MenuRegisterService implements MenuRegisterServiceInter {
    @Override
    public void processLogic() {
        System.out.println("Register Happened!");
    }
}
