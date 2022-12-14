package service.menu;

import bean.Config;
import bean.Player;
import service.intrface.RegisterServiceInter;

import java.util.Scanner;

public class RegisterService implements RegisterServiceInter {
    @Override
    public void process() {
        System.out.println("You should enter 5 players.");
        for (int i = 0; i < 5; i++) {
            Scanner sc1 = new Scanner(System.in);
            System.out.println("Enter name of player" + (i+1) + ".");
            String name = sc1.nextLine();
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Enter surname of player" + (i+1) + ".");
            String surname = sc2.nextLine();
            Config.setPlayers(i, new Player(name, surname));
        }
        System.out.println("Players have been successfully registered!");
    }
}
