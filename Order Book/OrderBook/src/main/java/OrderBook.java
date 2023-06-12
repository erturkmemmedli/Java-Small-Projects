import java.io.*;
import java.util.HashMap;

public class OrderBook {
    private HashMap<Integer, Integer> asks;
    private HashMap<Integer, Integer> bids;

    public OrderBook() {
        asks = new HashMap<>();
        bids = new HashMap<>();
    }

    public void input(String inputFilepath, String outputFilepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilepath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilepath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String command = parts[0];

            if (command.equals("u")) {
                update(parts);
            } else if (command.equals("q")) {
                query(parts, writer);
            } else if (command.equals("o")) {
                order(parts);
            }
        }

        writer.close();
    }

    private void update(String[] parts) {
        int price = Integer.parseInt(parts[1]);
        int size = Integer.parseInt(parts[2]);
        String type = parts[3];

        if (type.equals("bid")) {
            bids.put(price, size);
        } else if (type.equals("ask")) {
            asks.put(price, size);
        }
    }

    private void query(String[] parts, BufferedWriter writer) throws IOException {
        String type = parts[1];

        if (type.equals("best_bid")) {
            write(writer, bids);
        } else if (type.equals("best_ask")) {
            write(writer, asks);
        } else if (type.equals("size")) {
            int price = Integer.parseInt(parts[2]);
            int bidSize = bids.getOrDefault(price, 0);
            int askSize = asks.getOrDefault(price, 0);
            int spreadSize = bidSize + askSize;
            writer.write(String.valueOf(spreadSize));
            writer.newLine();
        }
    }

    private void write(BufferedWriter writer, HashMap<Integer, Integer> map) throws IOException {
        int price = bestPrice(map);
        int size = map.getOrDefault(price, 0);
        writer.write(price + "," + size);
        writer.newLine();
    }

    private void order(String[] parts) {
        String orderType = parts[1];
        int size = Integer.parseInt(parts[2]);

        if (orderType.equals("buy")) {
            removeShare(size, asks);
        } else if (orderType.equals("sell")) {
            removeShare(size, bids);
        }
    }

    private int bestPrice(HashMap<Integer, Integer> map) {
        int best = Integer.MAX_VALUE;

        for (Integer price : map.keySet()) {
            if (price < best) {
                best = price;
            }
        }

        return best;
    }

    private void removeShare(int size, HashMap<Integer, Integer> map) {
        int remainingSize = size;

        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            int price = entry.getKey();
            int currentSize = entry.getValue();

            if (currentSize >= remainingSize) {
                map.put(price, currentSize - remainingSize);
                break;
            } else {
                map.remove(price);
                remainingSize -= currentSize;
            }
        }
    }
}
