package tcpclient;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws Exception {
        sendImageFile();
    }

    public static void sendTextMessage() throws Exception {
        Socket newSocket = new Socket("localhost", 6789);
        OutputStream os = newSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.write("Salam server, necesen?".getBytes());
        newSocket.close();
    }

    public static void sendImageFile() throws Exception {
        Socket newSocket = new Socket("localhost", 6789);
        OutputStream os = newSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        byte[] bytes = FileUtility.readBytes("C:/Users/Erturk Memmedli/Documents/Workspace/sauron.jpg");
        dos.writeInt(bytes.length);
        dos.write(bytes);
        newSocket.close();
    }
}
