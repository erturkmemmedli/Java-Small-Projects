import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OrderBook orderBook = new OrderBook();
        String inputFilepath = "C:/Users/Erturk Memmedli/Documents/Workspace/OrderBook/input.txt";
        String outputFilepath = "C:/Users/Erturk Memmedli/Documents/Workspace/OrderBook/output.txt";
        orderBook.input(inputFilepath, outputFilepath);
    }
}