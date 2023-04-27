package com.dkit.oop.sd2.Client;


/** CLIENT                                                  March 2021
 *
 * This Client program asks the user to input commands to be sent to the server.
 *
 * There are only two valid commands in the protocol: "Time" and "Echo"
 *
 * If user types "Time" the server should reply with the current server time.
 *
 * If the user types "Echo" followed by a message, the server will echo back the message.
 * e.g. "Echo Nice to meet you"
 *
 * If the user enters any other input, the server will not understand, and
 * will send back a message to the effect.
 *
 * NOte: You must run the server before running this the client.
 * (Both the server and the client will be running together on this computer)
 */

import com.dkit.oop.sd2.BusinessObjects.JsonDeserializerArtist;
import com.dkit.oop.sd2.DTOs.Artist;
import com.dkit.oop.sd2.Exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    // Instantiate a GsonBuilder and register the TypeAdapter (to adapt types!)
    // passing in the IssPositionAtTime class definition,
    // the name of the deserialization object (containing the deserialize() method)
    //
    private static Gson gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(Artist.class, new JsonDeserializerArtist())
            .serializeNulls()
            .create();
 public static void main(String[] args)
        {
            Client client = new Client();
            client.start();
        }

        public void start()
        {
            Gson gsonParser = new Gson();
            Scanner in = new Scanner(System.in);
            try {
                Socket socket = new Socket("192.168.83.13", 8080);  // connect to server socket
                System.out.println("Client: Port# of this client : " + socket.getLocalPort());
                System.out.println("Client: Port# of Server :" + socket.getPort() );

                System.out.println("Client message: The Client is running and has connected to the server");

                printMenuInstructions();
                String command = in.nextLine();

                OutputStream os = socket.getOutputStream();
                PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

//                socketWriter.println(command);
                if(command.startsWith("1"))   //we expect the server to return a time
                {
                    System.out.println("Please enter the Id of the Artist: ");
                    int artistId=in.nextInt();
                    socketWriter.println(command+" "+artistId);
               }
                else if (command.startsWith("2")) {
                    socketWriter.println(command);
                }
                else if (command.startsWith("3")) {
                    System.out.println("Please enter the details of the artist:");
                    System.out.println("Name:");
                    String name =in.nextLine();
                    System.out.println("Country:");
                    String country =in.nextLine();
                    System.out.println("Genre:");
                    String genre =in.nextLine();
                    System.out.println("Active Since:");
                    int active_since =in.nextInt();
                    in.nextLine();
                    System.out.println("Biography:");
                    String biography =in.nextLine();
                    System.out.println("Rating out of 5:");
                    double rating =in.nextDouble();
                    Artist a=new Artist(name,country,genre,active_since,biography,rating);
                    String artistJson= gsonParser.toJson(a);
                    socketWriter.println(command+" "+artistJson);
                }
                Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply

                if(command.startsWith("1"))   //we expect the server to return a time
                {
                    String artistByIdJson = socketReader.nextLine();

                    if(artistByIdJson.startsWith("error")) {
                        System.out.println(artistByIdJson);
                    }
                    else {
                        // Now that we have set up the Adapter, we call the fromJson() method
                        // to parse the JSON string and create and populate
                        // the Java IssPositionAtTime object.
                        //
                        Artist artist = gsonBuilder.fromJson(artistByIdJson, new TypeToken<Artist>() {
                        }.getType());
                        System.out.println("Client message: Displaying Artist By ID: " + artist);
                    }
                }
                else if(command.startsWith("2"))
                {
                    String artistsStringJson= socketReader.nextLine();
                    if(artistsStringJson.startsWith("error")) {
                        System.out.println(artistsStringJson);
                    }
                    else {
                        System.out.println("Client message: Displaying All Artists: ");
                        // Now that we have set up the Adapter, we call the fromJson() method
                        // to parse the JSON string and create and populate
                        // the Java IssPositionAtTime object.
                        //
                        Artist[] artists = gsonBuilder.fromJson(artistsStringJson, Artist[].class);
                        List<Artist> artistList = new ArrayList<>(Arrays.asList(artists));
                        for(Artist a:artistList){
                            System.out.println(a);
                    }

                    }

                }
                else if(command.startsWith("3"))
                {
                    String message=socketReader.nextLine();
                    System.out.println("Client message: "+message);
                }
                else                            // the user has entered the Echo command or an invalid command
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }

                socketWriter.close();
                socketReader.close();
                socket.close();

            } catch (IOException e) {
                System.out.println("Client message: IOException: "+e);
            }
//            catch (DaoException e) {
//                throw new RuntimeException(e);
//            }
        }
    private static void printMenuInstructions() {
        System.out.println("======================================  WELCOME ! ==============================");
        System.out.println("|   ----------------------------------    MENU   ----------------------------  |");
        System.out.println("|   |                          Please Select an option:                     |  |");
        System.out.println("|   |                                0. Exit App                            |  |");
        System.out.println("|   |                          1. Dispaly Artist By Id                      |  |");
        System.out.println("|   |                          2. Display All Artists                       |  |");
        System.out.println("|   |                          3. Add Artist                                |  |");
        System.out.println("|   |                          4. Delete Artist By Id                       |  |");
        System.out.println("|   -------------------------------------------------------------------------  |");
        System.out.println("================================================================================");
    }



}


//  LocalTime time = LocalTime.parse(timeString); // Parse timeString -> convert to LocalTime object if required

