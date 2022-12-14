package service.menu;

import bean.Config;
import service.intrface.LogoutServiceInter;

public class LogoutService implements LogoutServiceInter {
    @Override
    public void process() {
        Config.setLoggedIn(false);
    }
}
