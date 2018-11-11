package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

//TODO: Await new functions/classes to complete openSession connectfunction calls

public class ClientMain {

    /*
    static public ArrayList<String> availCommands;
    static
    {
        availCommands.add("users");
        availCommands.add("invite");
        availCommands.add("quit");

    }
*/
    public static void openSession(ClientMain.User User, ObjectOutputStream toServer, ObjectInputStream fromServer, Scanner in)
    {
        /*
        toServer.writeObject(new gameConnect(User.username));
        gameConnectResult currgameConnectResult = (gameConnectResult)fromServer.readObject();
        if (!(currgameConnectResult.Error()))
        {
            System.out.println(User.username + " connected to server and online!");
            User.connected = true;

        }
        else
            System.out.println("Failed to connect to server!");
        */

    }

    public static void Session(Socket userSocket, String username)
    {
        try
        {
            Scanner clientWindow = new Scanner(System.in);
            try
            {
                ObjectInputStream fromServer = new ObjectInputStream(userSocket.getInputStream());
                try
                {
                    ObjectOutputStream toServer = new ObjectOutputStream(userSocket.getOutputStream());
                    ClientMain.User user = new User(username);
                    openSession(user,toServer,fromServer,clientWindow);


                }
                catch (Exception e4)
                {
                    System.out.println(e4.getMessage());
                }
            }
            catch (Exception e3)
            {
                System.out.println(e3.getMessage());
            }
        }
        catch(Exception e2)
        {
            System.out.println(e2.getMessage());
        }
    }


    public static void main(String[] args)
    {
        if ( !(args.length <=2 && args.length >= 1 ) )
        {
            System.out.println("Invalid Arguments!");
            System.out.println("<username> [address] ");
            System.exit(-1);
        }
        try
        {
            if (args.length == 1)
            {
                Socket userSocket = new Socket (InetAddress.getLocalHost(), 1111);
                System.out.println("Creating Session in: "
                + InetAddress.getLocalHost() + ":" + 1111 + " for " + args[0]
                );
                Session(userSocket,args[0]);
            }
            if (args.length == 2)
            {
                Socket userSocket = new Socket (args[1], 1111);
                System.out.println("Creating Session in: "
                        + args[1] + ":" + 1111 + " for " + args[0]
                );
                Session(userSocket,args[0]);
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());

        }

    }

    static class User
    {
        String username;
        boolean isConnected = false;
        public User(String username) {
            this.username = username;
        }

    }


}
