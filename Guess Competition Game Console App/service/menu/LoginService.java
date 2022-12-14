package service.menu;

import bean.Config;
import service.intrface.LoginServiceInter;

import java.util.Scanner;

public class LoginService implements LoginServiceInter {
    @Override
    public void process() {
        int trialCount = 0;
        while (trialCount < 3) {
            trialCount++;
            System.out.println("Enter username:");
            Scanner sc1 = new Scanner(System.in);
            String username = sc1.nextLine();
            System.out.println("Enter password:");
            Scanner sc2 = new Scanner(System.in);
            String password = sc2.nextLine();
            if (!(username.equals("erturk") && password.equals("12345"))) {
                if (trialCount < 3) {
                    System.out.println("Wrong username or password. You have " + (3 - trialCount) + " attempt left.");
                } else {
                    System.out.println("You have been banned!");
                    Config.setShouldExit(true);
                }
            } else {
                System.out.println("You have been successfully logged in!");
                Config.setLoggedIn(true);
                break;
            }
        }
    }
}
