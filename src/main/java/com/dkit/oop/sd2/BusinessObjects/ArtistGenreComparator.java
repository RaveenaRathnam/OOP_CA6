package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DTOs.Artist;

import java.util.Comparator;

public class ArtistGenreComparator implements Comparator<Artist> {
    // implement the compare() method required by the Comparator interface
    public int compare(Artist a1, Artist a2) {
        return a1.getGenre().compareTo(a2.getGenre());

        // The Genre of a ARTIST is of type String and the String class implements
        // a compareTo() method that returns -1, 0, or +1 as appropriate.
        // So, to compare string fields we simply use the compareTo() method
        // of the String class.
    }
}
