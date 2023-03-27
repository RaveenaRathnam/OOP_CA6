package com.dkit.oop.sd2.BusinessObjects;
//
///** OOP Feb 2022
// * This App demonstrates the use of a Data Access Object (DAO)
// * to separate Business logic from Database specific logic.
// * It uses Data Access Objects (DAOs),
// * Data Transfer Objects (DTOs), and  a DAO Interface to define
// * a contract between Business Objects and DAOs.
// *
// * "Use a Data Access Object (DAO) to abstract and encapsulate all
// * access to the data source. The DAO manages the connection with
// * the data source to obtain and store data" Ref: oracle.com
// *
// * Here, we use one DAO per database table.
// *
// * Use the SQL script "mysqlSetup.sql" included with this project
// * to create the required MySQL user_database and User table.
// */
//
import com.dkit.oop.sd2.DAOs.MySqlArtistDao;
import com.dkit.oop.sd2.DAOs.ArtistDaoInterface;
import com.dkit.oop.sd2.DTOs.Artist;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;
import java.util.Scanner;

//
public class App {
    private static final Scanner keyboard = new Scanner(System.in);
    private static final ArtistDaoInterface IArtistDao = new MySqlArtistDao();//"IUserDao" -> "I" stands for for
    public static void main(String[] args) {


//        // Notice that the userDao reference is an Interface type.
//        // This allows for the use of different concrete implementations.
//        // e.g. we could replace the MySqlUserDao with an OracleUserDao
//        // (accessing an Oracle Database)
//        // without changing anything in the Interface.
//        // If the Interface doesn't change, then none of the
//        // code in this file that uses the interface needs to change.
//        // The 'contract' defined by the interface will not be broken.
//        // This means that this code is 'independent' of the code
//        // used to access the database. (Reduced coupling).

//        // The Business Objects require that all User DAOs implement
//        // the interface called "UserDaoInterface", as the code uses
//        // only references of the interface type to access the DAO methods.

        try {
            boolean exit = false;

            String userInput;
            while (!exit) {
                printMainMenuInstructions();
                int choice = keyboard.nextInt();
                switch (choice) {
                    case 1:
                         displayAllArtists();
                        break;
                    case 2:
                         findArtistById();
                        break;
                    case 3:
                         deleteArtistById();
                        break;
                    case 4:
                          insertArtist();
                        break;
                    case 0:
                        System.out.println("Exiting the app...");
                        exit = true;
                        break;
                    default:
                        System.out.println("\nInvalid Input. Try again.");
                        break;

                }
            }
        }
        catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private static void insertArtist()throws DaoException {
        keyboard.nextLine();
        System.out.println("\nCall insertartist()");
        System.out.println("Please enter the details of the artist:");
        System.out.println("Name:");
        String name =keyboard.nextLine();
        System.out.println("Country:");
        String country =keyboard.nextLine();
        System.out.println("Genre:");
        String genre =keyboard.nextLine();
        System.out.println("Active Since:");
        int active_since =keyboard.nextInt();
        keyboard.nextLine();
        System.out.println("Biography:");
        String biography =keyboard.nextLine();
        System.out.println("Rating out of 5:");
        double rating =keyboard.nextDouble();
        Artist a=new Artist(name,country,genre,active_since,biography,rating);
        Artist artist = IArtistDao.insertArtist(a);     // call a method in the DAO
        if( artist!=null)
            System.out.println("Artist added!"+artist.toString());
        else {
                System.out.println("Artist not added!");
        }
    }

    private static void deleteArtistById() throws DaoException{
            // test dao - with username and password that we know are present in the database
            System.out.println("\nCall: deleteArtistById()");
           System.out.println("Please enter the Id of the Artist: ");
             int artistId=keyboard.nextInt();
            boolean result= IArtistDao.deleteArtistById(artistId);
            if( result == true) // null returned if userid and password not valid
                System.out.println("Artist has been successfully deleted");
            else
                System.out.println("Artist with the id: "+artistId+" not deleted as it doesn't exist.");
    }

    private static void findArtistById() throws DaoException{
        // test dao - with username and password that we know are present in the database
        System.out.println("\nCall: findArtistById()");
        System.out.println("Please enter the Id of the Artist: ");
            int artistId=keyboard.nextInt();
            Artist artist = IArtistDao.findArtistById(artistId);
            if( artist != null ) // null returned if userid and password not valid
                System.out.println("Artist found: " + artist);
            else
                System.out.println("Artist with the id: "+artist+" not found");
    }

    private static void displayAllArtists() throws DaoException{
        System.out.println("\nCall findAllArtists()");
            List<Artist> artists = IArtistDao.findAllArtists();     // call a method in the DAO

            if( artists.isEmpty() )
                System.out.println("There are no Artists");
            else {
                for (Artist artist : artists)
                    System.out.println("Artist: " + artist.toString());
            }
        System.out.println("");
    }

    private static void printMainMenuInstructions() {
        System.out.println("======================================  WELCOME ! ==============================");
        System.out.println("|   ----------------------------------    MENU   ----------------------------  |");
        System.out.println("|   |                          Please Select an option:                     |  |");
        System.out.println("|   |                                0. Exit App                            |  |");
        System.out.println("|   |                          1. Display All Artists                       |  |");
        System.out.println("|   |                          2. Dispaly Artist By Id                      |  |");
        System.out.println("|   |                          3. Delete Artist By Id                       |  |");
        System.out.println("|   |                               4. Add Artist                           |  |");
        System.out.println("|   -------------------------------------------------------------------------  |");
        System.out.println("================================================================================");
    }
}
