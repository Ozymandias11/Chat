import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer {
    private ServerSocket serversocket;

    public ChatServer(ServerSocket serversocket) {
        this.serversocket = serversocket;
    }

    public void serverStarter()  {
        System.out.println(LocalTime.now() + " server is waiting on a port " + serversocket.getLocalPort());
        try {
            while (!serversocket.isClosed()) {
                Socket socket = serversocket.accept();
                ChatClient chatClient = new ChatClient(socket);
                Thread thread = new Thread(chatClient);
                thread.start();
            }
        } catch (IOException e) {
            serverCloser();
        }


    }
    private void serverCloser() {
        try{
            if(serversocket != null)
                serversocket.close();
        }catch (IOException e) {
            System.out.println("LOGIN FAILED");
        }
    }


    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        ChatServer chatServer = new ChatServer(serverSocket);
        chatServer.serverStarter();

    }

}
