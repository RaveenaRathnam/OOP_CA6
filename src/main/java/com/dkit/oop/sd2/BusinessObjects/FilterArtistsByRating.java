package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DTOs.Artist;

public class FilterArtistsByRating implements IFilter{
    private double minRating, maxRating;

    public FilterArtistsByRating(double minRating, double maxRating)
    {
        this.minRating = minRating;
        this.maxRating = maxRating;
    }

    @Override
    public boolean matches(Object object)
    {
        Artist a = (Artist) object;

        return a.getRating() >= minRating && a.getRating() <= maxRating;
    }
}
