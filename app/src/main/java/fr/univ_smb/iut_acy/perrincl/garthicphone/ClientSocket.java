package fr.univ_smb.iut_acy.perrincl.garthicphone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSocket extends Thread {

    private final static String TAG = ClientSocket.class.getName();
    private String message;
    private SharedPreferences sp;

    public ClientSocket(SharedPreferences sp , String message) {
        this.sp = sp;
        this.message = message;
        this.start();
    }

    @Override
    public void run() {
        Socket socket = new Socket();
        try {
            this.connectToServer(socket);
            this.write(socket, message.getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToServer(Socket socket) throws IOException {
        socket.connect(new InetSocketAddress(this.sp.getString("ip", ""), Integer.parseInt(this.sp.getString("port", "3245"))));
    }

    public void write(Socket socket, byte[] request) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(request);
        outputStream.flush();
        outputStream.close();
    }
}
