package main;

import bean.Config;
import util.MenuUtil;

public class Main {
    public static void main(String[] args) throws Exception {
        Config.initialize();
        MenuUtil.showMenu();
    }
}
