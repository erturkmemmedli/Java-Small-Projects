package util;

import bean.Config;

import java.util.Scanner;

public class MenuUtil {
    public static void showMenu() {
        Menu.showAllMenu();
        int num = 0;
        Scanner sc = new Scanner(System.in);
        if (Config.isLoggedIn()) {
            num = sc.nextInt();
        }
        Menu selectedMenu = Menu.findMenuNumber(num);
        selectedMenu.process();
    }
}
