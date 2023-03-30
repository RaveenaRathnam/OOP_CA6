package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DTOs.Artist;

import java.util.Comparator;

public class ArtistRatingComparator implements Comparator<Artist> {
    // implement the compare() method required by the Comparator interface
    public int compare(Artist a1, Artist a2) {
        if (a1.getRating() < a2.getRating()) {
            return -1;	// a negative value
        } else if (a1.getRating() == a2.getRating()) {
            return 0;
        } else {
            return 1;
        }

        // Note that we must use getRating() above, as the rating field
        // is private to the Artist class.

//		As an alternative to the above, we could subtract one from
//		the other and return the difference.
//		return (a1.getRating() - a2.getRating());

    }
}