package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DAOs.ArtistDaoInterface;
import com.dkit.oop.sd2.DTOs.Artist;
import com.dkit.oop.sd2.Exceptions.DaoException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AppTest {

    private static ArtistDaoInterface daoMock;
    private List<Artist> artists;
    @Before
    public void setUp() throws Exception {
        daoMock=mock(ArtistDaoInterface.class);
        artists = new ArrayList<>();
        artists.add(new Artist(1, "Artist1", "Country1", "Genre1", 2000, "Biography1", 4.5));
        artists.add(new Artist(2, "Artist2", "Country2", "Genre2", 1995, "Biography2", 4.0));
        artists.add(new Artist(3, "Artist3", "Country3", "Genre3", 2010, "Biography3", 3.5));

        try {
            for(int i=0;i<artists.size();i++) {
                daoMock.insertArtist(artists.get(i));
            }
        } catch (DaoException e) {
            System.out.println("Error adding artists: " + e.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {

        daoMock=null;
    }

    @Test
    public void testFindAllArtists() throws DaoException {
        List<Artist> expectedArtists = new ArrayList<>();
        expectedArtists.add(new Artist(1, "Artist1", "Country1", "Genre1", 2000, "Biography1", 4.5));
        expectedArtists.add(new Artist(2, "Artist2", "Country2", "Genre2", 1995, "Biography2", 4.0));
        expectedArtists.add(new Artist(3, "Artist3", "Country3", "Genre3", 2010, "Biography3", 3.5));

        // Mock the DAO's findAllArtists() method to return the expected artists
        when(daoMock.findAllArtists()).thenReturn(expectedArtists);

        List<Artist> actualArtists = daoMock.findAllArtists();
//        String actual = "";
//        for (Artist a : actualArtists) {
//            actual +=a.toString() + "\n";
//        }
//        String expected = "Artist{id=1, name='Artist1', country='Country1', genre='Genre1', active_since=2000, biography='Biography1', rating=4.5}\n" +
//                          "Artist{id=2, name='Artist2', country='Country2', genre='Genre2', active_since=1995, biography='Biography2', rating=4.0}\n" +
//                          "Artist{id=3, name='Artist3', country='Country3', genre='Genre3', active_since=2010, biography='Biography3', rating=3.5}\n" +
//                           "";

         assertEquals(expectedArtists,actualArtists);
    }
    @Test
    public void testFindArtistById() throws DaoException {
        List<Artist> expectedArtists = new ArrayList<>();
        expectedArtists.add(new Artist(1, "Artist1", "Country1", "Genre1", 2000, "Biography1", 4.5));
        expectedArtists.add(new Artist(2, "Artist2", "Country2", "Genre2", 1995, "Biography2", 4.0));
        expectedArtists.add(new Artist(3, "Artist3", "Country3", "Genre3", 2010, "Biography3", 3.5));

        // Mock the DAO's findAllArtists() method to return the expected artists
        when(daoMock.findArtistById(1)).thenReturn(expectedArtists.get(0));

        Artist actualArtist = daoMock.findArtistById(1);
        String actual =actualArtist.toString();

        String expected = "Artist{id=1, name='Artist1', country='Country1', genre='Genre1', active_since=2000, biography='Biography1', rating=4.5}";

        assertEquals(expected,actual);
    }
    @Test
    public void testDeleteArtistById() throws DaoException {
         int artistId=1;
         boolean expectedResult=true;
        // Mock the DAO's deleteArtistById() method to return the expected result
        when(daoMock.deleteArtistById(artistId)).thenReturn(expectedResult);

        boolean actualResult = daoMock.deleteArtistById(artistId);

        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testInsertArtist() throws DaoException {
        Artist artist = new Artist("Artist4", "Country4", "Genre4", 2005, "Biography4", 4.0);
        when(daoMock.insertArtist(artist)).thenReturn(true);

        boolean inserted = daoMock.insertArtist(artist);

        assertTrue(inserted);
    }



}