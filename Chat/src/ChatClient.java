import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ChatClient implements Runnable {

    public static ArrayList<ChatClient> clients = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    public String userName;
    private  LocalTime time = LocalTime.now();
    private final LocalTime time1 = time;



    public ChatClient(Socket socket) throws IOException {
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = bufferedReader.readLine();
            clients.add(this);

            broadcastMessage(  this.userName + " has joined the chat"); // message to other group members
            System.out.println(time + " *** " + this.userName + " has joined the chat  *** "); // message on the server
            System.out.println(LocalTime.now()  + " Server is waiting on port " + socket.getLocalPort());



        }catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    @Override
    public void run() {
        String messagesFromClients;
        while(socket.isConnected()){
            try{
                messagesFromClients = bufferedReader.readLine();
                broadcastMessage(messagesFromClients);

            } catch (IOException e) {
                try {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    public void broadcastMessage(String messageToConsole) throws IOException {

        if(messageToConsole.equals("LOGOUT")){
            clients.remove(this);
            for (ChatClient clients : clients) {
                try {
                    if (!clients.userName.equals(this.userName)) {
                        clients.bufferedWriter.write(this.userName + " has left a chat");
                        clients.bufferedWriter.newLine();
                        clients.bufferedWriter.flush();
                    }

                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
            closeEverything(socket, bufferedReader, bufferedWriter);
            System.out.println(this.userName + " has left the chat");


        }else if(messageToConsole.equals("WHOIS")){
            int i = 1;
            for(ChatClient client : clients){
                this.bufferedWriter.write(i + ")" + client.userName + " Since: " +  client.time1);
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
                i++;
            }


        }else if(messageToConsole.equals("PINGU")){
            String[] facts = {"Penguins don’t have teeth",
                    "Penguins are only found in the Southern Hemisphere",
                    "There are 18 species of penguin",
                    "Penguins originated in Australia",
                    "The smallest penguin is only a foot tall"
            };
            Random random = new Random();
            int randomIndex = random.nextInt(facts.length);
            for (ChatClient clients : clients) {
                try {
                    clients.bufferedWriter.write(facts[randomIndex]);
                    clients.bufferedWriter.newLine();
                    clients.bufferedWriter.flush();


                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }
        else if(messageToConsole.contains("has joined the chat")){
            for (ChatClient clients : clients) {
                try {
                    if (!clients.userName.equals(this.userName)) {
                        clients.bufferedWriter.write(this.userName + " has joined the chat");
                        clients.bufferedWriter.newLine();
                        clients.bufferedWriter.flush();
                    }

                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }else if(messageToConsole.startsWith("@")){
            String userToSendMessage = messageToConsole.substring(1, messageToConsole.indexOf(' '));
            int count = 0;
            for(ChatClient chatClient : clients){
                if(userToSendMessage.equals(chatClient.userName)){
                    String privateMessage = messageToConsole.substring(messageToConsole.indexOf(' ') + 1);
                    chatClient.bufferedWriter.write(this.userName + ": " +  privateMessage);
                    chatClient.bufferedWriter.newLine();
                    chatClient.bufferedWriter.flush();

                    this.bufferedWriter.write("Me to " + userToSendMessage + ": " + privateMessage);
                    this.bufferedWriter.newLine();
                    this.bufferedWriter.flush();
                }else
                    count++;
            }
            if(count == clients.size()){
                this.bufferedWriter.write("Client with this bame does not exists in this group");
                this.bufferedWriter.newLine();
                this.bufferedWriter.flush();
            }
        }
        else{
            messageSender(messageToConsole);
        }

    }

    public void messageSender(String message) throws IOException {
        for (ChatClient clients : clients) {
            try {
                if (!clients.userName.equals(this.userName)) {
                    clients.bufferedWriter.write(this.userName + ": " +  message);
                    clients.bufferedWriter.newLine();
                    clients.bufferedWriter.flush();
                }else{
                    clients.bufferedWriter.write( "Me: " +  message);
                    clients.bufferedWriter.newLine();
                    clients.bufferedWriter.flush();
                }

            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
        System.out.println(this.userName + ": " +  message); // message to be displayed on a server
    }





    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) throws IOException {
        try{
            if(socket != null)
                socket.close();
            if(bufferedReader != null)
                bufferedReader.close();
            if(bufferedWriter != null)
                bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
