package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DTOs.Artist;

public class FilterArtistsByGenre implements IFilter {

        private String genre;

        public FilterArtistsByGenre(String genre) {
            this.genre = genre;
        }

        @Override
        public boolean matches(Object other) {
            Artist a = (Artist) other;        // cast from Object to Artist
            return a.getGenre().equalsIgnoreCase(genre);
        }
}
