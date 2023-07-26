import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;

    public client(String userName, Socket socket) throws IOException {
        try{
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = userName;

        } catch (IOException e) {
            socket.close();
            bufferedReader.close();
            bufferedWriter.close();
        }
    }
    public void sendMessage() throws IOException {
        try{
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while(socket.isConnected()){
                String message = scanner.nextLine();
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                if(message.equals("LOGOUT")){
                    socket.close();
                    bufferedReader.close();
                    bufferedWriter.close();
                    break;
                }

            }

        } catch (IOException e) {
            socket.close();
            bufferedReader.close();
            bufferedWriter.close();
        }
    }
    public void listenMessage(){
        new Thread(() -> {
            String messageGroupChat;
            while (socket.isConnected()){
                try{
                    messageGroupChat = bufferedReader.readLine();
                    System.out.println(messageGroupChat);
                    if(messageGroupChat.equals("LOGOUT")){
                        socket.close();
                        bufferedReader.close();
                        bufferedWriter.close();
                        break;
                    }
                }catch(IOException e){
                    //
                }
            }
        }).start();
    }
    public static void instructionSender(){
        System.out.println("Hello! Welcome to the chatroom!\n" +
                "Instructions:\n" +
                " - Simply type the message to send broadcast to all active clients\n" +
                " - Type '@username<space>yourmessage' without quotes to send message to desired client\n" +
                " - Type 'WHOIS' without quotes to see list of active clients\n" +
                " - Type 'LOGOUT' without quotes to logoff from server\n" +
                " - Type 'PINGU' without quotes to request a random penguin fact");
    }

    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter you name: ");
        String userName1 = scanner.nextLine();
        Socket socket1 = new Socket("localhost", 3000);
        client client = new client(userName1, socket1);
        instructionSender();
        client.listenMessage();
        client.sendMessage();




    }


}