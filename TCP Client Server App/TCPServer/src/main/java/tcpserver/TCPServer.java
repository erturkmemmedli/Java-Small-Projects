package tcpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        readAsByte();
    }

    public static void readAsByte() throws Exception {
        int bufferSize = 8192;
        ServerSocket ourFirstServerSocket = new ServerSocket(6789);
        while (true) {
            System.out.println("I am waiting for message from new client");
            Socket connection = ourFirstServerSocket.accept();
            System.out.println("Cool, we have a new client");
            DataInputStream dataStream = new DataInputStream(connection.getInputStream());
            byte[] arr = readMessage(dataStream);
            FileUtility.writeBytes("C:/Users/Erturk Memmedli/Documents/Workspace/test.jpg", arr);
        }
    }

    public static byte[] readMessage(DataInputStream din) throws Exception {
        int msgLen = din.readInt();
        byte[] msg = new byte[msgLen];
        din.readFully(msg);
        return msg;
    }

    public static void readAsString() throws Exception {
        ServerSocket ourFirstServerSocket = new ServerSocket(6789);
        while (true) {
            System.out.println("I am waiting for message from new client");
            Socket connection = ourFirstServerSocket.accept();
            System.out.println("Cool, we have a new client");
            InputStream is = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bReader = new BufferedReader(reader);
            String messageFromClient = bReader.readLine();
            System.out.println("The client said: " + messageFromClient);
        }
    }
}