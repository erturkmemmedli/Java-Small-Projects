package tcpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws Exception {
        readRequestAndWriteResponse();
    }

    public static void readRequestAndWriteResponse() throws Exception {
        int bufferSize = 8192;
        ServerSocket ourFirstServerSocket = new ServerSocket(6789);
        while (true) {
            Socket connection = ourFirstServerSocket.accept();
            DataInputStream dataStream = new DataInputStream(connection.getInputStream());
            String result = readRequest(dataStream);
            System.out.println(result);
            OutputStream os = connection.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            String message = "Bir dusun bu menzereni. Doyur qapini pencereni. Keder seher, hicran her axsam";
            writeResponse(dos, message);
            connection.close();
        }
    }

    public static String readRequest(InputStream is) throws Exception {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br  = new BufferedReader(isr);
        String message = "";
        while (true) {
            String s = br.readLine();
            if (s == null || s.trim().length() == 0) break;
            else message += s + "\r\n";
            System.out.println(s);
        }
        return message;
    }

    public static void writeResponse(OutputStream dos, String s) throws Exception {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Server: YarServer 2009-09-09\r\n"
                + "Content-Type: text/html\r\n"
                + "Content-Length: " + s.length() + "\r\n"
                + "Connection: close\r\n\r\n";
        String result = response + s;
        dos.write(result.getBytes());
        dos.flush();
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