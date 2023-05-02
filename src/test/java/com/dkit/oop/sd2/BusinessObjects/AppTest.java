package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DAOs.ArtistDaoInterface;
import com.dkit.oop.sd2.DTOs.Artist;
import com.dkit.oop.sd2.Exceptions.DaoException;
import com.google.gson.Gson;
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

    @Before
    public void setUp() throws Exception {
        daoMock=mock(ArtistDaoInterface.class);

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

    @Test
    public void testFilterArtistsByName() throws DaoException {
        IFilter filter=new FilterArtistsByName("Artist1");
        List<Artist> artistsExpected = new ArrayList<>();
        artistsExpected.add(new Artist(1, "Artist1", "Country1", "Genre1", 2000, "Biography1", 4.5));

        when(daoMock.filterArtists(filter)).thenReturn(artistsExpected);
        List<Artist> artistsActual=daoMock.filterArtists(filter);

         assertEquals(artistsExpected,artistsActual);

    }
    @Test
    public void testFilterArtistsByGenre() throws DaoException {
        IFilter filter=new FilterArtistsByGenre("Genre1");
        List<Artist> artistsExpected = new ArrayList<>();
        artistsExpected.add(new Artist(1, "Artist1", "Country1", "Genre1", 2000, "Biography1", 4.5));

        when(daoMock.filterArtists(filter)).thenReturn(artistsExpected);
        List<Artist> artistsActual=daoMock.filterArtists(filter);

        assertEquals(artistsExpected,artistsActual);

    }
    @Test
    public void testFilterArtistsByRating() throws DaoException {
        IFilter filter=new FilterArtistsByRating(3.5,4.5);
        List<Artist> artistsExpected = new ArrayList<>();
        artistsExpected.add(new Artist(1, "Artist1", "Country1", "Genre1", 2000, "Biography1", 4.5));
        artistsExpected.add(new Artist(2, "Artist2", "Country2", "Genre2", 1995, "Biography2", 4.0));
        artistsExpected.add(new Artist(3, "Artist3", "Country3", "Genre3", 2010, "Biography3", 3.5));
        when(daoMock.filterArtists(filter)).thenReturn(artistsExpected);
        List<Artist> artistsActual=daoMock.filterArtists(filter);

        assertEquals(artistsExpected,artistsActual);

    }
    @Test
    public void testFilterArtistsByActiveSince() throws DaoException {
        IFilter filter=new FilterArtistsByActiveSince(1995,2010);
        List<Artist> artistsExpected = new ArrayList<>();
        artistsExpected.add(new Artist(1, "Artist1", "Country1", "Genre1", 2000, "Biography1", 4.5));
        artistsExpected.add(new Artist(2, "Artist2", "Country2", "Genre2", 1995, "Biography2", 4.0));
        artistsExpected.add(new Artist(3, "Artist3", "Country3", "Genre3", 2010, "Biography3", 3.5));
        when(daoMock.filterArtists(filter)).thenReturn(artistsExpected);
        List<Artist> artistsActual=daoMock.filterArtists(filter);

        assertEquals(artistsExpected,artistsActual);

    }
    @Test
    public void testFindAllArtistsJson() throws DaoException {
        String expected = "Artist{id=1, name='Artist1', country='Country1', genre='Genre1', active_since=2000, biography='Biography1', rating=4.5}\n" +
                          "Artist{id=2, name='Artist2', country='Country2', genre='Genre2', active_since=1995, biography='Biography2', rating=4.0}\n" +
                          "Artist{id=3, name='Artist3', country='Country3', genre='Genre3', active_since=2010, biography='Biography3', rating=3.5}\n" +
                           "";
        // Mock the DAO's findArtistById() method to return the example Artist object
        when(daoMock.findAllArtistsJson()).thenReturn(expected);

        // Call the DAO's findArtistByIdJson() method with the ID of the example Artist object
        String json = daoMock.findAllArtistsJson();

        // Assert that the deserialized Artist object matches the example Artist object
        assertEquals(expected,json);
    }
    @Test
    public void testFindArtistByIdJson() throws DaoException {
        // Create an example Artist object
         String expected = "Artist{id=1, name='Artist1', country='Country1', genre='Genre1', active_since=2000, biography='Biography1', rating=4.5}";
        // Mock the DAO's findArtistById() method to return the example Artist object
        when(daoMock.findArtistByIdJson(1)).thenReturn(String.valueOf(expected));

        // Call the DAO's findArtistByIdJson() method with the ID of the example Artist object
        String json = daoMock.findArtistByIdJson(1);

        // Assert that the deserialized Artist object matches the example Artist object
        assertEquals(expected,json);
    }


}