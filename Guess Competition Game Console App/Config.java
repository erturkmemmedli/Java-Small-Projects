package bean;

public class Config {
    private static Config config;
    private static boolean isLoggedIn;
    private static boolean shouldExit;

    private static Player[] players = new Player[5];

    public static Config instance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }
    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public static Player[] getPlayers() {
        return players;
    }

    public static void setPlayers(int index, Player player) {
        players[index] = player;
    }

    public static boolean isShouldExit() {
        return shouldExit;
    }

    public static void setShouldExit(boolean shouldExit) {
        Config.shouldExit = shouldExit;
    }
}
