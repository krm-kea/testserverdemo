/**
 * 2018.11.12   -   Kris
 *
 * Example TCP Server
 * Minimal function for demo in class.
 * You need to fix it to work for chat exercise.
 *
 */

import java.io.*;
import java.net.*;

public class TCPServer_demo
{
    public static void main(String argv[]) throws Exception
    {
        System.out.println("starting main");
        String sentence;
        String userText;
        boolean go_on = true;
        int counter = 0;

        ServerSocket welcomeSocket = new ServerSocket(5656);
        System.out.println("we have a socket");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        while(go_on)
        {
            counter++;
            System.out.println("waiting for a connection");
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            readAllLines(inFromClient);

            System.out.print("I'm sending back to client: Hum hum, foo,...");
            outToClient.writeBytes("HTTP/1.0 200 Alt er OK\n");
            outToClient.writeBytes("Server: Kris demo server\r\n\r\n");
            outToClient.writeBytes("Hum hum, foo, bar,... and bla bla. counter = " + counter);

            System.out.println("Quitting the loop and closing the socket to client");
            connectionSocket.close();
        }

        System.out.println("Quitting the Server and closing the main socket!!!!!!!!!!!!!!!**************");
        welcomeSocket.close();
    }


    static private void readAllLines(BufferedReader ifc) throws Exception
    {
        String line = "dummy";
        BufferedReader inFromClient = ifc;
        while (!line.equals(""))
        {
            line = inFromClient.readLine();
            System.out.println("FROM CLIENT: " + line);
        }

    }


}
