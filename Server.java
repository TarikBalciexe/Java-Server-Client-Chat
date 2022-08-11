

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Tarık BALCI
 */
public class Server {
    
    String mesaj;
    private DataInputStream input;
    private DataOutputStream output;
    private ServerSocket serverSocket;
    
    public Server(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
}
    //methodlar
    //sunucu başlatan method
    public void sunucuyuBaslat() {
        try {
            
            while (!serverSocket.isClosed()) {
               
                Socket socket = serverSocket.accept();
                System.out.println("Yeni katılımcı bağlandı");
                ClientHandler clientHandler = new ClientHandler(socket);
                
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }
        catch (IOException e) {
        }

            }//bağlantıyı kapatan method
    public void baglantiyiKapat() {
        try {
            if (serverSocket !=null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
     
        
    public static void main(String[] args) throws IOException {
    try 
    { ServerSocket serverSocket = new ServerSocket(3000);
        Server server = new Server(serverSocket);
        server.sunucuyuBaslat();
        
      
    }
    catch (IOException e) {
         
         System.out.println("Connection Refused Exception: " + e);
         
   
        }
    }
}
