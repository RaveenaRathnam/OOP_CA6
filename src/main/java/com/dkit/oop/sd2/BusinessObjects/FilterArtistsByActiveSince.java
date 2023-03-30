package com.dkit.oop.sd2.BusinessObjects;

import com.dkit.oop.sd2.DTOs.Artist;

public class FilterArtistsByActiveSince implements IFilter{
    private int minActiveSince, maxActiveSince;

    public FilterArtistsByActiveSince(int minActiveSince, int maxActiveSince)
    {
        this.minActiveSince = minActiveSince;
        this.maxActiveSince= maxActiveSince;
    }

    @Override
    public boolean matches(Object object)
    {
        Artist a = (Artist) object;

        return a.getActive_since() >= minActiveSince && a.getActive_since() <=maxActiveSince;
    }
}
