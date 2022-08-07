/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.imageio.IIOException;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author Tarık BALCI
 */
public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String kullaniciAdi;
    
    public Client(Socket socket, String kullaniciAdi) {
    try {
    this.socket = socket;
    this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    this.kullaniciAdi = kullaniciAdi;
    }
    catch (IOException e) {
    kapa(socket, bufferedReader, bufferedWriter);
    }
    }
    public void mesajgonder() {
    try {
    bufferedWriter.write(kullaniciAdi);
    bufferedWriter.newLine();
    bufferedWriter.flush();
     Scanner scanner = new Scanner(System.in);
     while (socket.isConnected()) {
     String gidenmesaj = scanner.nextLine();
     bufferedWriter.write(kullaniciAdi + ": " + gidenmesaj);
     bufferedWriter.newLine();
     bufferedWriter.flush();
     }
    } catch (IOException e) {
    kapa(socket, bufferedReader, bufferedWriter);
    } 
    }
    private void mesajidinle() {
    new Thread(new Runnable() {
        @Override
        public void run() {
         String chatmesaji;
         
         while (socket.isConnected()) {
         try {
         chatmesaji = bufferedReader.readLine();
         System.out.println(chatmesaji);
         }
          catch (IOException e) {
                 
                 kapa(socket, bufferedReader, bufferedWriter);
                 }
         }
         
        }
    
    }).start(); 
}
        public void kapa(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {

        try {
        if (bufferedReader !=null) {
        bufferedReader.close();
}
        if (bufferedWriter !=null) {
        bufferedWriter.close();
}
        if (socket !=null) {
        socket.close();
}

}
        catch (IOException e) {
        e.printStackTrace();
}
    }

    public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Kullanıcı Adı Girin : ");
    String kullaniciAdi = scanner.nextLine();
    Socket socket = new Socket("localhost", 1234);
    Client client = new Client(socket, kullaniciAdi);
    client.mesajidinle();
    client.mesajgonder();
}
    }

