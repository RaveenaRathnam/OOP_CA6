package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DTOs.Artist;

import java.util.Comparator;

public class ArtistActiveSinceComparator implements Comparator<Artist> {
    // implement the compare() method required by the Comparator interface
    public int compare(Artist a1, Artist a2) {
        if (a1.getActive_since() < a2.getActive_since()) {
            return -1;    // a negative value
        } else if (a1.getActive_since() == a2.getActive_since()) {
            return 0;
        } else {
            return 1;
        }

        // Note that we must use getActive_since() above, as the active_since field
        // is private to the Artist class.

//		As an alternative to the above, we could subtract one from
//		the other and return the difference.
//		return (a1.getActive_since() - a2.getActive_since());

    }
}