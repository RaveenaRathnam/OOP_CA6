package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DTOs.Artist;

public class FilterArtistByName implements IFilter{
    private String name;

    public FilterArtistByName(String name) {
        this.name = name;
    }

    @Override
    public boolean matches(Object other) {
        Artist a = (Artist) other;        // cast from Object to Artist
        return a.getName().equalsIgnoreCase(name);
    }
}
