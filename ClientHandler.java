/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
/**
 *
 * @author Tarık BALCI
 */
public class ClientHandler implements Runnable {
    
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String istemcikullaniciAdi;
    
    public ClientHandler(Socket socket) {
    
    try {
    this.socket = socket;
    this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    this.istemcikullaniciAdi = bufferedReader.readLine();
    clientHandlers.add(this);
    mesajakisi("Sunucu : " + istemcikullaniciAdi + " sohbete katıldı");
    } catch (IOException e) {
        kapa(socket, bufferedReader, bufferedWriter);
    } 
    }
    
    
    @Override
    public void run() {
    
    String istemcimesaji;

    while (socket.isConnected()) {
    
    try {
     
    istemcimesaji = bufferedReader.readLine();
    mesajakisi(istemcimesaji);
    } catch (IOException e) {
    
    kapa(socket, bufferedReader, bufferedWriter);
    break;
    }
}

}

        public void mesajakisi(String gidenmesaj) {
        
        for (ClientHandler clientHandler : clientHandlers) {
        
        try {
        
        if (!clientHandler.istemcikullaniciAdi.equals(istemcikullaniciAdi)); {
        clientHandler.bufferedWriter.write(gidenmesaj);
        clientHandler.bufferedWriter.newLine();
        clientHandler.bufferedWriter.flush();
             
        }
        
        } catch (IOException e) {
        kapa(socket, bufferedReader, bufferedWriter);
        
        }
        }
        }
        public void isleyicikaldir() {
        clientHandlers.remove(this);
        mesajakisi("Sunucu : " + istemcikullaniciAdi + "sohbetten ayrıldı");
        }
                
        public void kapa(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        isleyicikaldir();
        try {
        if (bufferedReader !=null) {
        bufferedReader.close();
        }
        if (bufferedWriter!=null) {
        bufferedWriter.close();
        }
        if (socket !=null) {
        socket.close();
        }
        } catch (IOException e) {
        e.printStackTrace();
        }
        
        }        
            }

        
        
        
        
     
