package com.dkit.oop.sd2.DAOs;

/** OOP Feb 2022
 *
 * Data Access Object (DAO) for User table with MySQL-specific code
 * This 'concrete' class implements the 'UserDaoInterface'.
 *
 * The DAO will contain the SQL query code to interact with the database,
 * so, the code here is specific to a particular database (e.g. MySQL or Oracle etc...)
 * No SQL queries will be used in the Business logic layer of code, thus, it
 * will be independent of the database specifics.
 *
 * The Business Logic layer is only permitted to access the database by calling
 * methods provided in the Data Access Layer - i.e. by callimng the DAO methods.
 *
 */


import com.dkit.oop.sd2.BusinessObjects.IFilter;
import com.dkit.oop.sd2.BusinessObjects.JsonDeserializerArtist;
import com.dkit.oop.sd2.Cache.ArtistCache;
import com.dkit.oop.sd2.DTOs.Artist;
import com.dkit.oop.sd2.Exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;


public class MySqlArtistDao extends MySqlDao implements ArtistDaoInterface {
    ArtistCache cache = new ArtistCache();

    @Override
    public List<Artist> findAllArtists() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Artist> artistsList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM ARTIST";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                String country = resultSet.getString("COUNTRY");
                String genre = resultSet.getString("GENRE");
                int active_since = resultSet.getInt("ACTIVE_SINCE");
                String biography = resultSet.getString("BIOGRAPHY");
                double rating = resultSet.getDouble("RATING");
                Artist a = new Artist(id, name, country, genre, active_since, biography, rating);
                artistsList.add(a);

            }
            if (!artistsList.isEmpty())
                for (Artist a : artistsList) {
                    cache.add(a.getId());
                }
        } catch (SQLException e) {
            throw new DaoException("findAllArtistsresultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllArtists() " + e.getMessage());
            }
        }

        return artistsList;     // may be empty

    }

    @Override
    public Artist findArtistById(int artistId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Artist artist = null;
        findAllArtists();
        try {
            connection = this.getConnection();
            if (cache.contains(artistId)) {
                String query = "SELECT * FROM ARTIST WHERE ID= ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, artistId);

                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("NAME");
                    String country = resultSet.getString("COUNTRY");
                    String genre = resultSet.getString("GENRE");
                    int active_since = resultSet.getInt("ACTIVE_SINCE");
                    String biography = resultSet.getString("BIOGRAPHY");
                    double rating = resultSet.getDouble("RATING");
                    artist = new Artist(id, name, country, genre, active_since, biography, rating);

                }
            }
        } catch (SQLException e) {
            throw new DaoException("findArtistById() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findArtistById() " + e.getMessage());
            }
        }

        return artist;     // reference to User object, or null value

    }

    @Override
    public boolean deleteArtistById(int artistId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean result = false;
        findAllArtists();
        try {
            connection = this.getConnection();
            if (cache.contains(artistId)) {
                String query = "DELETE FROM ARTIST WHERE ID= ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, artistId);

                int value = preparedStatement.executeUpdate();

                if (value == 1) {
                    result = true;
                    cache.remove(artistId);
                } else {
                    result = false;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("deleteArtistById() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("deleteArtistById() " + e.getMessage());
            }
        }
        return result;
    }

    public boolean insertArtist(Artist artist) throws DaoException {
        Connection connection = null;
        PreparedStatement ps1 = null;
        boolean result = false;
        findAllArtists();
        // List<User> usersList = new ArrayList<>();
//         artist = null;

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();
            if (!cache.contains(artist.getId())) {
                String query1 = " INSERT INTO ARTIST (name, country, genre, active_since, biography, rating) VALUES(?,?,?,?,?,?)";

                ps1 = connection.prepareStatement(query1);

                ps1.setString(1, artist.getName());
                ps1.setString(2, artist.getCountry());
                ps1.setString(3, artist.getGenre());
                ps1.setInt(4, artist.getActive_since());
                ps1.setString(5, artist.getBiography());
                ps1.setDouble(6, artist.getRating());

                //Using a PreparedStatement to execute SQL...

                int value = ps1.executeUpdate();
                if (value == 1) {
                    result = true;
                    cache.add(artist.getId());
                } else {
                    result = false;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("findAllArtistresultSet() " + e.getMessage());
        } finally {
            try {

                if (ps1 != null) {
                    ps1.close();

                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("insertArtist() " + e.getMessage());
            }
        }
        return result;
    }

    /**
     * Filters the artists in the databases based on the provided filter.
     *
     * @param filter An IFilter object, which is checked against all the artists to see if they match the filter.
     * @return A list of the filtered recipes
     * @throws DaoException Extends SQLException
     * @see IFilter
     */
//    @Override
    public List<Artist> filterArtists(IFilter filter) throws DaoException {
        List<Artist> filteredList = new ArrayList<>();

        try {
            List<Artist> allArtists = findAllArtists();
            for (Artist artist : allArtists) {
                if (filter.matches(artist)) {
                    filteredList.add(artist);
                }
            }
        } catch (DaoException daoe) {
            System.out.println("filterArtists() " + daoe.getMessage());
        }

        return filteredList;
    }

    /**
     * Gets all the artists in the database in JSON String format.
     *
     * @return A JSON String containing all the artists in the database
     * @throws DaoException Extends SQLException
     */
    @Override
    public String findAllArtistsJson() throws DaoException {
        List<Artist> artistsList = findAllArtists();

        if (artistsList == null || artistsList.isEmpty()) return null;

        Gson gsonParser = new Gson();
        return gsonParser.toJson(artistsList);
    }

    /**
     * Gets the JSON String of a Artist object when provided the id.
     *
     * @param artistId The id of the artist
     * @return A JSON String containing the fields of the artist object matching the provided id.
     * @throws DaoException Extends SQLException
     */
    @Override
    public String findArtistByIdJson(int artistId) throws DaoException {
        Artist artist = findArtistById(artistId);

        if (artist == null) return null;

        Gson gsonParser = new Gson();
        return gsonParser.toJson(artist);
    }

}
