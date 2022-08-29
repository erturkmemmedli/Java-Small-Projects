/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package main;

import util.InputUtil;
import util.MenuUtil;

/**
 *
 * @author Erturk Memmedli
 */
public class StudentRegistrationSystemConsoleApp {

    public static void main(String[] args) {
        int menu = 0;
        while (true) {
            menu = InputUtil.requireNumber(MenuUtil.menu_introduction);
            MenuUtil.processMenu(menu);
        }
    }
}
