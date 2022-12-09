package util;

import java.util.Scanner;

public class MenuUtil {
    public static void showMenu() {
        Menu.showAllMenu();
        Scanner sc = new Scanner(System.in);
        int selectedMenuNumber = sc.nextInt();
        Menu selectedMenu = Menu.find(selectedMenuNumber);
        selectedMenu.process();
    }

    public static void processMenu(Menu menu) {
        menu.process();
    }
}
