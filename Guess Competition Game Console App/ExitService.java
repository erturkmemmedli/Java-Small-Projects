package service.menu;

import bean.Config;
import bean.Player;
import service.intrface.ExitServiceInter;

public class ExitService implements ExitServiceInter {
    @Override
    public void process() {
        Config.setShouldExit(true);
        for (int i = 0; i < 5; i++) {
            Config.setPlayers(i, null);
        }
    }
}
