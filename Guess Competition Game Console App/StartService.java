package service.menu;

import bean.Config;
import bean.Player;
import service.intrface.StartServiceInter;

import java.util.*;

public class StartService implements StartServiceInter {
    @Override
    public void process() {
        ArrayList<Integer> winners = new ArrayList<Integer>();
        Player[] players = Config.getPlayers();
        for (int i = 0; i < 5; i++) {
            winners.add(i);
        }
        while (true) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            Random rand = new Random();
            int value = 1 + rand.nextInt(5);
            for (int i = 0; i < winners.size(); i++) {
                System.out.println(players[winners.get(i)].getName() + " " + players[winners.get(i)].getSurname() + ", please choose a number between 1-5.");
                Scanner sc = new Scanner(System.in);
                int selectedNum = sc.nextInt();
                if (selectedNum == value) {
                    temp.add(winners.get(i));
                }
            }
            if (temp.size() == 1) {
                System.out.println(players[temp.get(0)].getName() + " " + players[winners.get(0)].getSurname() + ", congratulations, you're the winner!");
                Config.setShouldExit(true);
                break;
            } else if (temp.size() > 1) {
                winners = temp;
                for (int i = 0; i < winners.size(); i++) {
                    if (i == winners.size() - 1) {
                        System.out.print(players[winners.get(i)].getName() + " ");
                    } else {
                        System.out.print(players[winners.get(i)].getName() + ", ");
                    }
                }
                System.out.println("are in the next stage");
            } else {
                for (int i = 0; i < winners.size(); i++) {
                    if (i == winners.size() - 1) {
                        System.out.print(players[winners.get(i)].getName() + " ");
                    } else {
                        System.out.print(players[winners.get(i)].getName() + ", ");
                    }
                }
                System.out.println("are in the next stage");
            }
        }
    }
}
