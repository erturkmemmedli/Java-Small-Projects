package com.scratchgame;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Configuration {
    private int columns;
    private int rows;
    private Map<String, Map<String, Object>> symbols;
    private Map<String, Object> probabilities;
    @JsonProperty("win_combinations")
    private Map<String, Map<String, Object>> winCombinations;

    // Getters for rows, columns, symbols, probabilities, and win combinations
    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Map<String, Map<String, Object>> getSymbols() {
        return symbols;
    }

    public Map<String, Object> getProbabilities() {
        return probabilities;
    }

    public Map<String, Map<String, Object>> getWinCombinations() {
        return winCombinations;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setSymbols(Map<String, Map<String, Object>> symbols) {
        this.symbols = symbols;
    }

    public void setProbabilities(Map<String, Object> probabilities) {
        this.probabilities = probabilities;
    }

    public void setWinCombinations(Map<String, Map<String, Object>> winCombinations) {
        this.winCombinations = winCombinations;
    }

    public static Configuration parseConfig(String configFile) {
        Configuration config = new Configuration();
        try {
            Object object = new JSONParser().parse(new FileReader(configFile));
            JSONObject json_object = (JSONObject) object;


            Integer columns = null;
            columns = ((Long) json_object.get("columns")).intValue();
            config.setColumns(columns);


            Integer rows = null;
            rows = ((Long) json_object.get("rows")).intValue();
            config.setRows(rows);


            JSONObject symbolsJson = (JSONObject) json_object.get("symbols");
            Map<String, Map<String, Object>> symbols = new HashMap<>();
            for (Object key : symbolsJson.keySet()) {
                String symbolName = (String) key;
                JSONObject symbolDetailsJson = (JSONObject) symbolsJson.get(key);
                Map<String, Object> symbolDetails = new HashMap<>();
                for (Object detailKey : symbolDetailsJson.keySet()) {
                    String detailName = (String) detailKey;
                    Object detailValue = symbolDetailsJson.get(detailKey);
                    symbolDetails.put(detailName, detailValue);
                }
                symbols.put(symbolName, symbolDetails);
            }
            config.setSymbols(symbols);


            JSONObject probabilitiesJson = (JSONObject) json_object.get("probabilities");
            Map<String, Object> probabilities = new HashMap<>();
            for (Object key : probabilitiesJson.keySet()) {
                String symbolType = (String) key;
                Object value = probabilitiesJson.get(key);
                if (value instanceof JSONArray) {
                    JSONArray symbolArray = (JSONArray) value;
                    List<Map<String, Object>> standardSymbols = new ArrayList<>();
                    for (Object symbolEntry : symbolArray) {
                        JSONObject symbolEntryJson = (JSONObject) symbolEntry;
                        Map<String, Object> symbolMap = new HashMap<>();
                        symbolMap.put("column", symbolEntryJson.get("column"));
                        symbolMap.put("row", symbolEntryJson.get("row"));
                        symbolMap.put("symbols", symbolEntryJson.get("symbols"));
                        standardSymbols.add(symbolMap);
                    }
                    probabilities.put(symbolType, standardSymbols);
                }
                else if (value instanceof JSONObject) {
                    probabilities.put(symbolType, value);
                }
            }
            config.setProbabilities(probabilities);


            JSONObject winCombinationsJson = (JSONObject) json_object.get("win_combinations");
            Map<String, Map<String, Object>> winCombinations = new HashMap<>();
            for (Object key : winCombinationsJson.keySet()) {
                String combinationName = (String) key;
                JSONObject combinationDetailsJson = (JSONObject) winCombinationsJson.get(key);
                Map<String, Object> combinationDetails = new HashMap<>();
                for (Object detailKey : combinationDetailsJson.keySet()) {
                    String detailName = (String) detailKey;
                    Object detailValue = combinationDetailsJson.get(detailKey);
                    combinationDetails.put(detailName, detailValue);
                }
                winCombinations.put(combinationName, combinationDetails);
            }
            config.setWinCombinations(winCombinations);

            System.out.println("cols "+config.getColumns());
            System.out.println("rows "+config.getRows());
            System.out.println("symbols "+config.getSymbols());
            System.out.println("prob "+config.getProbabilities());
            System.out.println("winComb "+config.getWinCombinations());
            return config;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

//    public static class Probabilities {
//        @JsonProperty("standard_symbols")
//        private List<Map<String, Object>> standardSymbols;
//
//        @JsonProperty("bonus_symbols")
//        private BonusSymbols bonusSymbols;
//
//        public List<Map<String, Object>> getStandardSymbols() {
//            return standardSymbols;
//        }
//
//        public BonusSymbols getBonusSymbols() {
//            return bonusSymbols;
//        }
//    }
//
//    public static class BonusSymbols {
//        private Map<String, Integer> symbols;
//
//        public Map<String, Integer> getSymbols() {
//            return symbols;
//        }
//    }
}
