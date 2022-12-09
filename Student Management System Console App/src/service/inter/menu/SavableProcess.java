package service.inter;

import bean.Config;

public interface SavableProcess extends Process {
    public default void process() {
        processLogic();
        Config.save();
    }

}
