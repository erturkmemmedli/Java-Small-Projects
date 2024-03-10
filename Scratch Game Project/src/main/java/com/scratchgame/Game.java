package com.scratchgame;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game {
    private final Configuration config;

    public Game(Configuration config) {
        this.config = config;
    }

    public String[][] generateMatrix() {
        int rows = config.getRows();
        int columns = config.getColumns();
        String[][] matrix = new String[rows][columns];

        Map<String, Map<String, Object>> standardSymbols = (Map<String, Map<String, Object>>) config.getSymbols();
        List<Map<String, Object>> bonusSymbols = (List<Map<String, Object>>) config.getSymbols().get("bonus_symbols");

        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                boolean isStandardSymbol = rand.nextDouble() < 0.5; // Adjust probability as needed
                List<Map<String, Object>> symbols = isStandardSymbol ? standardSymbols : bonusSymbols;
                String symbol = getRandomSymbol(symbols);
                matrix[i][j] = symbol;
            }
        }

        return matrix;
    }

    private String getRandomSymbol(List<Map<String, Object>> symbols) {
        Random rand = new Random();
        int totalWeight = symbols.stream().mapToInt(s -> (int) s.get("weight")).sum();
        int randomNumber = rand.nextInt(totalWeight) + 1;
        int sum = 0;

        for (Map<String, Object> symbol : symbols) {
            sum += (int) symbol.get("weight");
            if (randomNumber <= sum) {
                return (String) symbol.get("name");
            }
        }

        return null;
    }
}
