

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Tarık BALCI
 */
public class Server {

    private ServerSocket serverSocket;
    
    public Server(ServerSocket serverSocket) {
    this.serverSocket = serverSocket;
}
    
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

            }
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
        ServerSocket serverSocket = new ServerSocket(3000);
        Server server = new Server(serverSocket);
        server.sunucuyuBaslat();
    }
}
