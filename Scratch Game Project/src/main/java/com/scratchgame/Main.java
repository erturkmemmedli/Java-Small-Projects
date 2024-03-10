package com.scratchgame;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Welcome to Scratch Game!");
//
//        if (args.length != 2) {
//            System.out.println("Usage: java -jar <jar-file> <config-file> <betting-amount>");
//            return;
//        }
//
//        String configFile = args[0];
//        int betAmount = Integer.parseInt(args[1]);
        String configFile = "config.json";

        Configuration config = Configuration.parseConfig(configFile);

        if (config == null) {
            System.out.println("Failed to parse configuration file.");
            return;
        }

//        Game game = new Game(config);
//        String[][] matrix = game.generateMatrix();
//
//        Map<String, List<String>> appliedWinningCombinations = null; // Placeholder
//        String appliedBonusSymbol = null; // Placeholder
//        int reward = calculateReward(betAmount, appliedWinningCombinations, appliedBonusSymbol, config);
//
//        System.out.println("Generated Matrix:");
//        for (String[] row : matrix) {
//            for (String cell : row) {
//                System.out.print(cell + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("Reward: " + reward);
//        System.out.println("Applied Winning Combinations: " + appliedWinningCombinations);
//        System.out.println("Applied Bonus Symbol: " + appliedBonusSymbol);
    }

    private static int calculateReward(int betAmount, Map<String, List<String>> appliedWinningCombinations, String appliedBonusSymbol, Configuration config) {
        int reward = 0;

        if (appliedWinningCombinations != null) {
            // Iterate through each symbol in appliedWinningCombinations
            for (Map.Entry<String, List<String>> entry : appliedWinningCombinations.entrySet()) {
                String symbol = entry.getKey();
                List<String> winningCombinations = entry.getValue();

                // Retrieve the reward multiplier for the symbol from the configuration
                double rewardMultiplier = (double) config.getSymbols().get(symbol).get("reward_multiplier");

                // Iterate through each winning combination for the symbol
                for (String combination : winningCombinations) {
                    // Retrieve the reward multiplier for the winning combination from the configuration
                    double combinationMultiplier = (double) config.getWinCombinations().get(combination).get("reward_multiplier");

                    // Calculate the reward for the combination and multiply it by the betAmount and rewardMultiplier
                    reward += betAmount * combinationMultiplier * rewardMultiplier;
                }
            }
        }

        // If an applied bonus symbol exists, calculate its impact on the reward
        if (appliedBonusSymbol != null) {
            switch (appliedBonusSymbol) {
                case "10x":
                    reward *= 10;
                    break;
                case "5x":
                    reward *= 5;
                    break;
                case "+1000":
                    reward += 1000;
                    break;
                case "+500":
                    reward += 500;
                    break;
                case "MISS":
                    // No impact
                    break;
                default:
                    System.out.println("Unknown bonus symbol: " + appliedBonusSymbol);
            }
        }

        return reward;
    }
}
