package util;

import bean.Config;
import service.intrface.MenuProcess;
import service.menu.*;

public enum Menu {
    LOGIN(0, "Login to the system", new LoginService()),
    REGISTER(1, "Register players", new RegisterService()),
    START(2, "Start the game", new StartService()),
    LOGOUT(3, "Logout from the system", new LogoutService()),
    EXIT(4, "Exit the game", new ExitService()),
    UNKNOWN;

    private int number;
    private String label;
    private MenuProcess service;

    Menu() {}

    Menu(int number, String label, MenuProcess service) {
        this.number = number;
        this.label = label;
        this.service = service;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MenuProcess getService() {
        return service;
    }

    public void setService(MenuProcess service) {
        this.service = service;
    }

    @Override
    public String toString() {
        if (!Config.isLoggedIn()) {
            return "";
        }
        return number + "." + label;
    }

    public void process() {
        service.process();
        if (!Config.isShouldExit()) {
            MenuUtil.showMenu();
        }
    }

    public static void showAllMenu() {
        Menu[] menu = Menu.values();
        if (!Config.isLoggedIn()) {
            System.out.println(menu[0].label);
        } else {
            System.out.println("Please select the menu.");
            for (int i = 1; i < menu.length; i++) {
                if (menu[i] != UNKNOWN && Config.isLoggedIn()) {
                    System.out.println(menu[i]);
                }
            }
        }
    }

    public static Menu findMenuNumber(int num) {
        Menu[] menu = Menu.values();
        for (int i = 0; i < menu.length; i++) {
            if (menu[i].getNumber() == num) {
                return menu[i];
            }
        }
        return Menu.UNKNOWN;
    }
}
