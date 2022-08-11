
import java.io.*;
import java.net.*;
import java.util.*;

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
     String gidenMesaj = scanner.nextLine();
     bufferedWriter.write(kullaniciAdi + ": " + gidenMesaj);
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
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        
       try {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Kullanıcı Adı Girin : ");
    String kullaniciAdi = scanner.nextLine();
    Socket socket = new Socket("localhost", 3000);
    Client client = new Client(socket, kullaniciAdi);
   
             client.mesajidinle();
             client.mesajgonder();
        
                 }
                 catch (IOException e) {
         
         System.out.println("Connection Refused Exception: " + e);
         
    
       
}
    }
}


