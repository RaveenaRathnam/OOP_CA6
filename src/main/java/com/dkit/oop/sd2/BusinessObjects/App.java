package com.dkit.oop.sd2.BusinessObjects;

/** OOP Feb 2022
 * This App demonstrates the use of a Data Access Object (DAO)
 * to separate Business logic from Database specific logic.
 * It uses Data Access Objects (DAOs),
 * Data Transfer Objects (DTOs), and  a DAO Interface to define
 * a contract between Business Objects and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here, we use one DAO per database table.
 *
 * Use the SQL script "mysqlSetup.sql" included with this project
 * to create the required MySQL user_database and User table.
 */

import com.dkit.oop.sd2.DAOs.MySqlArtistDao;
import com.dkit.oop.sd2.DAOs.ArtistDaoInterface;
import com.dkit.oop.sd2.DTOs.Artist;
import com.dkit.oop.sd2.Exceptions.DaoException;
import java.util.List;

public class App
{
    public static void main(String[] args)
    {
        ArtistDaoInterface IArtistDao = new MySqlArtistDao();  //"IUserDao" -> "I" stands for for

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
//
//        // The Business Objects require that all User DAOs implement
//        // the interface called "UserDaoInterface", as the code uses
//        // only references of the interface type to access the DAO methods.

        try
        {
            //=====================================================================================
            System.out.println("\nCall findAllArtists()");
            List<Artist> artists = IArtistDao.findAllArtists();     // call a method in the DAO

            if( artists.isEmpty() )
                System.out.println("There are no Artists");
            else {
                for (Artist artist : artists)
                    System.out.println("Artist: " + artist.toString());
            }
//==============================================================================================================================
            // test dao - with username and password that we know are present in the database
            System.out.println("\nCall: findArtistById()");
            int artistId=1;
            Artist artist = IArtistDao.findArtistById(artistId);

            if( artist != null ) // null returned if userid and password not valid
                System.out.println("Artist found: " + artist);
            else
                System.out.println("Artist with the id: "+artist+" not found");
//===============================================================================================
//            // test dao - with an invalid username (i.e. not in database)
//            username = "madmax";
//            password = "thunderdome";
//            artist = IUserDao.findUserByUsernamePassword(username, password);
//            if(artist != null)
//                System.out.println("Username: " + username + " was found: " + artist);
//            else
//                System.out.println("Username: " + username + ", password: " + password +" is not valid.");
//
//            //test-class findAllUsersLastNameContains()
//            System.out.println("\nCall findAllUsersLastNameContains()");
//            String substring="S";
//            List<Artist> usersContainln = IUserDao.findAllUsersLastNameContains(substring);     // call a method in the DAO
//
//            if( artists.isEmpty() )
//                System.out.println("There are no Users");
//            else {
//                for (Artist artist1 : usersContainln)
//                    System.out.println("User: " + artist1.toString());
//            }
//
//            //test-class addUser()
//            System.out.println("\nCall findAllUsers()");
//            Artist artist2 = IUserDao.addUser(new Artist("Mary","Ann","maryann","mann123"));     // call a method in the DAO
//            System.out.println("User: " + artist2.toString());
//
//            System.out.println("\nCall findAllUsers()");
//             artists = IUserDao.findAllUsers();     // call a method in the DAO
//
//            if( artists.isEmpty() )
//                System.out.println("There are no Users");
//            else {
//                for (Artist artist1 : artists)
//                    System.out.println("User: " + artist1.toString());
//            }
//            //test-updatepassword
//            System.out.println("\nCall UpdatePassword()");
//            Artist artist3Z = IUserDao.addUser(new Artist("Mary","Ann","maryann","mann123"));     // call a method in the DAO
//            System.out.println("User: " + artist2.toString());
//
//            System.out.println("\nCall findAllUsers()");
//            artists = IUserDao.findAllUsers();     // call a method in the DAO
//
//            if( artists.isEmpty() )
//                System.out.println("There are no Users");
//            else {
//                for (Artist artist1 : artists)
//                    System.out.println("User: " + artist1.toString());
//            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }
}
