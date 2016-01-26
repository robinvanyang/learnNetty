package name.redbud.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by robin on 16/1/13.
 */
public class TimeServer {
    public static void main(String[] args) throws IOException{
        int port = 8080;

        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("The Time Server is start in port: " + port);
            Socket socket = null;

            while (true) {
                socket = server.accept();

            }

        } finally {
            if (server != null) {
                System.out.println("The Time Server close");
                server.close();
                server = null;
            }
        }
    }
}
