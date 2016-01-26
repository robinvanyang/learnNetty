package name.redbud.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by robin on 16/1/13.
 */
public class TimeServerHandler implements Runnable{

    private Socket mSocket;

    public TimeServerHandler(Socket mSocket) {
        this.mSocket = mSocket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));

            out = new PrintWriter(mSocket.getOutputStream(), true);

            String currentTime = null;
            String body = null;

            while (true) {
                body = in.readLine();

                if (body == null)
                    break;

                System.out.println("Time Server receive order: " + body);

                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                        new Date(System.currentTimeMillis()).toString(): "BAD QUERY";

                out.println(currentTime);
            }

        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            if (out != null) {
                out.close();
                out = null;
            }
        }
    }
}
