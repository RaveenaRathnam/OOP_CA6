package com.dkit.oop.sd2.Server;


/**
 * SERVER  - MULTITHREADED                                         March 2021
 * <p>
 * Server accepts client connections, creates a ClientHandler to handle the
 * Client communication, creates a socket and passes the socket to the handler,
 * runs the handler in a separate Thread.
 * <p>
 * <p>
 * The handler reads requests from clients, and sends replies to clients, all in
 * accordance with the rules of the protocol. as specified in
 * "ClientServerBasic" sample program
 * <p>
 * The following PROTOCOL is implemented:
 * <p>
 * If ( the Server receives the request "Time", from a Client ) then : the
 * server will send back the current time
 * <p>
 * If ( the Server receives the request "Echo message", from a Client ) then :
 * the server will send back the message
 * <p>
 * If ( the Server receives the request it does not recognize ) then : the
 * server will send back the message "Sorry, I don't understand"
 * <p>
 * This is an example of a simple protocol, where the server's response is based
 * on the client's request.
 *
 *  Each client is handled by a ClientHandler running in a separate worker Thread
 *  which allows the Server to continually listen for and handle multiple clients
 */


import com.dkit.oop.sd2.BusinessObjects.JsonDeserializerArtist;
import com.dkit.oop.sd2.DAOs.ArtistDaoInterface;
import com.dkit.oop.sd2.DAOs.MySqlArtistDao;
import com.dkit.oop.sd2.DTOs.Artist;
import com.dkit.oop.sd2.Exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Server
{
    // Instantiate a GsonBuilder and register the TypeAdapter (to adapt types!)
    // passing in the IssPositionAtTime class definition,
    // the name of the deserialization object (containing the deserialize() method)
    //
    private static Gson gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(Artist.class, new JsonDeserializerArtist())
            .serializeNulls()
            .create();
    private static final Scanner keyboard = new Scanner(System.in);
    private static final ArtistDaoInterface IArtistDao = new MySqlArtistDao();//"IUserDao" -> "I" stands for for
    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            String message;
            Gson gsonParser = new Gson();
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                    if (message.startsWith("1"))
                    {
                        int artistId=Integer.parseInt(message.substring(2));
                        String artist = IArtistDao.findArtistByIdJson(artistId);
                        if( artist != null ) // null returned if userid and password not valid
                        {
                            socketWriter.println(artist);
                        }//sends the artist by id to the client
                        else
                        {
                            String error="error : Artist with id : "+artistId+" not found!";
                            socketWriter.println(error);
                        }
                    }
                    else if (message.startsWith("2"))
                    {
                        String artists = IArtistDao.findAllArtistsJson();     // call a method in the DAO
                        if( artists != null ) // null returned if userid and password not valid
                        {
                            socketWriter.println(artists);  // send message to client
                        }//sends the artist by id to the client
                        else
                        {
                            String error="error : No artists found!";
                            socketWriter.println(error);
                        }
                    }
                    else if (message.startsWith("3"))
                    {
                        String artistJson=message.substring(2);
                        Artist a =  gsonBuilder.fromJson(artistJson, new TypeToken<Artist>(){}.getType());
                        boolean inserted=IArtistDao.insertArtist(a);
                        if(inserted==true) {
                            socketWriter.println("Insert completed");
                        }
                        else {
                            socketWriter.println("Insert failed!");
                        }

                    }
                    else if (message.startsWith("4"))
                    {
                        int artistId=Integer.parseInt(message.substring(2));
                        boolean deleted=IArtistDao.deleteArtistById(artistId);
                        if(deleted==true) {
                            socketWriter.println("Deleted artist with id : "+artistId);
                        }
                        else {
                            socketWriter.println("Deleting failed!");
                        }

                    }
                    else
                    {
                        socketWriter.println("I'm sorry I don't understand :(");
                    }
                }

                socket.close();

            } catch (IOException ex)
            {
                ex.printStackTrace();
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

}
